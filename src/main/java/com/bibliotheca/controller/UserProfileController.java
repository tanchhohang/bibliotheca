package com.bibliotheca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import com.bibliotheca.util.CookieUtil;
import com.bibliotheca.util.SessionUtil;

/**
 * Author Tanchho Limbu
 * Servlet implementation class UserProfileController
 */
@WebServlet(urlPatterns = { "/userprofile"})
public class UserProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (CookieUtil.getCookie(request, "role") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		} else if (CookieUtil.getCookie(request, "role").getValue().equals("Admin")) {
			response.sendRedirect(request.getContextPath() + "/adminprofile");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/pages/user-profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
