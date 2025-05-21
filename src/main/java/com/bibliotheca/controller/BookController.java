package com.bibliotheca.controller;
import java.io.IOException;
import com.bibliotheca.model.BookModel;
import com.bibliotheca.service.BookService;
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
    
    /**
     * Initialize the controller with a BookService instance
     */
    @Override
    public void init() throws ServletException {
        super.init();
        bookService = new BookService();
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
     * Clean up resources when servlet is destroyed
     */
    @Override
    public void destroy() {
        if (bookService != null) {
            bookService.closeConnection();
        }
        super.destroy();
    }
}