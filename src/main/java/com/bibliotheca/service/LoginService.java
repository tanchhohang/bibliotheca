package com.bibliotheca.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bibliotheca.config.DbConfig;
import com.bibliotheca.model.UserModel;
import com.bibliotheca.util.PasswordUtil;

/**
 * Service class for handling login operations. Connects to the database,
 * verifies user credentials, and returns login status.
 */
public class LoginService {

	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public LoginService() {
		try {
			dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Validates the user credentials against the database records.
	 *
	 * @param userModel the userModel object containing user credentials
	 * @return true if the user credentials are valid, false otherwise; null if a
	 *         connection error occurs
	 */
	public Boolean loginUser(UserModel userModel) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT Email, Password, Role FROM user WHERE Email = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, userModel.getEmail());
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				userModel.setRole(result.getString("role"));
				return validatePassword(result, userModel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}

	/**
	 * Validates the password retrieved from the database.
	 *
	 * @param result       the ResultSet containing the username, and password from
	 *                     the database
	 * @param userModel the userModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
		String dbUsername = result.getString("email");
		String dbPassword = result.getString("password");

		return dbUsername.equals(userModel.getEmail())
				&& PasswordUtil.decrypt(dbPassword, dbUsername).equals(userModel.getPassword());
	}
}