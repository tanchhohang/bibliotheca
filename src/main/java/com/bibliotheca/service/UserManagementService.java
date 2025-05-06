package com.bibliotheca.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheca.config.DbConfig;
import com.bibliotheca.model.UserModel;

public class UserManagementService {
    
    private Connection dbConn;
    
    public UserManagementService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    /**
     * Updates the role of a user by their email
     * @param email The email of the user to update
     * @param newRole The new role to assign ("Admin" or "User")
     * @return True if update was successful, False if failed, null if error occurred
     */
    public Boolean updateUserRole(String email, String newRole) {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }
        
        // Validate inputs
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Email cannot be null or empty");
            return false;
        }
        
        if (newRole == null || (!newRole.equals("Admin") && !newRole.equals("User"))) {
            System.err.println("Role must be either 'Admin' or 'User'");
            return false;
        }
        
        String updateQuery = "UPDATE user SET Role = ? WHERE Email = ?";
        
        try {
            PreparedStatement updateStmt = dbConn.prepareStatement(updateQuery);
            updateStmt.setString(1, newRole);
            updateStmt.setString(2, email);
            
            int rowsAffected = updateStmt.executeUpdate();
            updateStmt.close();
            
            if (rowsAffected > 0) {
                System.out.println("Successfully updated role for " + email + " to " + newRole);
                return true;
            } else {
                System.out.println("No rows updated for " + email);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error updating user role: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Gets all users from the database
     * @return List of all users
     */
    public List<UserModel> getAllUsers() {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }
        
        List<UserModel> users = new ArrayList<>();
        String selectQuery = "SELECT * FROM user";
        
        try (PreparedStatement selectStmt = dbConn.prepareStatement(selectQuery);
             ResultSet rs = selectStmt.executeQuery()) {
            
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setFirst_Name(rs.getString("First_Name"));
                user.setLast_Name(rs.getString("Last_Name"));
                user.setDOB(rs.getDate("DOB").toString());
                user.setGender(rs.getString("Gender"));
                user.setEmail(rs.getString("Email"));
                user.setMembership(rs.getString("Membership"));
                user.setAddress(rs.getString("Address"));
                user.setRole(rs.getString("Role"));
                user.setProfile_pic(rs.getString("Profile_pic"));
                // Not getting password for security reasons
                
                users.add(user);
            }
            
            return users;
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Gets user stats: total users, admin count, and member count
     * @return ResultSet containing stats data
     */
    public ResultSet getUserStats() {
        if (dbConn == null) {
            System.err.println("Database connection is not available.");
            return null;
        }
        
        String countQuery = "SELECT COUNT(*) AS total_users, " +
                           "COUNT(CASE WHEN Role = 'Admin' THEN 1 END) AS admin_count, " +
                           "COUNT(CASE WHEN Membership = 'Premium' THEN 1 END) AS member_count " +
                           "FROM user";
        
        try {
            PreparedStatement stmt = dbConn.prepareStatement(countQuery);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error getting user stats: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}