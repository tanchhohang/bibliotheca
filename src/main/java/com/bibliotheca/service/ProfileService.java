package com.bibliotheca.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bibliotheca.config.DbConfig;
import com.bibliotheca.model.BookModel;
import com.bibliotheca.model.UserBookModel;
import com.bibliotheca.model.UserModel;

public class ProfileService {
    
    private Connection dbConn;
    
    public ProfileService() {
        try {
            this.dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println("Database connection error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public UserModel getUserByEmail(String email) {
        if (dbConn == null) {
            return null;
        }
        
        String query = "SELECT * FROM user WHERE Email = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserModel user = new UserModel();
                    user.setFirstName(rs.getString("First_Name"));
                    user.setLastName(rs.getString("Last_Name"));
                    user.setEmail(rs.getString("Email"));
                    user.setDob(rs.getString("DOB"));
                    user.setGender(rs.getString("Gender"));
                    user.setMembership(rs.getString("Membership"));
                    user.setAddress(rs.getString("Address"));
                    user.setPassword(rs.getString("Password"));
                    user.setRole(rs.getString("Role"));
                    user.setProfilePic(rs.getString("Profile_pic"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user by email: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    public int getUserBookCount(String userEmail) {
        if (dbConn == null) {
            return 0;
        }
        
        String query = "SELECT COUNT(*) as count FROM user_books ub JOIN user u ON ub.User_ID = u.User_ID WHERE u.Email = ? AND ub.Return_Date IS NULL";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, userEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user book count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    /**
     * Get the current borrowed book details for a user
     * @param userEmail The user's email
     * @return Map containing both UserBookModel and BookModel for the current borrowed book
     */
    public Map<String, Object> getCurrentBookDetails(String userEmail) {
        Map<String, Object> result = new HashMap<>();
        
        if (dbConn == null) {
            return result;
        }
        
        // First get user ID from email
        int userId = getUserIdByEmail(userEmail);
        if (userId == 0) {
            return result;
        }
        
        // Get the current book borrowing record
        String userBookQuery = "SELECT * FROM user_books WHERE User_ID = ? AND Return_Date IS NULL LIMIT 1";
        try (PreparedStatement stmt = dbConn.prepareStatement(userBookQuery)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UserBookModel userBook = new UserBookModel();
                    userBook.setUser_ID(rs.getInt("User_ID"));
                    userBook.setBook_ID(rs.getInt("Book_ID"));
                    userBook.setBorrowed_Date(rs.getString("Borrowed_Date"));
                    userBook.setDue_Date(rs.getString("Due_Date"));
                    userBook.setReturn_Date(rs.getString("Return_Date"));
                    userBook.setFine(rs.getString("Fine"));
                    
                    result.put("userBook", userBook);
                    
                    // Now get the book details
                    BookModel book = getBookById(userBook.getBook_ID());
                    if (book != null) {
                        result.put("book", book);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting current book: " + e.getMessage());
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Get user ID from email
     * @param email User's email
     * @return User ID, or 0 if not found
     */
    private int getUserIdByEmail(String email) {
        if (dbConn == null) {
            return 0;
        }
        
        String query = "SELECT User_ID FROM user WHERE Email = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("User_ID");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user ID by email: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    /**
     * Get all books borrowed by a user with their complete details
     * @param userEmail The user's email
     * @return List of maps, each containing UserBookModel and BookModel
     */
    public List<Map<String, Object>> getBorrowHistoryWithDetails(String userEmail) {
        List<Map<String, Object>> borrowHistory = new ArrayList<>();
        
        if (dbConn == null) {
            return borrowHistory;
        }
        
        // First get all user books
        List<UserBookModel> userBooks = getUserBookHistory(userEmail);
        
        // For each user book, get book details and create a combined map
        for (UserBookModel userBook : userBooks) {
            BookModel book = getBookById(userBook.getBook_ID());
            if (book != null) {
                Map<String, Object> bookDetails = new HashMap<>();
                bookDetails.put("userBook", userBook);
                bookDetails.put("book", book);
                borrowHistory.add(bookDetails);
            }
        }
        
        return borrowHistory;
    }
    
    public List<UserBookModel> getUserBookHistory(String userEmail) {
        List<UserBookModel> userBooks = new ArrayList<>();
        
        if (dbConn == null) {
            return userBooks;
        }
        
        // First get user ID from email
        int userId = getUserIdByEmail(userEmail);
        if (userId == 0) {
            return userBooks;
        }
        
        String query = "SELECT * FROM user_books WHERE User_ID = ? ORDER BY Borrowed_Date DESC";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserBookModel userBook = new UserBookModel();
                    userBook.setUser_ID(rs.getInt("User_ID"));
                    userBook.setBook_ID(rs.getInt("Book_ID"));
                    userBook.setBorrowed_Date(rs.getString("Borrowed_Date"));
                    userBook.setDue_Date(rs.getString("Due_Date"));
                    userBook.setReturn_Date(rs.getString("Return_Date"));
                    userBook.setFine(rs.getString("Fine"));
                    userBooks.add(userBook);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user books: " + e.getMessage());
            e.printStackTrace();
        }
        
        return userBooks;
    }
    
    public BookModel getBookById(int bookId) {
        if (dbConn == null) {
            return null;
        }
        
        String query = "SELECT * FROM books WHERE Book_ID = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BookModel book = new BookModel();
                    book.setBookId(rs.getInt("Book_ID"));
                    book.setBookName(rs.getString("Book_Name"));
                    book.setAuthor(rs.getString("Author"));
                    book.setGenre(rs.getString("Genre"));
                    book.setDescription(rs.getString("Description"));
                    book.setPublisher(rs.getString("Publisher"));
                    book.setAvailability(rs.getString("Availability"));
                    book.setBookImage(rs.getString("Book_Image"));
                    return book;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting book by id: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    public int getTotalMembers() {
        if (dbConn == null) {
            return 0;
        }
        
        String query = "SELECT COUNT(*) as count FROM user";
        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error getting total members: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public int getTotalBooks() {
        if (dbConn == null) {
            return 0;
        }
        
        String query = "SELECT COUNT(*) as count FROM books";
        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error getting total books: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public int getTotalBorrowed() {
        if (dbConn == null) {
            return 0;
        }
        
        String query = "SELECT COUNT(*) as count FROM user_books WHERE Return_Date IS NULL";
        try (PreparedStatement stmt = dbConn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            System.err.println("Error getting total borrowed: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}