<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator</title>
</head>
<body>
	<h1 align="center">IT Administrator Page </h1>
	<h3>Create the new user now</h3>
	<form action="administrator" method="POST">
		<select name="status1">
		  <option value="student">Student</option>
		  <option value="teacher">Teacher</option>
		</select>
		<input required="required" type="text" name="firstName1" placeholder="type first name at here">
		<input required="required" type="text" name="lastName1" placeholder="type last name at here">
		<input type="password" name="password" placeholder="enter password">
		<input type="submit" value="Submit">
		
	</form>
	
	<h3>Search the user</h3>
	<form action="administrator" method="POST">
		<select name="status2">
		  <option value="student2">Student</option>
		  <option value="teacher2">Teacher</option>
		</select>
		<input type="text" name="firstName2" placeholder="type first name at here">
		<input type="text" name="lastName2" placeholder="type last name at here">
		<input type="submit" value="Submit">
		
	</form>
</html>