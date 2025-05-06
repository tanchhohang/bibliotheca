<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bibliotheca - Edit Profile</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css" />
</head>
<body>

<jsp:include page="navbar.jsp" />

<main>
<div class="login-box">
    <h2>Edit Profile</h2>
    
    <c:if test="${not empty error}">
    	<div class="error-message">${error}</div>
	</c:if>

    <form action="${pageContext.request.contextPath}/editprofile" method="post" enctype="multipart/form-data">
        <div class="row">
            <div class="col">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" value="${First_Name}">
            </div>
            <div class="col">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" value="${Last_Name}">
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" value="${DOB}" >
            </div>
            <div class="col">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" >
                    <option value="" disabled <c:if test="${empty Gender}">selected</c:if>>Select gender</option>
                    <option value="male" <c:if test="${Gender == 'male'}">selected</c:if>>Male</option>
                    <option value="female" <c:if test="${Gender == 'female'}">selected</c:if>>Female</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label for="email">Email:</label>
                <input type="email" name="email" value="${Email}" readonly />
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${Address}" >
            </div>
            <div class="col">
                <label for="membership">Membership:</label>
                <select id="membership" name="membership" required>
                    <option value="" disabled <c:if test="${empty Membership}">selected</c:if>>Select membership type</option>
                    <option value="monthly" <c:if test="${Membership == 'monthly'}">selected</c:if>>Monthly</option>
                    <option value="yearly" <c:if test="${Membership == 'yearly'}">selected</c:if>>Yearly</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label for="password">New Password:</label>
                <input type="password" id="password" name="password"  autocomplete="off" placeholder ='Must be at least 8 characters with 1 upper-case, 1 number, and 1 symbol'>
            </div>
            <div class="col">
                <label for="retypePassword">Confirm New Password:</label>
                <input type="password" id="retypePassword" name="retypePassword"  autocomplete="off">
            </div>
        </div>

        <div class="row">
            <div class="col">
                <label for="image">New Profile Picture:</label>
                <input type="file" id="image" name="image" accept="image/*">
            </div>
        </div>

        <button type="submit" class="register-button">Submit</button>
    </form>
</div>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>
