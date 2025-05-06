<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bibliotheca - User Management</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user-management.css" />

</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="top_bg">
</div>
<!-- Main content section -->
<div class="container">
    <!-- Restructured header section with title and stats side by side -->
    <div class="header_section">
        <div class="title_container">
            <h1 class="page_title">User Management</h1>
            <p class="page_subtitle">List of all users</p>
        </div>
        
        <!-- Stats summary moved to header -->
        <div class="stats_container">
            <div class="stat_box">
                <div class="stat_label">Total Users</div>
                <div class="stat_value">${totalUsers}</div>
            </div>
            <div class="stat_box">
                <div class="stat_label">Members</div>
                <div class="stat_value">${memberCount}</div>
            </div>
            <div class="stat_box">
                <div class="stat_label">Admins</div>
                <div class="stat_value">${adminCount}</div>
            </div>
        </div>
    </div>

    <!-- Display message if any -->
    <c:if test="${not empty sessionScope.message}">
    <div class="message ${sessionScope.message.contains('success') ? 'success' : 'error'}">
        ${sessionScope.message}
    </div>
    <% session.removeAttribute("message"); %>
    </c:if>

    <!-- User data table -->
    <table class="users_table">
        <thead>
            <tr>
                <th>UID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Access</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${userList}" var="user" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.first_Name}</td>
                <td>${user.last_Name}</td>
                <td>${user.gender}</td>
                <td>${user.email}</td>
                <td class="access">
                    <form action="${pageContext.request.contextPath}/UserManagementController" method="post" class="role-form" id="roleForm-${status.index}">
                        <input type="hidden" name="action" value="updateRole">
                        <input type="hidden" name="userEmail" value="${user.email}">
                        <select name="newRole" class="role-select ${user.role == 'Admin' ? 'admin-role' : 'user-role'}"
                        onchange="document.getElementById('roleForm-${status.index}').submit()">
                            <option value="Admin" ${user.role == 'Admin' ? 'selected' : ''}>Admin</option>
                            <option value="User" ${user.role != 'Admin' ? 'selected' : ''}>User</option>
                        </select>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>