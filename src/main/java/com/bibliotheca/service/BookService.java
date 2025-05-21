package com.bibliotheca.service;

import com.bibliotheca.model.BookModel;
import com.bibliotheca.config.DbConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private Connection dbConn;
    private boolean isConnectionError;

    public BookService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }

    public List<BookModel> getAllBooks() {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return new ArrayList<>();
        }

        List<BookModel> bookList = new ArrayList<>();
        String query = "SELECT * FROM books";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            System.out.println("Executing query: " + query);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                BookModel book = new BookModel();
                book.setBookId(result.getInt("Book_ID"));
                book.setBookName(result.getString("Book_Name"));
                book.setAuthor(result.getString("Author"));
                book.setGenre(result.getString("Genre"));
                book.setDescription(result.getString("Description"));
                book.setPublisher(result.getString("Publisher"));
                book.setAvailability(result.getString("Availability"));
                book.setBookImage(result.getString("Book_Image"));
                
                bookList.add(book);
            }
            
            System.out.println("Retrieved " + bookList.size() + " books from database");
            return bookList;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in getAllBooks: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public BookModel getBookById(int bookId) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return null;
        }
        
        String query = "SELECT * FROM books WHERE Book_ID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            ResultSet result = stmt.executeQuery();
            
            if (result.next()) {
                BookModel book = new BookModel();
                book.setBookId(result.getInt("Book_ID"));
                book.setBookName(result.getString("Book_Name"));
                book.setAuthor(result.getString("Author"));
                book.setGenre(result.getString("Genre"));
                book.setDescription(result.getString("Description"));
                book.setPublisher(result.getString("Publisher"));
                book.setAvailability(result.getString("Availability"));
                book.setBookImage(result.getString("Book_Image"));
                
                return book;
            }
            
            return null;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in getBookById: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public List<BookModel> getBooksByGenre(String genre) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return new ArrayList<>();
        }
        
        List<BookModel> bookList = new ArrayList<>();
        String query = "SELECT * FROM books WHERE Genre = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, genre);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                BookModel book = new BookModel();
                book.setBookId(result.getInt("Book_ID"));
                book.setBookName(result.getString("Book_Name"));
                book.setAuthor(result.getString("Author"));
                book.setGenre(result.getString("Genre"));
                book.setDescription(result.getString("Description"));
                book.setPublisher(result.getString("Publisher"));
                book.setAvailability(result.getString("Availability"));
                book.setBookImage(result.getString("Book_Image"));
                
                bookList.add(book);
            }
            
            return bookList;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in getBooksByGenre: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public List<BookModel> getBooksByAuthor(String author) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return new ArrayList<>();
        }
        
        List<BookModel> bookList = new ArrayList<>();
        String query = "SELECT * FROM books WHERE Author = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, author);
            ResultSet result = stmt.executeQuery();
            
            while (result.next()) {
                BookModel book = new BookModel();
                book.setBookId(result.getInt("Book_ID"));
                book.setBookName(result.getString("Book_Name"));
                book.setAuthor(result.getString("Author"));
                book.setGenre(result.getString("Genre"));
                book.setDescription(result.getString("Description"));
                book.setPublisher(result.getString("Publisher"));
                book.setAvailability(result.getString("Availability"));
                book.setBookImage(result.getString("Book_Image"));
                
                bookList.add(book);
            }
            
            return bookList;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in getBooksByAuthor: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    /**
     * Updates the availability status of a book
     * 
     * @param bookId The ID of the book to update
     * @param availability The new availability status (e.g., "Available", "Not Available")
     * @return true if update was successful, false otherwise
     */
    public boolean updateBookAvailability(int bookId, String availability) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return false;
        }
        
        String query = "UPDATE books SET Availability = ? WHERE Book_ID = ?";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, availability);
            stmt.setInt(2, bookId);
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Updated availability for book ID " + bookId + ": " + rowsAffected + " rows affected");
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in updateBookAvailability: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Adds a new book to the database
     * 
     * @param book The BookModel object containing book details
     * @return true if insertion was successful, false otherwise
     */
    public boolean addBook(BookModel book) {
        if (isConnectionError) {
            System.out.println("Database Connection Error!");
            return false;
        }
        
        String query = "INSERT INTO books (Book_Name, Author, Genre, Description, Publisher, Availability, Book_Image) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, book.getBookName());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setString(4, book.getDescription());
            stmt.setString(5, book.getPublisher());
            stmt.setString(6, book.getAvailability());
            stmt.setString(7, book.getBookImage());
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Added new book: " + book.getBookName() + ", rows affected: " + rowsAffected);
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("SQL Error in addBook: " + e.getMessage());
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