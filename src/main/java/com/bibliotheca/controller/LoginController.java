package com.bibliotheca.controller;

import java.io.IOException;

import com.bibliotheca.model.UserModel;
import com.bibliotheca.service.LoginService;
import com.bibliotheca.util.CookieUtil;
import com.bibliotheca.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * LoginController is responsible for handling login requests. It interacts with
 * the LoginService to authenticate users.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final LoginService loginService;

	/**
	 * Constructor initializes the LoginService.
	 */
	public LoginController() {
		this.loginService = new LoginService();
	}

	/**
	 * Handles GET requests to the login page.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (CookieUtil.getCookie(request, "role") == null) {
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		} else if (CookieUtil.getCookie(request, "role").getValue().equals("Admin")) {
			response.sendRedirect(request.getContextPath() + "/adminprofile");
			return;
		}  else if (CookieUtil.getCookie(request, "role").getValue().equals("User")) {
			response.sendRedirect(request.getContextPath() + "/userprofile");
			return;
		}
	}

	/**
	 * Handles POST requests for user login.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserModel userModel = new UserModel(email, password, "User");
		Boolean loginStatus = loginService.loginUser(userModel);

		if (loginStatus != null && loginStatus) {
			System.out.println(userModel.getRole());
			SessionUtil.setAttribute(req, "role", userModel.getRole());
			if (userModel.getRole().equals("Admin")) {
				CookieUtil.addCookie(resp, "role", "Admin", 5 * 30 * 60);
				CookieUtil.addCookie(resp, "email", userModel.getEmail(), 5 * 30 * 60);
				resp.sendRedirect(req.getContextPath() + "/adminprofile"); // Redirect to /home
			} else {
				CookieUtil.addCookie(resp, "role", "User", 5 * 30 * 60);
				CookieUtil.addCookie(resp, "email", userModel.getEmail(), 5 * 30 * 60);
				resp.sendRedirect(req.getContextPath() + "/userprofile"); // Redirect to /home
			}
		} else {
			handleLoginFailure(req, resp, loginStatus);
		}
	}

	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param req         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleLoginFailure(HttpServletRequest req, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		req.setAttribute("error", errorMessage);
		req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
	}

}