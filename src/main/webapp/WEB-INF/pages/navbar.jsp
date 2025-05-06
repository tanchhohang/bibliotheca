<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar.css"> 
 
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/css2?family=Instrument+Serif:ital@0;1&display=swap" rel="stylesheet"> 
     
<div class="navbar"> 
  <div class="navbar-left"> 
    <a href="${pageContext.request.contextPath}/home" class="logo">Bibliotheca</a> 
  </div> 
  <div class="navbar-right"> 
    <a href="${pageContext.request.contextPath}/userprofile">Profile</a> 
    <a href="${pageContext.request.contextPath}/library">Library</a> 
    <a href="${pageContext.request.contextPath}/aboutus">About Us</a> 
    <a href="${pageContext.request.contextPath}/contactus">Contact</a> 
    
    <c:choose>
      <c:when test="${not empty cookie.role}">
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
      </c:when>
      <c:otherwise>
        <a href="${pageContext.request.contextPath}/login">Sign In</a>
      </c:otherwise>
    </c:choose>
  </div> 
</div>