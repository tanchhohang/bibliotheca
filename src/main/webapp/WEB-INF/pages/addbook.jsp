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
                <label for="firstName">Book Title:</label>
                <input type="text" id="firstName" name="firstName" value="${bookName}" required>
            </div>
            <div class="col">
                <label for="lastName">Author:</label>
                <input type="text" id="lastName" name="lastName" value="${author}" required>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label for="dob">Description:</label>
                <input type="text" id="desc" name="dob" value="${description}" required>
            </div>
            <div class="col">
                <label for="gender">Genre:</label>
                <input type="text" id="lastName" name="lastName" value="${genre}" required>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label for="email">Publisher:</label>
                <input type="email" id="email" name="email" value="${publisher}" required>
            </div>
            <div class="col">
                <label for="availability">Availability:</label>
                <select id="availability" name="availability">
                    <option value="available" <c:if test="${Availability == 'Available'}">selected</c:if>>Available</option>
                    <option value="notAvailable" <c:if test="${Availability == 'Not Available'}">selected</c:if>>Not Available</option>
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