<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome page</title>
</head>
<body>

		<h1 align="center">Grade Management System</h1>
		<marquee behavior="scroll" direction="right">Welcome to student grade information retrieval and management system!</marquee>

	
	<select name="Your status" onchange="location = this.value;">
	  <option value="student.jsp">Student</option>
	  <option value="teacher.jsp">Teacher</option>
	  <option value="administrator.jsp">IT Administrator</option>
	</select>
		<a href="Login">go to login</a>
</body>
</html>