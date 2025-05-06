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
                    <img class="book-image" src="${pageContext.request.contextPath}/resources/images/nocountry.png" alt="No Country For Old Men Book Cover">
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
                        <input type="text" class="form-input" placeholder="Book Title" value="No Country For Old Men">
                    </div>
                    
                    <!-- Author input field -->
                    <div class="form-group">
                        <label class="form-label">Author:</label>
                        <input type="text" class="form-input" placeholder="Author Name" value="Cormac McCarthy">
                    </div>
                    
                    <!-- Book description textarea -->
                    <div class="form-group">
                        <label class="form-label">Book Description:</label>
                        <textarea class="form-textarea" placeholder="Summary about the book">No Country for Old Men is a 2005 novel by American author Cormac McCarthy. The story follows the interweaving paths of three main characters set in motion by a drug deal gone bad near the Mexican-American border in southwest Texas. It explores themes of fate, conscience, and circumstance in the modern American West.</textarea>
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