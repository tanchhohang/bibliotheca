<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bibliotheca - Contact Us</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact-us.css" />
  
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

  <div class="container">
    <h1>Contact Us</h1>
    <p>Contact our team for any inquiries</p>
    
    <form>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" placeholder="Author Name">
      </div>
      
      <div class="form-group">
        <label for="inquiry">Inquiry:</label>
        <textarea id="inquiry" placeholder="Summary about the book"></textarea>
      </div>
    </form>
  </div>
  
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>