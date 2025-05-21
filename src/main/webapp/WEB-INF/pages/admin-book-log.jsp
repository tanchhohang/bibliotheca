<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - Book Log</title>
    <!-- Link to external CSS and JS file -->
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-book-log.css">

</head>
<body>
	
	<jsp:include page='navbar.jsp'/>

    <div class="container">

        
        <!-- Main content section with book details -->
        <div class="main-content-wrapper">
            <!-- background element -->
            
            <div class="main-content">
                <!-- Book cover image container with background shape -->
                <div class="book-image-container">
                
                    <img class="book-image" src="${pageContext.request.contextPath}/resources/images/${book.bookImage}" alt="${book.bookName} Cover">
                </div>
                
                <!-- Book information and form controls -->
                <div class="book-info-container">
                    <!-- Status dropdown with availability indicator -->
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
                    
                    <!-- Book title and author -->
                    <h1 class="book-title">${book.bookName}</h1>${book.bookId}
                    <h2 class="book-author">by ${book.author}</h2>
                    
                    <!-- Borrowing form fields -->
                    <form method="post" action="${pageContext.request.contextPath}/adminbooklog">
					    <input type="hidden" name="action" value="borrow" />
					    <input type="hidden" name="bookId" value="" />
					
					    <div class="form-row">
					        <div class="form-group">
					            <label>Borrowed By:</label>
					            <input type="text" name="userId" placeholder="User ID" required>
					        </div>
					        <div class="form-group">
					            <label>Borrowed Date:</label>
					            <input type="date" name="borrowedDate" placeholder="dd/mm/yy" required>
					        </div>
					    </div>
					
					    <div class="form-row">
					        <div class="form-group">
					            <label>Due Date:</label>
					            <input type="date" name="dueDate" placeholder="dd/mm/yy" required>
					        </div>
					        <div class="form-group">
					            <label>Return Date:</label>
					            <input type="date" name="returnDate" placeholder="dd/mm/yy">
					        </div>
					    </div>
					
					    <!-- Action buttons for logging book status -->
					    <div class="form-row">
					        <button type="submit" name="action" value="return" class="action-button return-button">Log Returned</button>
					        <button type="submit" name="action" value="borrow" class="action-button borrow-button">Log Book</button>
					    </div>
					</form>
				</div>
            </div>
        </div>
        
    </div>
    
    <jsp:include page='footer.jsp'/>
    
    <script src="${pageContext.request.contextPath}/js/admin-book-log.js"></script>

</body>
</html>