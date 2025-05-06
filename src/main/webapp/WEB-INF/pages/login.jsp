<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - Login</title>
    
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>

	<jsp:include page="navbar.jsp" />

    <main>
        <div class="login-container">
            <h1>Login</h1>

            <form method="post" action="${pageContext.request.contextPath}/login">
                
                <!-- Email input field -->
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                
                <!-- Password input field with toggle visibility -->
                <div class="form-group">
                    <label for="password">Password</label>
                    <div class="password-field">
                        <input type="password" id="password" name="password" required>
                        <button type="button" class="password-toggle">
                            <svg id="eye-icon" xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>
                                <circle cx="12" cy="12" r="3"></circle>
                                <line id="eye-slash" x1="3" y1="3" x2="21" y2="21"></line>
                            </svg>
                        </button>
                    </div>
                </div>

                <c:if test="${not empty error}">
                    <div class="error-message">${error}</div>
                </c:if>
                
                <!-- Submit button -->
                <button type="submit" class="sign-in-btn">Sign In</button>
                <div class="sign-up-link">
                    <p>Don't have an account? <a href="${pageContext.request.contextPath}/register">Register</a></p> 
                </div>
            </form>
        </div>
    </main>

	<jsp:include page="footer.jsp" />
    <script src="${pageContext.request.contextPath}/js/login.js"></script>
</body>
</html>
