<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bibliotheca - User Report</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/report.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <jsp:include page="navbar.jsp" />
    
    <div class="report-container">
        <h2>User Activity Report</h2>
        
        <div class="user-details">
            <p class="user-name">User: ${userEmail}</p>
            <p>Report generated on: ${report.reportDate}</p>
        </div>
        
        <table>
            <tr>
                <th>Total Books Borrowed</th>
                <td>${userReport.borrowedBooks}</td>
            </tr>
            <tr>
                <th>Total Fine</th>
                <td>$${userReport.totalPayments}</td>
            </tr>

        </table>
        
        <div style="text-align: center; margin-top: 30px;">
            <p><i>Thank you for using Bibliotheca Library Management System!</i></p>
        </div>
    </div>
</body>
</html>