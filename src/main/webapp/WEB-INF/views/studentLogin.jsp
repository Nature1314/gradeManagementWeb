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
		        <td>ID Number :</td>
		        <td><sf:input required="required"  path="idNumber" /></td>
		    </tr>
		    <tr>
		        <td>Password :</td>
		        <td><sf:password required="required"  path="password" /></td>
		    </tr>
		    <tr>
		    	<td><input type="submit" value= "Login"></td>
		    </tr>
		</table>
	</sf:form>

</body>
</html>