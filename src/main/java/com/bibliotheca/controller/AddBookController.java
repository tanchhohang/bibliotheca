package com.bibliotheca.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.bibliotheca.model.BookModel;
import com.bibliotheca.service.BookService;
import com.bibliotheca.util.ImageUtil;

@WebServlet("/addbook")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,  // 10 MB
    maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class AddBookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookService bookService;
    private String uploadPath;
    private ImageUtil imageUtil;
    
    @Override
    public void init() throws ServletException {
        super.init();
        bookService = new BookService();
        imageUtil = new ImageUtil();
        
        // Set upload directory path - you may need to adjust this based on your server configuration
        uploadPath = getServletContext().getRealPath("");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the add book form
        request.getRequestDispatcher("/WEB-INF/pages/addbook.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract form data from the updated form fields
            String bookName = request.getParameter("bookName");
            String author = request.getParameter("author");
            String description = request.getParameter("description");
            String genre = request.getParameter("genre");
            String publisher = request.getParameter("publisher");
            String availability = request.getParameter("availability");
            
            // Set proper availability format based on form selection
            if ("available".equals(availability)) {
                availability = "Available";
            } else if ("notAvailable".equals(availability)) {
                availability = "Not Available";
            }
            
            // Process image upload
            String imagePath = null;
            Part filePart = request.getPart("image");
            if (filePart != null && filePart.getSize() > 0) {
                String saveFolder = "uploads/books";
                boolean uploadSuccess = imageUtil.uploadImage(filePart, uploadPath, saveFolder);
                
                if (uploadSuccess) {
                    String imageName = imageUtil.getImageNameFromPart(filePart);
                    imagePath = saveFolder + "/" + imageName;
                    System.out.println("Image uploaded successfully: " + imagePath);
                } else {
                    System.out.println("Failed to upload image");
                }
            }
            
            // Create book model
            BookModel book = new BookModel();
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setDescription(description);
            book.setGenre(genre);
            book.setPublisher(publisher);
            book.setAvailability(availability);
            book.setBookImage(imagePath);
            
            // Add to database using service
            boolean success = addBook(book);
            
            if (success) {
                // Redirect to book list or success page
                response.sendRedirect(request.getContextPath() + "/library");
            } else {
                // Set error message and return to form
                request.setAttribute("error", "Failed to add book. Please try again.");
                request.setAttribute("bookName", bookName);
                request.setAttribute("author", author);
                request.setAttribute("description", description);
                request.setAttribute("genre", genre);
                request.setAttribute("publisher", publisher);
                request.setAttribute("availability", availability);
                request.getRequestDispatcher("/WEB-INF/pages/addbook.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/pages/addbook.jsp").forward(request, response);
        }
    }
    
    private boolean addBook(BookModel book) {
        // Using the BookService to add a book to the database
        if (bookService == null) {
            bookService = new BookService();
        }
        
        try {
            return bookService.addBook(book);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public void destroy() {
        if (bookService != null) {
            bookService.closeConnection();
        }
        super.destroy();
    }
}