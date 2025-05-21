<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - No Country For Old Men</title>
    <!-- Link to external CSS and JS file -->
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-book.css">
    
</head>
<body>

    <jsp:include page='navbar.jsp'/>

    <div class="container">
        <div class="main-content-wrapper">
           
            <div class="main-content">
                <div class="book-image-container">
                    <img class="book-image" src="${pageContext.request.contextPath}/resources/images/${book.bookImage}" alt="${book.bookName}">
                </div>
                
                <div class="book-info-container">
                    <!-- Book availability status -->
                    <div class="status-dropdown">
                        <div id="statusBadge" class="status-badge">
                            Not Available
                            <span class="dropdown-arrow"></span>
                        </div>
                        <div id="statusDropdown" class="status-dropdown-content">
                            <a href="#" onclick="changeStatus('available'); return false;">Available</a>
                            <a href="#" onclick="changeStatus('not-available'); return false;">Not Available</a>
                        </div>
                    </div>
                    
                    <!-- Book information -->
                    <h1 class="book-title">${book.bookName} </h1>
                    <h2 class="book-author">${book.author} </h2>
                    <div class="book-rating">Average Rating: 8.9/10</div>
                    
                    <!-- Book description -->
                    <p class="book-description">${book.description}</p>
                    
                    <!-- Action buttons -->
                    <div class="buttons-container">
                        <a href="${pageContext.request.contextPath}/adminbookedit?id=${book.bookId}"><button class="action-button edit-button">Edit</button></a>
                        <button class="action-button delete-button">Delete</button>
                    </div>
                    
                    <a href="${pageContext.request.contextPath}/adminbooklog?id=${book.bookId}"><button class="action-button borrow-button">Log Borrowed</button></a>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page='footer.jsp'/>
    
    <script src="${pageContext.request.contextPath}/js/admin-book-log.js"></script>
    
</body>
</html>