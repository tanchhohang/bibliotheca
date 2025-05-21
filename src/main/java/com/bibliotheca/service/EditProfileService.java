package com.bibliotheca.service;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bibliotheca.config.DbConfig;
import com.bibliotheca.model.UserModel;

public class EditProfileService {

    private Connection dbConn;

    public EditProfileService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public UserModel getUserByEmail(String email) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        String selectQuery = "SELECT * FROM user WHERE Email = ?";

        try (PreparedStatement selectStmt = dbConn.prepareStatement(selectQuery)) {
            selectStmt.setString(1, email);

            try (ResultSet rs = selectStmt.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setFirstName(rs.getString("First_Name"));
                    user.setLastName(rs.getString("Last_Name"));
                    user.setDob(rs.getDate("DOB").toString());
                    user.setGender(rs.getString("Gender"));
                    user.setEmail(rs.getString("Email"));
                    user.setMembership(rs.getString("Membership"));
                    user.setAddress(rs.getString("Address"));
                    user.setRole(rs.getString("Role"));
                    user.setProfilePic(rs.getString("Profile_pic"));
                    user.setPassword(rs.getString("Password"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Boolean updateUser(UserModel userModel, String currentEmail) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }

        if (userModel.getPassword() == null || userModel.getPassword().isEmpty()) {
            UserModel currentUser = getUserByEmail(currentEmail);
            if (currentUser != null && currentUser.getPassword() != null) {
                userModel.setPassword(currentUser.getPassword());
            } else {
                System.err.println(" Password cannot be null");
                return false;
            }
        }

        String updateQuery = "UPDATE user SET First_Name= ?, Last_Name= ?, DOB= ?, Gender= ?, Email= ?, Membership= ?, Address= ?, Password= ?, Role= ?, Profile_pic= ? WHERE Email= ?";

        try (PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery)) {
            // Update user details
            updateStmt.setString(1, userModel.getFirstName());
            updateStmt.setString(2, userModel.getLastName());
            updateStmt.setDate(3, Date.valueOf(userModel.getDob()));
            updateStmt.setString(4, userModel.getGender());
            updateStmt.setString(5, userModel.getEmail());
            updateStmt.setString(6, userModel.getMembership());
            updateStmt.setString(7, userModel.getAddress());
            updateStmt.setString(8, userModel.getPassword());
            updateStmt.setString(9, userModel.getRole());
            updateStmt.setString(10, userModel.getProfilePic());
            updateStmt.setString(11, currentEmail);

            return updateStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during profile update: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}