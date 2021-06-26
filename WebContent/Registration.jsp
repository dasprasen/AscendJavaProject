<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
    <form name="form" action="<%=request.getContextPath()%>/RegisterServlet" method="post">
   	 <c:if test="${not empty successMessage}">
	    <div style="color:green;text-align: center">
	       <c:out value="${successMessage}"/>
	   	</div>
   			<c:set var="successMessage" value="" scope="session"/>
		</c:if>
        <table align="center">
         <tr>
	         <td>First Name</td>
	         <td><input type="text" name="firstname" required/></td>
         </tr>
         <tr>
	         <td>Last Name</td>
	         <td><input type="text" name="lastname" required/></td>
         </tr>
         <tr>
	         <td>Password</td>
	         <td><input type="password" name="password" required/></td>
         </tr>
         <tr>
	         <td>Confirm Password</td>
	         <td><input type="password" name="conpassword" required/></td>
         </tr>
         <tr>
         	<td><%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%></td>
         </tr>
         <tr>
         <td></td>
         <td><input type="submit" value="Register"></input><input
         type="reset" value="Reset"></input></td> <td><a href="Login.jsp">Click here to Login</a></td>
         </tr>
        </table>
    </form>
</body>
</html>