package com.bibliotheca.controller;

import com.bibliotheca.model.BookModel;
import com.bibliotheca.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/library")
public class LibraryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        System.out.println("LibraryController: Processing GET request");

        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }

        String query = request.getParameter("query");
        BookService bookService = new BookService();
        List<BookModel> books = new ArrayList<>();

        try {
            switch (action) {
                case "genre":
//                    String genre = request.getParameter("genre");
//                    if (genre != null && !genre.trim().isEmpty()) {
//                        books = bookService.getBooksByGenre(genre);
//                        System.out.println("Fetched books by genre: " + genre + ", count: " + books.size());
//                    } else {
//                        books = bookService.getAllBooks();
//                        System.out.println("Genre parameter empty, fetched all books");
//                    }
//                    break;

                case "author":
                    String author = request.getParameter("author");
                    if (author != null && !author.trim().isEmpty()) {
                        books = bookService.getBooksByAuthor(author);
                        System.out.println("Fetched books by author: " + author + ", count: " + books.size());
                    } else {
                        books = bookService.getAllBooks();
                        System.out.println("Author parameter empty, fetched all books");
                    }
                    break;

                case "list":
                default:
                    books = bookService.getAllBooks();
                    System.out.println("Fetched all books, count: " + books.size());
                    break;
            }

            // Filter by search query
            if (query != null && !query.trim().isEmpty()) {
                String lowerQuery = query.toLowerCase();
                books = books.stream()
                             .filter(book -> book.getBookName().toLowerCase().contains(lowerQuery)
                                          || book.getAuthor().toLowerCase().contains(lowerQuery))
                             .collect(Collectors.toList());
                System.out.println("Filtered books by query: '" + query + "', result count: " + books.size());
            }

            // Set attribute and forward
            request.setAttribute("books", books);
            request.getRequestDispatcher("/WEB-INF/pages/library.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error in LibraryController: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve books");
        } finally {
            bookService.closeConnection();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	doGet(request, response);
    }
}