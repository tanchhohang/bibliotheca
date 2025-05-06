<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<link rel="stylesheet" href="<c:url value='/css/report.css' />" />

<head>
    <title>Admin Report</title>
</head>
<body>
<h2>Admin Report</h2>
<table border="1">
    <tr><th>Total Books</th><td>${adminReport.totalBooks}</td></tr>
    <tr><th>Total Users</th><td>${adminReport.totalUsers}</td></tr>
    <tr><th>Monthly Revenue</th><td>$${adminReport.monthlyRevenue}</td></tr>
</table>
</body>
</html>
