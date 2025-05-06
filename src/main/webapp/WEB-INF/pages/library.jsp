<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - Library</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/library.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Instrument+Serif:ital@0;1&display=swap" rel="stylesheet">
</head>
<body>

<%-- Debugging Logs (Optional, remove in production) --%>
<%
System.out.println("JSP PAGE LOADED");
if (request.getAttribute("books") != null) {
    System.out.println("Books attribute exists: " + ((java.util.List)request.getAttribute("books")).size() + " books");
} else {
    System.out.println("Books attribute is NULL");
}
%>

<jsp:include page="navbar.jsp" />

<div class="hero-container">
    <div class="hero"></div>
    <div class="banner-dots">
        <div class="dot active"></div>
        <div class="dot"></div>
        <div class="dot"></div>
    </div>
</div>

<div class="navigation">
    <div class="nav-items-container">
        <div class="nav-item">GENRE</div>
        <div class="nav-item">AUTHOR</div>
        <div class="nav-item">BOOKS</div>
    </div>
    <div class="search-container">
    <form method="get" class="search-bar" action="${pageContext.request.contextPath}/library">
        <input type="text" class="search-input" name="query" placeholder="Search by title or author..." value="${param.query}" />
        <button type="submit" class="search-button"><i class="fas fa-search"></i> Search</button>
    </form>
</div>
</div>

<div class="books-container">
    <c:choose>
        <c:when test="${not empty books}">
            <c:set var="booksPerRow" value="5" />
            <c:forEach var="book" items="${books}" varStatus="status">
                <c:if test="${status.index % booksPerRow == 0}">
                    <div class="book-row">
                </c:if>

                <div class="book-card">
                    <div class="book-cover-container">
                        <a href="${pageContext.request.contextPath}/book?id=${book.bookId}">
                            <c:choose>
                                <c:when test="${not empty book.bookImage}">
                                    <img src="${pageContext.request.contextPath}/resources/images/${book.bookImage}"
                                         alt="${book.bookName}" class="book-cover">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/resources/images/norwegian.png"
                                         alt="${book.bookName}" class="book-cover">
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </div>
                    <div class="book-info">
                        <div class="book-title">
                            <a href="${pageContext.request.contextPath}/book?id=${book.bookId}">${book.bookName}</a>
                        </div>
                        <div class="book-author">${book.author}</div>
                        <div class="book-desc">${book.description}</div>
                    </div>
                </div>

                <c:if test="${(status.index + 1) % booksPerRow == 0 || status.last}">
                    </div> <!-- Close book-row -->
                </c:if>
            </c:forEach>
        </c:when>

        <c:otherwise>
            <div class="no-books-message">
                <p>No books found</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>
