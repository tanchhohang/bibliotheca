package com.bibliotheca.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bibliotheca.config.DbConfig;
import com.bibliotheca.model.ReportModel;

/**
 * Service class for generating user reports based on user email
 */
public class UserReportService {
    
    /**
     * Generates a report for the user identified by email
     * 
     * @param email The email of the user
     * @return ReportModel containing report information
     * @throws ClassNotFoundException If database driver is not found
     * @throws SQLException If database operation fails
     */
    public ReportModel generateUserReport(String email) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement booksStatement = null;
        PreparedStatement paymentStatement = null;
        ResultSet userResultSet = null;
        ResultSet booksResultSet = null;
        ResultSet paymentResultSet = null;
        
        ReportModel report = new ReportModel();
        
        try {
            connection = DbConfig.getDbConnection();
            
            // Get user ID from email
            String userQuery = "SELECT User_ID FROM user WHERE Email = ?";
            userStatement = connection.prepareStatement(userQuery);
            userStatement.setString(1, email);
            userResultSet = userStatement.executeQuery();
            
            if (!userResultSet.next()) {
                throw new SQLException("User not found with email: " + email);
            }
            
            int userId = userResultSet.getInt("User_ID");
            
            // Count total books borrowed by user
            String booksQuery = "SELECT COUNT(*) as total FROM user_books WHERE User_ID = ?";
            booksStatement = connection.prepareStatement(booksQuery);
            booksStatement.setInt(1, userId);
            booksResultSet = booksStatement.executeQuery();
            
            int totalBooks = 0;
            if (booksResultSet.next()) {
                totalBooks = booksResultSet.getInt("total");
            }
            
            // Sum total fines/payments by user
            String paymentQuery = "SELECT SUM(CAST(Fine AS DECIMAL(10,2))) as total_payment FROM user_books WHERE User_ID = ? AND Fine IS NOT NULL";
            paymentStatement = connection.prepareStatement(paymentQuery);
            paymentStatement.setInt(1, userId);
            paymentResultSet = paymentStatement.executeQuery();
            
            double totalPayment = 0.0;
            if (paymentResultSet.next()) {
                totalPayment = paymentResultSet.getDouble("total_payment");
            }
            
            // Create the report
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            report.setReport_ID(0); // Will be set by database when saved
            report.setReport_Type("User Activity");
            report.setReport_Description("Books borrowed: " + totalBooks + ", Total payments: $" + totalPayment);
            report.setReport_Date(dateFormat.format(new Date()));
            report.setUser_ID(userId);
            
            // Create a custom attribute holder for JSP
            report.setBorrowedBooks(totalBooks);
            report.setTotalPayments(totalPayment);
            
            return report;
            
        } finally {
            // Close all resources
            if (paymentResultSet != null) try { paymentResultSet.close(); } catch (SQLException e) {}
            if (booksResultSet != null) try { booksResultSet.close(); } catch (SQLException e) {}
            if (userResultSet != null) try { userResultSet.close(); } catch (SQLException e) {}
            if (paymentStatement != null) try { paymentStatement.close(); } catch (SQLException e) {}
            if (booksStatement != null) try { booksStatement.close(); } catch (SQLException e) {}
            if (userStatement != null) try { userStatement.close(); } catch (SQLException e) {}
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
    }
    
    /**
     * Saves the generated report to the database
     * 
     * @param report The report to save
     * @return true if the report was saved successfully
     * @throws ClassNotFoundException If database driver is not found
     * @throws SQLException If database operation fails
     */
    public boolean saveReport(ReportModel report) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = DbConfig.getDbConnection();
            
            String query = "INSERT INTO report (Report_Type, Report_Description, Report_Date, User_ID) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, report.getReport_Type());
            statement.setString(2, report.getReport_Description());
            statement.setString(3, report.getReport_Date());
            statement.setInt(4, report.getUser_ID());
            
            int result = statement.executeUpdate();
            return result > 0;
            
        } finally {
            if (statement != null) try { statement.close(); } catch (SQLException e) {}
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
    }
}