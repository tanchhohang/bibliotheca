<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bibliotheca - Book Edit</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-book-edit.css">

</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">
<div class="main-content-wrapper">

<div class="main-content">
<!-- Book cover image container with background effect -->
<div class="book-image-container">
    <!-- Placeholder for book cover image -->
    <img class="book-image" id="bookCover" src="${pageContext.request.contextPath}/resources/images/${book.bookImage}" alt="${book.bookName} Cover">
    
    <!-- Change Cover Button -->
    <div class="cover-change-button">
        <label for="coverUpload" class="upload-btn">Change Cover</label>
        <input type="file" id="coverUpload" name="coverUpload" accept="image/*" style="display: none;">
    </div>
</div>

<!-- Book information and edit form -->
<div class="book-info-container">
    <!-- Availability status dropdown -->
    <div class="status-dropdown">
        <button id="statusButton" class="status-button">
            Not Available
            <svg width="10" height="6" viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M1 1L5 5L9 1" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
        </button>
        <div id="statusDropdown" class="dropdown-content">
            <a href="#" onclick="changeStatus('Available')">Available</a>
            <a href="#" onclick="changeStatus('Not Available')">Not Available</a>
        </div>
    </div>

    <!-- Book title input field -->
    <div class="form-group">
        <label class="form-label">Title:</label>
        <input type="text" class="form-input" placeholder="Book Title" value="${book.bookName}">
    </div>

    <!-- Author input field -->
    <div class="form-group">
        <label class="form-label">Author:</label>
        <input type="text" class="form-input" placeholder="Author Name" value="${book.author}">
    </div>

    <!-- Book description textarea -->
    <div class="form-group">
        <label class="form-label">Book Description:</label>
        <textarea class="form-textarea" placeholder="Summary about the book">${book.description}</textarea>
    </div>

    <!-- Delete button -->
    <a href="${pageContext.request.contextPath}/"><button class="action-button">Confirm</button></a>
</div>
</div>
</div>
</div>

<jsp:include page="footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/admin_book_edit.js"></script>


</body>
</html>