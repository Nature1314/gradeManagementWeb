<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>administrator login page</title>
</head>
<body>

	<sf:form action="adminLogin" method="POST" modelAttribute="admin_login_user">
		<table>
		    <tr>
		        <td>ID Number :</td>
		        <td><sf:input path="IDnumber" required="required"/></td>
		    </tr>
		    <tr>
		        <td>Password :</td>
		        <td><sf:password path="password" required="required" /></td>
		    </tr>
		    <tr>
		    	<td><input type="submit" value= "Login"></td>
		    </tr>
		</table>
	</sf:form>

</body>
</html>