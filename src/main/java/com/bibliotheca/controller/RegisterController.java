package com.bibliotheca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.bibliotheca.model.UserModel;
import com.bibliotheca.service.RegisterService;
import com.bibliotheca.util.CookieUtil;
import com.bibliotheca.util.ImageUtil;
import com.bibliotheca.util.PasswordUtil;
import com.bibliotheca.util.ValidationUtil;

/**
 * Author Tanchho LImbu
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/register"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private final ImageUtil imageUtil = new ImageUtil();
	private final RegisterService registerService = new RegisterService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (CookieUtil.getCookie(request, "role") == null) {
			request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
		} else if (CookieUtil.getCookie(request, "role").getValue().equals("Admin")) {
			response.sendRedirect(request.getContextPath() + "/adminprofile");
			return;
		}  else if (CookieUtil.getCookie(request, "role").getValue().equals("User")) {
			response.sendRedirect(request.getContextPath() + "/userprofile");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Validate and extract user model
			String validationMessage = validateRegistrationForm(request);
			if (validationMessage != null) {
				handleError(request, response, validationMessage);
				return;
			}

			UserModel userModel = extractUserModel(request);
			Boolean isAdded = registerService.addUser(userModel);

			if (isAdded == null) {
				handleError(request, response, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {
				try {
					if (uploadImage(request)) {
						handleSuccess(request, response, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
					} else {
						handleError(request, response, "Could not upload the image. Please try again later!");
					}
				} catch (IOException | ServletException e) {
					handleError(request, response, "An error occurred while uploading the image. Please try again later!");
					e.printStackTrace(); // Log the exception
				}
			} else {
				handleError(request, response, "Could not register your account. Please try again later!");
			}
		} catch (Exception e) {
			handleError(request, response, "An unexpected error occurred. Please try again later!");
			e.printStackTrace(); // Log the exception
		}
	}

	private String validateRegistrationForm(HttpServletRequest request) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dobStr = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String membership = request.getParameter("membership");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");

		// Check for null or empty fields first
		if (ValidationUtil.isNullOrEmpty(firstName))
			return "First name is required.";
		if (ValidationUtil.isNullOrEmpty(lastName))
			return "Last name is required.";
		if (ValidationUtil.isNullOrEmpty(gender))
			return "Gender is required.";
		if (ValidationUtil.isNullOrEmpty(email))
			return "Email is required.";
		if (ValidationUtil.isNullOrEmpty(address))
			return "Address is required.";
		if (ValidationUtil.isNullOrEmpty(membership))
			return "Membership is required.";
		if (ValidationUtil.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidationUtil.isNullOrEmpty(retypePassword))
			return "Please retype the password.";

		// Convert date of birth
		LocalDate dob;
		try {
			dob = LocalDate.parse(dobStr);
		} catch (Exception e) {
			return "Invalid date format. Please use YYYY-MM-DD.";
		}

		// Validate fields
		if (!ValidationUtil.isValidGender(gender))
			return "Gender must be 'male' or 'female'.";
		if (!ValidationUtil.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidationUtil.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";

		// Check if the date of birth is at least 16 years before today
		if (!ValidationUtil.isAgeAtLeast16(dob))
			return "You must be at least 16 years old to register.";

		try {
			Part image = request.getPart("image");
			if (!ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			return "Error handling image file. Please ensure the file is valid.";
		}

		return null; // All validations passed
	}

	private UserModel extractUserModel(HttpServletRequest request) throws Exception {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String dobStr = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String membership = request.getParameter("membership");
		String address = request.getParameter("address");
		String password = request.getParameter("password");

		// Assuming password validation is already done in validateRegistrationForm
		password = PasswordUtil.encrypt(email, password);

		Part image = request.getPart("image");
		String profilePic = imageUtil.getImageNameFromPart(image);

		return new UserModel(firstName, lastName, dobStr, gender, email, membership, address, password, "User",profilePic);
	}

	private boolean uploadImage(HttpServletRequest request) throws IOException, ServletException {
	    Part image = request.getPart("image");
	    String rootPath = request.getServletContext().getRealPath("/");
	    return imageUtil.uploadImage(image, rootPath, "user");
	}

	private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message, String redirectPage)
			throws ServletException, IOException {
		request.setAttribute("success", message);
		request.getRequestDispatcher(redirectPage).forward(request, response);
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("error", message);
		request.setAttribute("First_Name", request.getParameter("firstName"));
		request.setAttribute("Last_Name", request.getParameter("lastName"));
		request.setAttribute("User_Name", request.getParameter("userName"));
		request.setAttribute("DOB", request.getParameter("dob"));
		request.setAttribute("Gender", request.getParameter("gender"));
		request.setAttribute("Email", request.getParameter("email"));
		request.setAttribute("Membership", request.getParameter("membership"));
		request.setAttribute("Address", request.getParameter("address"));
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}
}