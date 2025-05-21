package com.bibliotheca.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bibliotheca.model.BookModel;
import com.bibliotheca.model.UserBookModel;
import com.bibliotheca.model.UserModel;
import com.bibliotheca.service.ProfileService;
import com.bibliotheca.util.CookieUtil;

/**
 * Author Sameep Karki
 * Servlet implementation class UserProfileController
 */
@WebServlet(urlPatterns = { "/userprofile" })
public class UserProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ProfileService profileService = new ProfileService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = getUserEmailFromCookie(request, response);
        if (userEmail == null) return;

        UserModel user = profileService.getUserByEmail(userEmail);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("user", user);

        // Set book count
        int bookCount = profileService.getUserBookCount(userEmail);
        request.setAttribute("bookCount", bookCount);

        // Set current book details
        Map<String, Object> currentBookDetails = profileService.getCurrentBookDetails(userEmail);
        if (!currentBookDetails.isEmpty()) {
            request.setAttribute("currentUserBook", currentBookDetails.get("userBook"));
            request.setAttribute("currentBook", currentBookDetails.get("book"));
        }

        // Set borrow history
        List<Map<String, Object>> borrowHistoryDetails = profileService.getBorrowHistoryWithDetails(userEmail);
        request.setAttribute("borrowHistoryDetails", borrowHistoryDetails);

        // Set user books and associated book details
        List<UserBookModel> userBooks = profileService.getUserBookHistory(userEmail);
        request.setAttribute("userBooks", userBooks);

        List<BookModel> books = new ArrayList<>();
        for (UserBookModel userBook : userBooks) {
            BookModel book = profileService.getBookById(userBook.getBook_ID());
            if (book != null) books.add(book);
        }
        request.setAttribute("books", books);

        request.getRequestDispatcher("/WEB-INF/pages/user-profile.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String getUserEmailFromCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie roleCookie = CookieUtil.getCookie(request, "role");
        if (roleCookie == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return null;
        }

        String role = roleCookie.getValue();
        if ("Admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/adminprofile");
            return null;
        }

        Cookie emailCookie = CookieUtil.getCookie(request, "email");
        return (emailCookie != null) ? emailCookie.getValue() : null;
    }
}