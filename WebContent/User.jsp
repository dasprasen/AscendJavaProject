<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.io.IOException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="ascend.java.project.*"%>

<%
	List<UserBean> userInfo = new ArrayList<UserBean>();
	Connection con = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
		con = DatabaseConnection.getConnection();
		String query = "select username,firstname,lastname,age,phone,email from users where username=?";
		statement = con.prepareStatement(query);
		String uname=session.getAttribute("User").toString();
		//String uname=request.getParameter("username");
		statement.setString(1, uname);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			String username = resultSet.getString("username");
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			int age = resultSet.getInt("age");
			String phone = resultSet.getString("phone");
			String email = resultSet.getString("email");
			UserBean userBean = new UserBean(username, firstname, lastname, age, phone, email);
			userInfo.add(userBean);
		}
		con.close();
		session.setAttribute("userInfo", userInfo);
	} catch (SQLException e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Page</title>
</head>
<body>
    <center><h2>User's Home</h2></center>
    <form name="form" action="<%=request.getContextPath()%>/UpdateServlet" method="post">
    <c:if test="${not empty successMessage}">
		    <div style="color:green;text-align: center">
		       <c:out value="${successMessage}"/>
		   	</div>
	   			<c:set var="successMessage" value="" scope="session"/>
		</c:if>
        <table align="center">
        <c:forEach var='users' items="${userInfo }">
			 <tr>
		         <td>Username</td>
		         <td><input type="text" readonly="readonly" name="username" value="${users.username}"/></td>
	         </tr>
	         <tr>
		         <td>First Name</td>
		         <td><input type="text" readonly="readonly" name="firstname" value="${users.firstname}" /></td>
	         </tr>
	         <tr>
		         <td>Last Name</td>
		         <td><input type="text" readonly="readonly" name="lastname" value="${users.lastname}"/></td>
	         </tr>
	          <tr>
		         <td>Age</td>
		         <td><input type="number" required name="age" min="10" max="95" value="${users.age}"/></td>
	         </tr>
	         <tr>
		         <td>Phone</td>
		         <td><input type="text" required name="phone" pattern="[6789][0-9]{9}" value="${users.phone}"/></td>
	         </tr>
	         <tr>
		         <td>Email Address</td>
		         <td><input type="email" required name="email" value="${users.email}" /></td>
	         </tr>
		</c:forEach>
		<tr>
         	<td><%=(session.getAttribute("errMessage") == null) ? "": session.getAttribute("errMessage")%></td>
         </tr>
		<tr>
         <td><input type="submit" value="Update"></input></td>
         </tr>
        </table>
    </form>
    <div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
 
</body>
</html>