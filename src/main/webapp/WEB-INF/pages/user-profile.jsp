<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - User Profile</title>
    
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user-profile.css" />

    
</head>
<body>

	<jsp:include page="navbar.jsp" />

    <div class="container">
        <section class="profile-section">
            <!-- User profile image container -->
            <div class="profile-image">
                <img src="${pageContext.request.contextPath}/resources/images/profilepic.jpg" alt="Profile avatar">
            </div>
            
            <!-- Profile details with personal info and action buttons -->
            <div class="profile-details">
                <div class="profile-info-container">
                    <!-- Username with admin badge -->
                    <h1>user1 <span class="admin-tag">User</span></h1>
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
        
        <div class="section-divider"></div>
        
        <div class="main-content">
            <div class="section">
                <h2 class="section-title">Current Book</h2>
                <p class="section-subtitle">Look back to your reading history</p>
                
                <div class="current-book">
                    <div class="book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/goldenkey.png" alt="Meditations Cover">
                    </div>
                    
                    <div class="book-info">
                        <h3>Meditations</h3>
                        <div class="book-author">Marcus Aurelius</div>
                        
                        <div class="book-rating">Your Rating: 8.9/10</div>
                        <div class="book-date">Borrow Date: 09/04/25 - Present</div>
                        
                        <div class="book-summary">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="section">
                <h2 class="section-title">Reading Stats</h2>
                <p class="section-subtitle">Look back to your reading history</p>
                
                <div class="chart-container">
                    <div class="chart">
                    </div>
                </div>
            </div>
        </div>
        
        <div class="section borrow-history">
            <h2 class="section-title">Borrow History</h2>
            <p class="section-subtitle">Look back to your reading history</p>
            
            <div class="search-box">
                <input type="text" placeholder="Search">
                <button>🔍</button>
            </div>
            
            <div class="history-items">
                <div class="history-item">
                    <div class="history-book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/goldenkey.png" alt="Meditations Cover">
                    </div>
                    
                    <div class="book-info">
                        <h3>Meditations</h3>
                        <div class="book-author">Marcus Aurelius</div>
                        
                        <div class="book-rating">Your Rating: 8.9/10</div>
                        <div class="book-date">Borrow Date: 09/04/25 - Present</div>
                        
                        <div class="book-summary">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
                        </div>
                    </div>
                </div>
                
                <div class="history-item">
                    <div class="history-book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/bfg.png" alt="The BFG Cover">
                    </div>
                    
                    <div class="book-info">
                        <h3>The BFG</h3>
                        <div class="book-author">Roald Dahl</div>
                        
                        <div class="book-rating">Your Rating: 8.9/10</div>
                        <div class="book-date">Borrow Date: 09/04/25 - Present</div>
                        
                        <div class="book-summary">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
                        </div>
                    </div>
                </div>
                
                <div class="history-item">
                    <div class="history-book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/inferno.png" alt="The Prince Cover">
                    </div>
                    
                    <div class="book-info">
                        <h3>The Prince</h3>
                        <div class="book-author">Nicolo Machiavelli</div>
                        
                        <div class="book-rating">Your Rating: 8.9/10</div>
                        <div class="book-date">Borrow Date: 09/04/25 - Present</div>
                        
                        <div class="book-summary">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
                        </div>
                    </div>
                </div>
                
                <div class="history-item">
                    <div class="history-book-cover">
                        <img src="${pageContext.request.contextPath}/resources/images/norwegian.png" alt="Norwegian Woods Cover">
                    </div>
                    
                    <div class="book-info">
                        <h3>Norwegian Woods</h3>
                        <div class="book-author">Murakami</div>
                        
                        <div class="book-rating">Your Rating: 8.9/10</div>
                        <div class="book-date">Borrow Date: 09/04/25 - Present</div>
                        
                        <div class="book-summary">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<jsp:include page="footer.jsp" />

</body>
</html>