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
		//String uname=request.getAttribute("username");
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
<title>User Home Page</title>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

.container {
  padding: 8px;
}

input[type=text], input[type=number], input[type=email] {
  width: 98%;
  padding: 13px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

.updatebutton {
  background-color: #04AA6D;
  color:white;
  padding: 14px 90px;
  margin: 8px 8px;
  border: none;
  cursor: pointer;
}

a {
  background-color: #24a0ed;
  color: white;
  padding: 10px 18px;
  margin: 40px 20px;
  border: none;
  cursor: pointer;
}

</style>
</head>
<body>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet?userName=<%=request.getAttribute("userName")%>">Logout</a></div>
    <center><h1>User's Home</h1></center>
    <form name="form" action="<%=request.getContextPath()%>/UpdateServlet" method="post">
    <c:if test="${not empty successMessage}">
		    <div style="color:green;text-align: center">
		       <c:out value="${successMessage}"/>
		   	</div>
	   			<c:set var="successMessage" value="" scope="session"/>
		</c:if>
        <c:forEach var='users' items="${userInfo }">
        	<div class="container">
        		<label for="username"><b>Username</b></label>
		          <input type="text" readonly="readonly" name="username" value="${users.username}"/> 
	          
		          <label for="firstname"><b>First Name</b></label>
		          <input type="text" readonly="readonly" name="firstname" value="${users.firstname}" /> 
	          
		          <label for="lastname"><b>Last Name</b></label>
		          <input type="text" readonly="readonly" name="lastname" value="${users.lastname}"/> 
	          
		          <label for="age"><b>Age</b></label>
		          <input type="number" required name="age" min="10" max="95" value="${users.age}"/> 
	          
		          <label for="phone"><b>Phone</b></label>
		          <input type="text" required name="phone" pattern="[6789][0-9]{9}" value="${users.phone}"/> 
	          
		          <label for="email"><b>Email Address</b></label>
		          <input type="email" required name="email" value="${users.email}" /> 
        	</div>
		</c:forEach>
         <%=(session.getAttribute("errMessage") == null) ? "": session.getAttribute("errMessage")%>
         <button type="submit" value="Update" class="updatebutton" name="Update">Update</button>
    </form>
</body>
</html>