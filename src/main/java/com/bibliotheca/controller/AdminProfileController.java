package com.bibliotheca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.bibliotheca.model.BookModel;
import com.bibliotheca.model.UserModel;
import com.bibliotheca.service.ProfileService;
import com.bibliotheca.util.CookieUtil;

/**
 * Author Anush Tamang
 * Servlet implementation class AdminProfileController
 */
@WebServlet(urlPatterns = { "/adminprofile" })
public class AdminProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProfileService profileService = new ProfileService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProfileController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if user is authenticated and has admin role
        String userEmail = getAdminEmailFromCookie(request, response);
        if (userEmail == null) return;
        
        // Get admin user details
        UserModel admin = profileService.getUserByEmail(userEmail);
        if (admin == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        // Set admin user details for display in the JSP
        request.setAttribute("admin", admin);
        
        // Set library statistics
        int totalMembers = profileService.getTotalMembers();
        int totalBooks = profileService.getTotalBooks();
        int totalBorrowed = profileService.getTotalBorrowed();
        int availableBooks = totalBooks - totalBorrowed;
        
        request.setAttribute("totalMembers", totalMembers);
        request.setAttribute("totalBooks", totalBooks);
        request.setAttribute("totalBorrowed", totalBorrowed);
        request.setAttribute("availableBooks", availableBooks);
        
        // Get sample books for display (limited to 4 as shown in the JSP)
        // This could be modified to get the most recent books or other criteria
        // For now, we'll get the first 4 books in the system
        List<BookModel> recentBooks = getRecentBooks(4);
        request.setAttribute("recentBooks", recentBooks);
        
        // Forward to admin profile page
        request.getRequestDispatcher("/WEB-INF/pages/admin-profile.jsp").forward(request, response);
    }

    /**
     * Get a limited number of recent books from the system
     * 
     * @param limit Maximum number of books to retrieve
     * @return List of recent books
     */
    private List<BookModel> getRecentBooks(int limit) {
        // This is a placeholder method
        // You should implement a proper method in ProfileService to get recent books
        // For now, we're returning null, but the JSP will handle this case
        return null;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    /**
     * Verify that the user has admin role and return the admin's email
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return Admin's email or null if not authorized
     * @throws IOException If redirection fails
     */
    private String getAdminEmailFromCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie roleCookie = CookieUtil.getCookie(request, "role");
        if (roleCookie == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return null;
        }
        
        String role = roleCookie.getValue();
        if (!"Admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/userprofile");
            return null;
        }
        
        Cookie emailCookie = CookieUtil.getCookie(request, "email");
        return (emailCookie != null) ? emailCookie.getValue() : null;
    }
}