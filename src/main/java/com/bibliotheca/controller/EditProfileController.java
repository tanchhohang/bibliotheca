package com.bibliotheca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.time.LocalDate;

import com.bibliotheca.model.UserModel;
import com.bibliotheca.service.EditProfileService;
import com.bibliotheca.util.CookieUtil;
import com.bibliotheca.util.ImageUtil;
import com.bibliotheca.util.PasswordUtil;
import com.bibliotheca.util.ValidationUtil;

/**
 * Author Tanchho LImbu
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/editprofile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */	
    public EditProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private final ImageUtil imageUtil = new ImageUtil();
	private final EditProfileService editProfileService = new EditProfileService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie emailCookie = CookieUtil.getCookie(request, "email");
		String userEmail = null;
		
		if (emailCookie != null) {
		    userEmail = emailCookie.getValue();
		}
        
        if (userEmail != null) {
            // Fetch user data from database
            UserModel user = editProfileService.getUserByEmail(userEmail);
            
            if (user != null) {
                // Set user data as request attributes
                request.setAttribute("First_Name", user.getFirstName());
                request.setAttribute("Last_Name", user.getLastName());
                request.setAttribute("DOB", user.getDob());
                request.setAttribute("Gender", user.getGender());
                request.setAttribute("Email", user.getEmail());
                request.setAttribute("Membership", user.getMembership());
                request.setAttribute("Address", user.getAddress());
            }
        }
		
		request.getRequestDispatcher("/WEB-INF/pages/edit-profile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Cookie emailCookie = CookieUtil.getCookie(request, "email");

			String currentEmail = null;
			if (emailCookie != null) {
			    currentEmail = emailCookie.getValue();
			}

			// Validate form data
			String validationMessage = validateRegistrationForm(request);
			if (validationMessage != null) {
				handleError(request, response, validationMessage);
				return;
			}

			// Get current user data to preserve unchanged fields
			UserModel currentUser = editProfileService.getUserByEmail(currentEmail);
			if (currentUser == null) {
				handleError(request, response, "Could not retrieve user information.");
				return;
			}
			
			// Extract form data and merge with current data
			UserModel userModel = extractUserModel(request, currentUser);
			
			// Update the user
			Boolean isUpdated = editProfileService.updateUser(userModel, currentEmail);

			if (isUpdated == null) {
				handleError(request, response, "Our server is under maintenance. Please try again later!");
			} else if (isUpdated) {
				try {
					if (uploadImage(request)) {
						handleSuccess(request, response, "Your profile is successfully updated!");
					} else {
						handleError(request, response, "Could not upload the image. Please try again later!");
					}
				} catch (IOException | ServletException e) {
					handleError(request, response, "An error occurred while uploading the image. Please try again later!");
					e.printStackTrace();
				}
			} else {
				handleError(request, response, "Could not update your profile. Please try again later!");
			}
		} catch (Exception e) {
			handleError(request, response, "An unexpected error occurred. Please try again later!");
			e.printStackTrace();
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
		
		if (password != null && !password.isEmpty()) {
			if (!ValidationUtil.isValidPassword(password))
				return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
			if (!ValidationUtil.doPasswordsMatch(password, retypePassword))
				return "Passwords do not match.";
		}

		if (!ValidationUtil.isAgeAtLeast16(dob))
			return "You must be at least 16 years old to register.";

		try {
			Part image = request.getPart("image");
			if (image != null && image.getSize() > 0 && !ValidationUtil.isValidImageExtension(image))
				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
		} catch (IOException | ServletException e) {
			return "Error handling image file. Please ensure the file is valid.";
		}

		return null;
	}

	private UserModel extractUserModel(HttpServletRequest request, UserModel currentUser) throws Exception {
	    String firstName = request.getParameter("firstName");
	    String lastName = request.getParameter("lastName");
	    String dobStr = request.getParameter("dob");
	    String gender = request.getParameter("gender");
	    String email = request.getParameter("email");
	    String membership = request.getParameter("membership");
	    String address = request.getParameter("address");
	    String password = request.getParameter("password");
	    
	    // Keep the existing password if no new one provided
	    if (password == null || password.isEmpty()) {
	        password = currentUser.getPassword();
	    } else {
	        password = PasswordUtil.encrypt(email, password);
	    }

	    // Get image filename
	    Part image = request.getPart("image");
	    String profilePic = (image != null && image.getSize() > 0) ? 
	                     imageUtil.getImageNameFromPart(image) : 
	                     currentUser.getProfilePic();

	    return new UserModel(firstName, lastName, dobStr, gender, email, membership, address, password, currentUser.getRole(), profilePic);
	}

	private boolean uploadImage(HttpServletRequest request) throws IOException, ServletException {
		Part image = request.getPart("image");
		if (image == null || image.getSize() == 0) {
			return true; // No image to upload
		}
		String rootPath = request.getServletContext().getRealPath("/");
		return imageUtil.uploadImage(image, rootPath, "user");
	}

	private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("success", message);
		response.sendRedirect(request.getContextPath() + "/userprofile");
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("error", message);
		
		// Get user email from cookie
		String userEmail = CookieUtil.getCookie(request, "email") != null ? 
                          CookieUtil.getCookie(request, "email").getValue() : null;
		
		if (userEmail != null) {
			// Fetch user data again to display in form
			UserModel user = editProfileService.getUserByEmail(userEmail);
			if (user != null) {
				request.setAttribute("First_Name", user.getFirstName());
				request.setAttribute("Last_Name", user.getLastName());
				request.setAttribute("DOB", user.getDob());
				request.setAttribute("Gender", user.getGender());
				request.setAttribute("Email", user.getEmail());
				request.setAttribute("Membership", user.getMembership());
				request.setAttribute("Address", user.getAddress());
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/edit-profile.jsp").forward(request, response);
	}
}