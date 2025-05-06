<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<link rel="stylesheet" href="<c:url value='/css/styles.css' />" />

<head>
    <title>User Report</title>
</head>
<body>
<h2>User Report</h2>
<table border="1">
    <tr><th>Total Books Borrowed</th><td>${userReport.borrowedBooks}</td></tr>
    <tr><th>Total Payment</th><td>$${userReport.totalPayments}</td></tr>
</table>
</body>
</html>
