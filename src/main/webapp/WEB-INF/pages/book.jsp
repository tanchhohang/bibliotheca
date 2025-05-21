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
		<div class="book-genre">Genre: ${book.genre}</div><br>
		<div class="book-publisher">Published by: ${book.publisher}</div><br>

		<div class="book-description">
		<p>${book.description}</p>
		</div>

		<!-- Success message if book was borrowed successfully -->
		<c:if test="${param.borrowed == 'true'}">
		<div class="success-message">
		    Book has been successfully borrowed. Due date is in 14 days.
		</div>
		</c:if>
		
		<!-- Error messages -->
		<c:if test="${param.error == 'notavailable'}">
		<div class="error-message">
		    This book is not available for borrowing.
		</div>
		</c:if>
		
		<c:if test="${param.error == 'availability'}">
		<div class="error-message">
		    Failed to update book availability. Please try again.
		</div>
		</c:if>
		
		<c:if test="${param.error == 'borrow'}">
		<div class="error-message">
		    Failed to create borrow record. Please try again.
		</div>
		</c:if>

	<c:if test="${book.availability == 'Available'}">
		<form method="post" action="${pageContext.request.contextPath}/book">
		    <input type="hidden" name="id" value="${book.bookId}" />
		    <input type="hidden" name="action" value="borrow" />
		    <button type="submit" class="cta-button">Borrow Now</button>
		</form>
	</c:if>
</div>
</div>
</div>
</div>

<jsp:include page='footer.jsp'/>
<script src="${pageContext.request.contextPath}/js/book.js"></script>
</body>
</html>