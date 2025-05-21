package com.bibliotheca.service;

import com.bibliotheca.config.DbConfig;
import com.bibliotheca.model.UserBookModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBookService {
    
    private Connection dbConn;
    private boolean isConnectionError;
    
    public UserBookService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }
    
    /**
     * Creates a new borrow record when a user borrows a book
     * 
     * @param userBook The UserBookModel containing borrow details
     * @return true if record was successfully created, false otherwise
     */
    public boolean createBorrowRecord(UserBookModel userBook) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return false;
        }
        
        String query = "INSERT INTO user_books (User_ID, Book_ID, Borrowed_Date, Due_Date) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userBook.getUser_ID());
            stmt.setInt(2, userBook.getBook_ID());
            stmt.setString(3, userBook.getBorrowed_Date());
            stmt.setString(4, userBook.getDue_Date());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Created borrow record: " + rowsAffected + " rows affected");
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in createBorrowRecord: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Gets all books borrowed by a specific user
     * 
     * @param userId The ID of the user
     * @return List of UserBookModel objects representing borrowed books
     */
    public List<UserBookModel> getBorrowedBooksByUser(int userId) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return new ArrayList<>();
        }
        
        List<UserBookModel> borrowedBooks = new ArrayList<>();
        String query = "SELECT * FROM user_books WHERE User_ID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                UserBookModel userBook = new UserBookModel();
                userBook.setUser_ID(result.getInt("User_ID"));
                userBook.setBook_ID(result.getInt("Book_ID"));
                userBook.setBorrowed_Date(result.getString("Borrowed_Date"));
                userBook.setDue_Date(result.getString("Due_Date"));
                userBook.setReturn_Date(result.getString("Return_Date"));
                userBook.setFine(result.getString("Fine"));
                
                borrowedBooks.add(userBook);
            }
            
            return borrowedBooks;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in getBorrowedBooksByUser: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Records a book return
     * 
     * @param userId The ID of the user returning the book
     * @param bookId The ID of the book being returned
     * @param returnDate The date the book was returned
     * @param fine The fine amount if applicable
     * @return true if return was recorded successfully, false otherwise
     */
    public boolean returnBook(int userId, int bookId, String returnDate, String fine) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return false;
        }
        
        String query = "UPDATE user_books SET Return_Date = ?, Fine = ? " +
                       "WHERE User_ID = ? AND Book_ID = ? AND Return_Date IS NULL";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, returnDate);
            stmt.setString(2, "0");
            stmt.setInt(3, userId);
            stmt.setInt(4, bookId);
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Updated return for book ID " + bookId + ": " + rowsAffected + " rows affected");
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in returnBook: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Checks if a user has already borrowed a specific book that has not been returned
     * 
     * @param userId The ID of the user
     * @param bookId The ID of the book
     * @return true if the user has an active borrow record for the book, false otherwise
     */
    public boolean isBookBorrowedByUser(int userId, int bookId) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return false;
        }
        
        String query = "SELECT COUNT(*) FROM user_books WHERE User_ID = ? AND Book_ID = ? AND Return_Date IS NULL";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            ResultSet result = stmt.executeQuery();
            
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in isBookBorrowedByUser: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    public void closeConnection() {
        if (dbConn != null) {
            try {
                dbConn.close();
                System.out.println("Database connection closed");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}