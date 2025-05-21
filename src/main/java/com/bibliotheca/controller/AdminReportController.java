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
import com.bibliotheca.service.AdminReportService;

@WebServlet("/adminreport")
public class AdminReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminReportService reportService;
    
    public AdminReportController() {
        super();
        reportService = new AdminReportService();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Verify user is admin
        boolean isAdmin = false;
        String userRole = null;
        
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("role".equals(cookie.getName())) {
                    userRole = cookie.getValue();
                    break;
                }
            }
        }
        
        // Check if user has admin role
        if ("admin".equalsIgnoreCase(userRole)) {
            isAdmin = true;
        }
        
        // If not admin, redirect to access denied page
        if (!isAdmin) {
            response.sendRedirect(request.getContextPath() + "/accessDenied");
            return;
        }
        
        try {
            // Get admin report data
            ReportModel reportData = reportService.generateAdminReport();
            
            // Save report to database for audit purposes (optional)
//            reportService.saveReport(reportData);
            
            // Set the report data as request attribute
            request.setAttribute("adminReport", reportData);
            
            // Forward to the JSP view
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/admin-report.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating admin report");
        }
    }
}