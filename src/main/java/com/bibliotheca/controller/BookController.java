package com.bibliotheca.controller;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.bibliotheca.model.BookModel;
import com.bibliotheca.model.UserBookModel;
import com.bibliotheca.service.BookService;
import com.bibliotheca.service.UserBookService;
import com.bibliotheca.util.CookieUtil;
import com.bibliotheca.util.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * BookController is responsible for handling book page requests and redirecting users
 * to the appropriate page based on their role.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/book" })
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;
    private UserBookService userBookService;
    
    /**
     * Initialize the controller with a BookService instance
     */
    @Override
    public void init() throws ServletException {
        super.init();
        bookService = new BookService();
        userBookService = new UserBookService();
    }
    
    /**
     * Handles GET requests to the book page.
     * Retrieves book information from the database and forwards to JSP.
     * Redirects users to either admin-book.jsp or book.jsp based on their role.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in by looking for role cookie
        String role = null;
        if (CookieUtil.getCookie(request, "role") != null) {
            role = CookieUtil.getCookie(request, "role").getValue();
        } else if (SessionUtil.getAttribute(request, "role") != null) {
            // Fallback to session if cookie not available
            role = (String) SessionUtil.getAttribute(request, "role");
        }
        
        System.out.println("User role: " + role);
        
        // Get book ID from request parameters
        String bookIdParam = request.getParameter("id");
        
        if (bookIdParam != null && !bookIdParam.isEmpty()) {
            try {
                int bookId = Integer.parseInt(bookIdParam);
                // Get book details from database
                BookModel book = bookService.getBookById(bookId);
                
                if (book != null) {
                    // Set book object as request attribute to be used in JSP
                    request.setAttribute("book", book);
                    System.out.println("Retrieved book: " + book.getBookName());
                } else {
                    System.out.println("Book not found with ID: " + bookId);
                    response.sendRedirect(request.getContextPath() + "/books");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid book ID format: " + bookIdParam);
                response.sendRedirect(request.getContextPath() + "/books");
                return;
            }
        } else {
            System.out.println("No book ID provided in request");
            response.sendRedirect(request.getContextPath() + "/books");
            return;
        }
        
        // Redirect based on role
        if (role == null) {
            // If not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
        } else if (role.equals("Admin")) {
            // If admin, forward to admin book page
            request.getRequestDispatcher("/WEB-INF/pages/admin-book.jsp").forward(request, response);
        } else if (role.equals("User")) {
            // If regular user, forward to user book page
            request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
        } else {
            // For any other unexpected role, redirect to login
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
    
    /**
     * Handles POST requests for borrowing a book.
     * Updates book availability and creates a user-book record.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract book ID and action from request
        String bookIdParam = request.getParameter("id");
        String action = request.getParameter("action");
        
        // Check if user is logged in and get user ID
        Integer userId = null;
        if (SessionUtil.getAttribute(request, "userId") != null) {
            userId = (Integer) SessionUtil.getAttribute(request, "userId");
        }
        
        if (userId == null) {
            // User not logged in, redirect to login
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Process borrow action
        if ("borrow".equals(action) && bookIdParam != null && !bookIdParam.isEmpty()) {
            try {
                int bookId = Integer.parseInt(bookIdParam);
                BookModel book = bookService.getBookById(bookId);
                
                if (book != null && "Available".equals(book.getAvailability())) {
                    // Update book availability
                    boolean updated = bookService.updateBookAvailability(bookId, "Not Available");
                    
                    if (updated) {
                        // Create borrowing record
                        UserBookModel userBook = new UserBookModel();
                        userBook.setUser_ID(userId);
                        userBook.setBook_ID(bookId);
                        
                        // Set borrowed date to today
                        LocalDate borrowedDate = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        userBook.setBorrowed_Date(borrowedDate.format(formatter));
                        
                        // Set due date to 14 days from now
                        LocalDate dueDate = borrowedDate.plusDays(14);
                        userBook.setDue_Date(dueDate.format(formatter));
                        
                        // Save the record
                        boolean recordCreated = userBookService.createBorrowRecord(userBook);
                        
                        if (recordCreated) {
                            // Redirect to user dashboard or book details with success message
                            response.sendRedirect(request.getContextPath() + "/book?id=" + bookId + "&borrowed=true");
                        } else {
                            // Handle error in creating borrow record
                            System.out.println("Failed to create borrow record");
                            response.sendRedirect(request.getContextPath() + "/book?id=" + bookId + "&error=borrow");
                        }
                    } else {
                        // Handle error in updating book availability
                        System.out.println("Failed to update book availability");
                        response.sendRedirect(request.getContextPath() + "/book?id=" + bookId + "&error=availability");
                    }
                } else {
                    // Book not available or doesn't exist
                    System.out.println("Book not available or doesn't exist");
                    response.sendRedirect(request.getContextPath() + "/book?id=" + bookId + "&error=notavailable");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid book ID format: " + bookIdParam);
                response.sendRedirect(request.getContextPath() + "/books");
            }
        } else {
            // Invalid action or missing parameters
            response.sendRedirect(request.getContextPath() + "/books");
        }
    }
    
    /**
     * Clean up resources when servlet is destroyed
     */
    @Override
    public void destroy() {
        if (bookService != null) {
            bookService.closeConnection();
        }
        if (userBookService != null) {
            userBookService.closeConnection();
        }
        super.destroy();
    }
}