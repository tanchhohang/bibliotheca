package com.bibliotheca.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.bibliotheca.model.ReportModel;
import com.bibliotheca.service.UserReportService;

@WebServlet("/userreport")
public class UserReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserReportService reportService;
    
    public UserReportController() {
        super();
        reportService = new UserReportService();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Extract email from cookie
        String userEmail = null;
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("email".equals(cookie.getName())) {
                    userEmail = cookie.getValue();
                    break;
                }
            }
        }
        
        // If no email cookie is found, redirect to login
        if (userEmail == null || userEmail.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        try {
            // Get report data for the user
            ReportModel reportData = reportService.generateUserReport(userEmail);
            
            // Set the report data as request attribute
            request.setAttribute("userReport", reportData);
            
            // Forward to the JSP view
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/user-report.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating report");
        }
    }
}