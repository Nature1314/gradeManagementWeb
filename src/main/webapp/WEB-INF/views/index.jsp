<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<c:url value="resources/css/selectForm.css" />"
	rel="stylesheet">
<title>welcome page</title>
</head>
<body>

	<h1 align="center">Grade Management System</h1>
	<marquee behavior="scroll" direction="right">Welcome to
		student grade information retrieval and management system!</marquee>

	<div class="center">
		<select name="Your status" onchange="location = this.value;"
			class="cmb">
			<option value="Your status">your status</option>
			<option value="studentLogin.jsp">Student</option>
			<option value="teacherLogin.jsp">Teacher</option>
			<option value="adminLogin.jsp">IT Administrator</option>
		</select>
	</div>
	<img  class = "centerImage"
		alt="background" 
		src="https://upload.wikimedia.org/wikipedia/commons/e/ec/Dartmouth_College_campus_2007-10-20_09.JPG"
		width = 800
		height = 300>
</body>
</html>