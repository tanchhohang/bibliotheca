<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - User Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/user-profile.css" />
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <div class="container">
        <section class="profile-section">
            <div class="profile-image">
                <c:choose>
                    <c:when test="${not empty user.profilePic}">
                        <img src="${pageContext.request.contextPath}/resources/images/${user.profilePic}" alt="Profile avatar">
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/resources/images/profilepic.jpg" alt="Default avatar">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="profile-details">
                <div class="profile-info-container">
                    <h1>${user.firstName} ${user.lastName} <span class="admin-tag">User</span></h1>
                    <div class="profile-info">
                        <div class="profile-info-item">
                            <div class="profile-info-label">Gender:</div>
                            <div class="profile-info-value">${user.gender}</div>
                        </div>
                        <div class="profile-info-item">
                            <div class="profile-info-label">Address:</div>
                            <div class="profile-info-value">${user.address}</div>
                        </div>
                        <div class="profile-info-item">
                            <div class="profile-info-label">Birthday:</div>
                            <div class="profile-info-value">${user.dob}</div>
                        </div>
                        <div class="profile-info-item">
                            <div class="profile-info-label">Books Count:</div>
                        </div>
                        <div class="profile-info-item">
                            <div class="profile-info-label">Email:</div>
                            <div class="profile-info-value">${user.email}</div>
                        </div>
                        <div class="profile-info-item">
                            <div class="profile-info-label">Favorite genre:</div>
                        </div>
                    </div>
                    <a href="${pageContext.request.contextPath}/editprofile"><button class="btn edit-profile-btn" id="editProfileBtn">Edit Profile</button></a>
                </div>
                <div class="buttons-container">
                    <button class="btn generate-report-btn" id="generateReportBtn">Generate Report</button>
                    <div class="stats-cards">
                        <div class="stat-card">
                            <div class="stat-label">Total Members</div>
                            <div class="stat-value">${stats.totalMembers}</div>
                        </div>
                        <div class="stat-card">
                            <div class="stat-label">Total Books</div>
                            <div class="stat-value">${stats.totalBooks}</div>
                        </div>
                        <div class="stat-card">
                            <div class="stat-label">Total Borrowed</div>
                            <div class="stat-value">${stats.totalBorrowed}</div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="section-divider"></div>
        <div class="main-content">
            <div class="section">
                <h2 class="section-title">Current Book</h2>
                <p class="section-subtitle">Look back to your reading history</p>
                <c:if test="${not empty currentBook}">
                    <div class="current-book">
                        <div class="book-cover">
                            <img src="${pageContext.request.contextPath}/resources/images/${not empty currentBook.coverImage ? currentBook.coverImage : 'defaultbook.jpg'}" alt="${currentBook.title} Cover">
                        </div>
                        <div class="book-info">
                            <h3>${currentBook.title}</h3>
                            <div class="book-author">${currentBook.author}</div>
                            <div class="book-rating">Your Rating: 9/10</div>
                            <div class="book-date">Borrow Date: <fmt:formatDate value="${currentBook.borrowDate}" pattern="dd/MM/yyyy"/> - Present</div>
                            <div class="book-summary">${currentBook.summary}</div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty currentBook}">
                    <div class="current-book">
                        No books currently reading
                    </div>
                </c:if>
            </div>
            <div class="section">
                <h2 class="section-title">Reading Stats</h2>
                <p class="section-subtitle">Look back to your reading history</p>
                <div class="chart-container">
                    <div class="chart"></div>
                </div>
            </div>
        </div>
        <div class="section borrow-history">
            <h2 class="section-title">Borrow History</h2>
            <p class="section-subtitle">Look back to your reading history</p>
            <div class="history-items">
                <c:forEach items="${borrowHistory}" var="history">
                    <div class="history-item">
                        <div class="history-book-cover">
                            <img src="${pageContext.request.contextPath}/resources/images/${not empty history.book.coverImage ? history.book.coverImage : 'defaultbook.jpg'}" alt="${history.book.title} Cover">
                        </div>
                        <div class="book-info">
                            <h3>${history.book.title}</h3>
                            <div class="book-author">${history.book.author}</div>
                            <div class="book-rating">Your Rating: ${history.rating}/10</div>
                            <div class="book-date">
                                Borrow Date: <fmt:formatDate value="${history.borrowDate}" pattern="dd/MM/yyyy"/> -
                                <c:choose>
                                    <c:when test="${not empty history.returnDate}">
                                        <fmt:formatDate value="${history.returnDate}" pattern="dd/MM/yyyy"/>
                                    </c:when>
                                    <c:otherwise>
                                        Present
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="book-summary">${history.summary}</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</body>
</html>