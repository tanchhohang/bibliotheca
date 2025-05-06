package com.bibliotheca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bibliotheca.model.UserModel;
import com.bibliotheca.service.UserManagementService;

@WebServlet(asyncSupported = true, urlPatterns = { "/usermanagement" })
public class UserManagementController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserManagementService userManagementService;
    
    public UserManagementController() {
        super();
        userManagementService = new UserManagementService();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load all users for the user management page
        List<UserModel> userList = userManagementService.getAllUsers();
        request.setAttribute("userList", userList);
        
        // Get user statistics
        ResultSet statsRS = userManagementService.getUserStats();
        try {
            if (statsRS != null && statsRS.next()) {
                request.setAttribute("totalUsers", statsRS.getInt("total_users"));
                request.setAttribute("adminCount", statsRS.getInt("admin_count"));
                request.setAttribute("memberCount", statsRS.getInt("member_count"));
            } else {
                request.setAttribute("totalUsers", 0);
                request.setAttribute("adminCount", 0);
                request.setAttribute("memberCount", 0);
            }
            
            // Close the ResultSet
            if (statsRS != null) {
                statsRS.close();
            }
        } catch (SQLException e) {
            System.err.println("Error processing user stats: " + e.getMessage());
            e.printStackTrace();
            
            // Set default values in case of error
            request.setAttribute("totalUsers", 0);
            request.setAttribute("adminCount", 0);
            request.setAttribute("memberCount", 0);
        }
        
        // Forward to JSP page
        request.getRequestDispatcher("/WEB-INF/pages/user-management.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        try {
            if (action != null && action.equals("updateRole")) {
                String userEmail = request.getParameter("userEmail");
                String newRole = request.getParameter("newRole");
                
                if (userEmail != null && !userEmail.isEmpty() && newRole != null && !newRole.isEmpty()) {
                    // Validate role value to prevent SQL injection or invalid values
                    if (!newRole.equals("Admin") && !newRole.equals("User")) {
                        session.setAttribute("message", "Invalid role value provided.");
                    } else {
                        Boolean updateResult = userManagementService.updateUserRole(userEmail, newRole);
                        
                        if (updateResult != null && updateResult) {
                            session.setAttribute("message", "User role updated successfully to " + newRole + ".");
                        } else {
                            session.setAttribute("message", "Failed to update user role. Please try again.");
                        }
                    }
                } else {
                    session.setAttribute("message", "Missing required parameters. Please try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error in doPost: " + e.getMessage());
            e.printStackTrace();
            session.setAttribute("message", "An error occurred while updating the role. Please try again.");
        }
        
        // Redirect back to the user management page
        response.sendRedirect(request.getContextPath() + "/usermanagement");
    }
}