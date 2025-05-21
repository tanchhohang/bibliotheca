<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bibliotheca - Add Book</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addbook.css" />
</head>
<body>

<jsp:include page="navbar.jsp" />

<main>
<div class="login-box">
<h2>Add Book</h2>

<c:if test="${not empty error}">
<div class="error-message">${error}</div>
</c:if>

<form action="${pageContext.request.contextPath}/addbook" method="post" enctype="multipart/form-data">
	<div class="row">
		<div class="col">
			<label for="bookName">Book Title:</label>
			<input type="text" id="bookName" name="bookName" value="${bookName}" required>
		</div>
		<div class="col">
			<label for="author">Author:</label>
			<input type="text" id="author" name="author" value="${author}" required>
		</div>
	</div>

	<div class="row">
		<div class="col">
			<label for="description">Description:</label>
				<input type="text" id="description" name="description" value="${description}" required>
			</div>
			<div class="col">
				<label for="genre">Genre:</label>
			<input type="text" id="genre" name="genre" value="${genre}" required>
		</div>
	</div>

	<div class="row">
		<div class="col">
			<label for="publisher">Publisher:</label>
			<input type="text" id="publisher" name="publisher" value="${publisher}" required>
		</div>
		<div class="col">
			<label for="availability">Availability:</label>
			<select id="availability" name="availability">
			<option value="available" <c:if test="${availability == 'Available'}">selected</c:if>>Available</option>
			<option value="notAvailable" <c:if test="${availability == 'Not Available'}">selected</c:if>>Not Available</option>
			</select>
		</div>
	</div>

	<div class="row">
		<div class="col">
			<label for="image">Book Cover:</label>
			<input type="file" id="image" name="image" accept="image/*">
		</div>
</div>

<button type="submit" class="register-button">Add Book</button>
</form>
</div>
</main>

<jsp:include page="footer.jsp" />
</body>
</html>