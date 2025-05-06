<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - Admin</title>

    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin-profile.css" />

</head>
<body>

	<jsp:include page="navbar.jsp" />
	
    <!-- Main container for all content -->
    <div class="container">
        <!-- User profile section with image and details -->
        <section class="profile-section">
            <!-- User profile image container -->
            <div class="profile-image">
                <img src="${pageContext.request.contextPath}/resources/images/profilepic.jpg" alt="Profile avatar">
            </div>
            
            <!-- Profile details with personal info and action buttons -->
            <div class="profile-details">
                <div class="profile-info-container">
                    <!-- Username with admin badge -->
                    <h1>admin1 <span class="admin-tag">Admin</span></h1>
                    <p>#95510618</p>
                    
                    <!-- Grid of user information -->
                    <div class="profile-info">
                        <div class="profile-info-item">
                            <div class="profile-info-label">Gender:</div>
                            <div class="profile-info-value">gmail</div>
                        </div>
                        
                        <div class="profile-info-item">
                            <div class="profile-info-label">Address:</div>
                            <div class="profile-info-value">gmail</div>
                        </div>
                        
                        <div class="profile-info-item">
                            <div class="profile-info-label">Birthday:</div>
                            <div class="profile-info-value">06/06/1006</div>
                        </div>
                        
                        <div class="profile-info-item">
                            <div class="profile-info-label">Books Count:</div>
                            <div class="profile-info-value">03</div>
                        </div>
                        
                        <div class="profile-info-item">
                            <div class="profile-info-label">Email:</div>
                            <div class="profile-info-value">anustam@gmail.com</div>
                        </div>
                        
                        <div class="profile-info-item">
                            <div class="profile-info-label">Favorite genre:</div>
                            <div class="profile-info-value">Romcom</div>
                        </div>
                    </div>
                    
                    <!-- Button to edit profile -->
                    <a href="${pageContext.request.contextPath}/editprofile"><button class="btn edit-profile-btn" id="editProfileBtn">Edit Profile</button></a>
                </div>
                
                <!-- Container for admin action buttons and statistics cards -->
                <div class="buttons-container">
                    <button class="btn generate-report-btn" id="generateReportBtn">Generate Report</button>
                    <a href="${pageContext.request.contextPath}/usermanagement"><button class="btn edit-users-btn" id="editUsersBtn">Edit Users</button></a>
                    
                    <!-- Statistics display cards -->
                    <div class="stats-cards">
                        <div class="stat-card">
                            <div class="stat-label">Total Members</div>
                            <div class="stat-value">39</div>
                        </div>
                        
                        <div class="stat-card">
                            <div class="stat-label">Total Books</div>
                            <div class="stat-value">56</div>
                        </div>
                        
                        <div class="stat-card">
                            <div class="stat-label">Total Borrowed</div>
                            <div class="stat-value">36</div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- Divider between page sections -->
        <div class="section-divider"></div>
        
        <!-- Dashboard section with charts and statistics -->
        <section class="dashboard-sections">
            <div class="dashboard-section">
                <h2>Monthly Transaction</h2>
                <p>Look back to your reading history</p>
                <!-- Placeholder for transaction chart -->
                <div class="placeholder-chart" id="transactionChart"></div>
            </div>
            
            <div class="dashboard-section">
                <h2>Reading Stats</h2>
                <p>Look back to your reading history</p>
                <!-- Placeholder for reading stats chart -->
                <div class="placeholder-chart" id="readingStatsChart"></div>
            </div>
        </section>
        
        <!-- Divider between page sections -->
        <div class="section-divider"></div>
        
        <!-- Library management section -->
        <section class="library-section">
            <div class="library-header">
                <div>
                    <h2>Edit Library</h2>
                    <p>Look back to your reading history</p>
                </div>
                
                <!-- Search box for books -->
                <div class="search-box">
                    <input type="text" id="searchInput" placeholder="Search Books">
                    <button id="searchBtn">🔍</button>
                </div>
            </div>
            
            <!-- Container for book items -->
            <div class="books-container">
                <!-- Book item 1 -->
                <div class="book-item">
                    <div class="book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/norwegian.png" alt="Meditations book cover">
                    </div>
                    
                    <div class="book-details">
                        <button class="edit-book-btn" data-book-id="1">Edit</button>
                        <h3 class="book-title">Meditations</h3>
                        <p class="book-author">Marcus Aurelius</p>
                        <p class="book-rating">Your Rating: 8.9/10</p>
                        <p class="book-borrow-date">Borrow Date: 09/04/25 - Present</p>
                        <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...</p>
                    </div>
                </div>
                
                <!-- Book item 2 -->
                <div class="book-item">
                    <div class="book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/norwegian.png" alt="The BFG book cover">
                    </div>
                    
                    <div class="book-details">
                        <button class="edit-book-btn" data-book-id="2">Edit</button>
                        <h3 class="book-title">The BFG</h3>
                        <p class="book-author">Roald Dahl</p>
                        <p class="book-rating">Your Rating: 8.9/10</p>
                        <p class="book-borrow-date">Borrow Date: 09/04/25 - Present</p>
                        <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...</p>
                    </div>
                </div>
                
                <!-- Book item 3 -->
                <div class="book-item">
                    <div class="book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/norwegian.png" alt="The Prince book cover">
                    </div>
                    
                    <div class="book-details">
                        <button class="edit-book-btn" data-book-id="3">Edit</button>
                        <h3 class="book-title">The Prince</h3>
                        <p class="book-author">Nicolo Machiavelli</p>
                        <p class="book-rating">Your Rating: 8.9/10</p>
                        <p class="book-borrow-date">Borrow Date: 09/04/25 - Present</p>
                        <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...</p>
                    </div>
                </div>
                
                <!-- Book item 4 -->
                <div class="book-item">
                    <div class="book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/norwegian.png" alt="Norwegian Woods book cover">
                    </div>
                    
                    <div class="book-details">
                        <button class="edit-book-btn" data-book-id="4">Edit</button>
                        <h3 class="book-title">Norwegian Woods</h3>
                        <p class="book-author">Murakami</p>
                        <p class="book-rating">Your Rating: 8.9/10</p>
                        <p class="book-borrow-date">Borrow Date: 09/04/25 - Present</p>
                        <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...</p>
                    </div>
                </div>
            </div>
        </section>
    </div>

	<jsp:include page="footer.jsp" />

	<script src="${pageContext.request.contextPath}/js/adminprofile.js"></script>
	
</body>
</html>