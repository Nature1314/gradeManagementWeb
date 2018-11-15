<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<sf:form action="Login" method="POST" modelAttribute="student_login_user">
		<table>
		    <tr>
		        <td>First Name :</td>
		        <td><sf:input path="firstName" /></td>
		    </tr>
		    <tr>
		        <td>Last Name :</td>
		        <td><sf:input path="lastName" /></td>
		    </tr>
		    <tr>
		        <td>Password :</td>
		        <td><sf:password path="password" /></td>
		    </tr>
		    <tr>
		    	<td><input type="submit" value= "Login"></td>
		    </tr>
		</table>
	</sf:form>

</body>
</html>