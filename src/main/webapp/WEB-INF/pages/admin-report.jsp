<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bibliotheca - Admin Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/report.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <jsp:include page="navbar.jsp" />
    
    <div class="report-container">
        <h2>Admin Dashboard Report</h2>
        
        <div class="admin-highlights">
            <div class="highlight-card">
                <h3>Total Books</h3>
                <div class="highlight-value">${adminReport.totalBooks}</div>
            </div>
            <div class="highlight-card">
                <h3>Total Users</h3>
                <div class="highlight-value">${adminReport.totalUsers}</div>
            </div>
            <div class="highlight-card">
                <h3>Monthly Revenue</h3>
                <div class="highlight-value revenue">$${adminReport.monthlyRevenue}</div>
            </div>
        </div>
        
        <table>
            <tr>
                <th>Total Books</th>
                <td>${adminReport.totalBooks}</td>
            </tr>
            <tr>
                <th>Total Users</th>
                <td>${adminReport.totalUsers}</td>
            </tr>
            <tr>
                <th>Monthly Revenue</th>
                <td>$${adminReport.monthlyRevenue}</td>
            </tr>
            <tr>
                <th>Books Currently Borrowed</th>
                <td>${adminReport.currentlyBorrowed}</td>
            </tr>
            <tr>
                <th>Users with High Fines</th>
                <td>${adminReport.usersWithFines}</td>
            </tr>
        </table>
        
        <div style="text-align: center; margin-top: 30px;">

        </div>
    </div>
</body>
</html>