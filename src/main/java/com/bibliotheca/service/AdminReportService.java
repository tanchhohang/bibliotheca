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
 * Service class for generating admin reports with library statistics
 */
public class AdminReportService {
    
    /**
     * Generates a comprehensive admin report with library statistics
     * 
     * @return ReportModel containing report information
     * @throws ClassNotFoundException If database driver is not found
     * @throws SQLException If database operation fails
     */
    public ReportModel generateAdminReport() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement booksStatement = null;
        PreparedStatement usersStatement = null;
        PreparedStatement revenueStatement = null;
        PreparedStatement borrowedStatement = null;
        PreparedStatement finesStatement = null;
        ResultSet booksResultSet = null;
        ResultSet usersResultSet = null;
        ResultSet revenueResultSet = null;
        ResultSet borrowedResultSet = null;
        ResultSet finesResultSet = null;
        
        ReportModel report = new ReportModel();
        
        try {
            connection = DbConfig.getDbConnection();
            
            // Get total books count
            String booksQuery = "SELECT COUNT(*) as total FROM books";
            booksStatement = connection.prepareStatement(booksQuery);
            booksResultSet = booksStatement.executeQuery();
            
            int totalBooks = 0;
            if (booksResultSet.next()) {
                totalBooks = booksResultSet.getInt("total");
            }
            
            // Get total users count
            String usersQuery = "SELECT COUNT(*) as total FROM user";
            usersStatement = connection.prepareStatement(usersQuery);
            usersResultSet = usersStatement.executeQuery();
            
            int totalUsers = 0;
            if (usersResultSet.next()) {
                totalUsers = usersResultSet.getInt("total");
            }
            
            // Get monthly revenue (sum of fines from current month)
            String revenueQuery = "SELECT SUM(CAST(Fine AS DECIMAL(10,2))) as monthly_revenue FROM user_books WHERE " +
                                "Borrowed_Date >= DATE_FORMAT(CURRENT_DATE(), '%Y-%m-01') AND " +
                                "Fine IS NOT NULL";
            revenueStatement = connection.prepareStatement(revenueQuery);
            revenueResultSet = revenueStatement.executeQuery();
            
            double monthlyRevenue = 0.0;
            if (revenueResultSet.next()) {
                monthlyRevenue = revenueResultSet.getDouble("monthly_revenue");
            }
            
            // Get currently borrowed books count
            String borrowedQuery = "SELECT COUNT(*) as total FROM user_books WHERE Return_Date IS NULL";
            borrowedStatement = connection.prepareStatement(borrowedQuery);
            borrowedResultSet = borrowedStatement.executeQuery();
            
            int currentlyBorrowed = 0;
            if (borrowedResultSet.next()) {
                currentlyBorrowed = borrowedResultSet.getInt("total");
            }
            
            // Get count of users with outstanding fines
            String finesQuery = "SELECT COUNT(DISTINCT User_ID) as users_with_fines FROM user_books " +
                              "WHERE Fine IS NOT NULL AND CAST(Fine AS DECIMAL(10,2)) > 0";
            finesStatement = connection.prepareStatement(finesQuery);
            finesResultSet = finesStatement.executeQuery();
            
            int usersWithFines = 0;
            if (finesResultSet.next()) {
                usersWithFines = finesResultSet.getInt("users_with_fines");
            }
            
            // Create the report
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            report.setReport_ID(0); // Will be set by database when saved
            report.setReport_Type("Admin Dashboard");
            report.setReport_Description("Library statistics report");
            report.setReport_Date(dateFormat.format(new Date()));
            report.setUser_ID(0); // Not associated with specific user
            
            // Set admin-specific fields
            report.setTotalBooks(totalBooks);
            report.setTotalUsers(totalUsers);
            report.setMonthlyRevenue(monthlyRevenue);
            report.setCurrentlyBorrowed(currentlyBorrowed);
            report.setUsersWithFines(usersWithFines);
            
            return report;
            
        } finally {
            // Close all resources
            if (finesResultSet != null) try { finesResultSet.close(); } catch (SQLException e) {}
            if (borrowedResultSet != null) try { borrowedResultSet.close(); } catch (SQLException e) {}
            if (revenueResultSet != null) try { revenueResultSet.close(); } catch (SQLException e) {}
            if (usersResultSet != null) try { usersResultSet.close(); } catch (SQLException e) {}
            if (booksResultSet != null) try { booksResultSet.close(); } catch (SQLException e) {}
            if (finesStatement != null) try { finesStatement.close(); } catch (SQLException e) {}
            if (borrowedStatement != null) try { borrowedStatement.close(); } catch (SQLException e) {}
            if (revenueStatement != null) try { revenueStatement.close(); } catch (SQLException e) {}
            if (usersStatement != null) try { usersStatement.close(); } catch (SQLException e) {}
            if (booksStatement != null) try { booksStatement.close(); } catch (SQLException e) {}
            if (connection != null) try { connection.close(); } catch (SQLException e) {}
        }
    }
    
    /**
     * Saves the generated admin report to the database
     * 
     * @param report The report to save
     * @return true if the report was saved successfully
     * @throws ClassNotFoundException If database driver is not found
     * @throws SQLException If database operation fails
     */
//    public boolean saveReport(ReportModel report) throws ClassNotFoundException, SQLException {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        
//        try {
//            connection = DbConfig.getDbConnection();
//            System.out.println("Saving report for User ID: " + report.getUser_ID());
//            String query = "INSERT INTO report (Report_Type, Report_Description, Report_Date, User_ID) VALUES (?, ?, ?, ?)";
//            statement = connection.prepareStatement(query);
//            statement.setString(1, report.getReport_Type());
//            statement.setString(2, report.getReport_Description());
//            statement.setString(3, report.getReport_Date());
//            statement.setInt(4, report.getUser_ID());
//            
//            int result = statement.executeUpdate();
//            return result > 0;
//            
//        } finally {
//            if (statement != null) try { statement.close(); } catch (SQLException e) {}
//            if (connection != null) try { connection.close(); } catch (SQLException e) {}
//        }
//    }
}