package com.bibliotheca.controller;

import java.io.IOException;

import com.bibliotheca.util.CookieUtil;
import com.bibliotheca.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogOutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CookieUtil.deleteCookie(response, "role");
        CookieUtil.deleteCookie(response, "email");
        
        // Remove the 'email' session variable
        SessionUtil.invalidateSession(request);
        
        // Redirect to the home page
        response.sendRedirect(request.getContextPath() + "/home");
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call doGet to handle post requests the same way
        doGet(request, response);
    }
}