<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - Your Digital Library Hub</title>
    
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
	
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Instrument+Serif:ital@0;1&display=swap" rel="stylesheet">
    
</head>
<body>

   <jsp:include page="navbar.jsp" />

    <main>
        <!-- Welcome Section - Updated to be 100% width with text on right -->
        <section class="welcome-section">
            <div class="welcome-image">
                <!-- Image of people shaking hands will be set as background in CSS -->
            </div>
            <div class="welcome-content">
              <h1>Welcome To</h1>
                <h2>Bibliotheca</h2>
                <p>Your Digital Library Hub</p>
                <c:choose>
			      <c:when test="${not empty cookie.role}">
			       	<a href="#"><button class="cta-button">HOME</button></a>
			      </c:when>
			      <c:otherwise>
			        <a href="${pageContext.request.contextPath}/register"><button class="cta-button">JOIN US NOW</button></a>
			      </c:otherwise>
			    </c:choose>
                
            </div>
        </section>

        <!-- Search and Categories - Full width layout -->
        <div class="search-and-categories">
            <div class="search-container">
                <input type="text" placeholder="Search" class="search-bar">
                <button class="search-button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                    </svg>
                </button>
            </div>
            <div class="category-tabs">
                <a href="#" class="category-tab">HOME</a>
                <a href="#" class="category-tab">GENRE</a>
                <a href="#" class="category-tab active">AUTHOR</a>
            </div>
        </div>

        <!-- Latest News Section - Full width -->
        <section class="latest-news">
            <h2>Latest News</h2>
            <p>Get to know the latest updates and what is happening with literature</p>
            
            <!-- Dune Feature - Updated to be 100% width -->
            <div class="featured-book">
                <div id="dune-container">
                    <div class="dune-content">
                        <h2>DUNE</h2>
                        <p>A beginning is the time for taking the most delicate care that the balances are correct. This every sister of the Bene Gesserit knows. To begin your study of the life of Muad'Dib, then, take care that you first place him in his time: born in the 57th year of the Padishah Emperor, Shaddam IV. And take the most special care that you locate...</p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Newest Collection Section -->
        <section class="newest-collection">
            <h2>Newest In Our Collection</h2>
            <p>Browse though our latest collection of curated books</p>
            <a href="#" class="see-more">SEE MORE ></a>
            
            <div class="book-grid">
                <!-- Book 1 -->
                <div class="book-card">
                    <img src="${pageContext.request.contextPath}/resources/images/goldenkey.png" alt="The Golden Key" class="book-cover">
                    <h3>The Golden Key</h3>
                    <p class="author">Elisabetta Dami</p>
                    <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
                
                <!-- Book 2 -->
                <div class="book-card">
                    <img src="${pageContext.request.contextPath}/resources/images/bfg.png" alt="The BFG" class="book-cover">
                    <h3>The BFG</h3>
                    <p class="author">Roald Dahl</p>
                    <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
                
                <!-- Book 3 -->
                <div class="book-card">
                    <img src="${pageContext.request.contextPath}/resources/images/inferno.png" alt="The Inferno" class="book-cover">
                    <h3>The Inferno</h3>
                    <p class="author">Dante Alighieri</p>
                    <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
                
                <!-- Book 4 -->
                <div class="book-card">
                    <img src="${pageContext.request.contextPath}/resources/images/lightningthief.png" class="book-cover">
                    <h3>The Lightning Thief</h3>
                    <p class="author">Rick Riordan</p>
                    <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
                
                <!-- Book 5 -->
                <div class="book-card">
                    <img src="${pageContext.request.contextPath}/resources/images/norwegian.png" alt="Norwegian Wood" class="book-cover">
                    <h3>Norwegian Wood</h3>
                    <p class="author">Murakami</p>
                    <p class="book-description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                </div>
            </div>
        </section>

        <!-- Why Choose Us Section -->
        <section class="why-choose-us">
            <div class="why-choose-content">
                <h2>Why Choose</h2> 
                <h1>Bibliotheca</h1>
                <p>Learn, explore, and grow with our extensive collection of digital resources covering a wide range of topics. Access our vast library of online reading materials from the comfort of your home. Enjoy our user-friendly interface designed to enhance your reading experience. Download our mobile application for on-the-go reading pleasure.</p>
                </div>
            <div class="why-choose-image">
                <!-- Image container for the illustration with book and sun -->
            </div>
        </section>
    </main>

    <jsp:include page="footer.jsp" />
    
</body>
</html>