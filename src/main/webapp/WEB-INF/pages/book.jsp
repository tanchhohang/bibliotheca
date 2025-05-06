<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - Book</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/book.css" />
</head>

<body>
    <jsp:include page='navbar.jsp'/>

    <div class="container">
        <div class="main-content-wrapper">
            <div class="main-content">
                <!-- Book image -->
                <div class="book-image-container">
                    <img class="book-image"
                         src="${pageContext.request.contextPath}/resources/images/${book.bookImage}"
                         alt="${book.bookName} Cover">
                </div>

                <!-- Book information -->
                <div class="book-info-container">
                    <div class="status-badge">${book.availability}</div>
                    <h1 class="book-title">${book.bookName}</h1>
                    <h2 class="book-author">by ${book.author}</h2>
                    <div class="book-genre">Genre: ${book.genre}</div>
                    <div class="book-publisher">Published by: ${book.publisher}</div>

                    <div class="book-description">
                        <p>${book.description}</p>
                    </div>

                    <c:if test="${book.availability == 'Available'}">
                        <button class="cta-button">Borrow Now</button>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page='footer.jsp'/>
    <script src="${pageContext.request.contextPath}/js/book.js"></script>
</body>
</html>
