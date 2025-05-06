<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bibliotheca - About Us</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about-us.css">
    
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

    <div class="container">
        <div class="header">
            <h1>About Us</h1>
            <p>Our team</p>
        </div>
        
        <div class="team-grid">
            <div class="team-member">
                <div class="member-image">
                    <div class="color-box black-box"></div>
                </div>
                <div class="member-info">
                    <h2 class="member-name">Tanchho Limbu</h2>
                    <p class="member-department">Islington - Computing</p>
                    <p class="member-dob">DOB: 12/01/2004</p>
                    <p class="member-bio">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua...
                    </p>
                </div>
            </div>
            
            <div class="team-member">
                <div class="member-image">
                    <div class="color-box navy-box"></div>
                </div>
                <div class="member-info">
                    <h2 class="member-name">Tanushree Rajbhandari</h2>
                    <p class="member-department">Islington - Computing</p>
                    <p class="member-dob">DOB: 12/05/2005</p>
                    <p class="member-bio">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua...
                    </p>
                </div>
            </div>
            
            <!-- Team Member 3 -->
            <div class="team-member">
                <div class="member-image">
                    <div class="color-box yellow-box"></div>
                </div>
                <div class="member-info">
                    <h2 class="member-name">Anush Tamang</h2>
                    <p class="member-department">Islington - Computing</p>
                    <p class="member-dob">DOB: 06/06/2006</p>
                    <p class="member-bio">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua...
                    </p>
                </div>
            </div>
            
            <div class="team-member">
                <div class="member-image">
                    <div class="color-box purple-box"></div>
                </div>
                <div class="member-info">
                    <h2 class="member-name">Aryan Tamrakar</h2>
                    <p class="member-department">Islington - Computing</p>
                    <p class="member-dob">DOB: 12/01/2004</p>
                    <p class="member-bio">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua...
                    </p>
                </div>
            </div>
            
            <div class="team-member">
                <div class="member-image">
                    <div class="color-box red-box"></div>
                </div>
                <div class="member-info">
                    <h2 class="member-name">Sameep Karki</h2>
                    <p class="member-department">Islington - Computing</p>
                    <p class="member-dob">DOB: 21/04/2004</p>
                    <p class="member-bio">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua.
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                        eiusmod tempor incididunt ut labore et dolore magna aliqua...
                    </p>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</body>
</html>