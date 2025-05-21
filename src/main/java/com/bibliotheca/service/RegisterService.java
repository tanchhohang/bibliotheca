package com.bibliotheca.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bibliotheca.config.DbConfig;
import com.bibliotheca.model.UserModel;

public class RegisterService {
	
	private Connection dbConn;
	
	public RegisterService() {
		try {
			this.dbConn = DbConfig.getDbConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			System.err.println("Database connection error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public Boolean addUser(UserModel userModel) {
		if (dbConn == null) {
			System.err.println("Database connection is not available.");
			return null;
		}

		String insertQuery = "INSERT INTO user (First_Name, Last_Name, DOB, Gender, Email, Membership, Address, Password, Role, Profile_Pic)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement insertStmt = dbConn.prepareStatement(insertQuery)) {


			// Insert student details
			insertStmt.setString(1, userModel.getFirstName());
			insertStmt.setString(2, userModel.getLastName());
			insertStmt.setDate(3, Date.valueOf(userModel.getDob()));
			insertStmt.setString(4, userModel.getGender());
			insertStmt.setString(5, userModel.getEmail());
			insertStmt.setString(6, userModel.getMembership());
			insertStmt.setString(7, userModel.getAddress());
			insertStmt.setString(8, userModel.getPassword());
			insertStmt.setString(9, userModel.getRole());
			insertStmt.setString(10, userModel.getProfilePic());

			return insertStmt.executeUpdate() > 0;
		} catch (SQLException e) {
			System.err.println("Error during student registration: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
}