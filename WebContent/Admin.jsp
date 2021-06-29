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
	List<AdminBean> usersList = new ArrayList<AdminBean>();
	Connection con = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
	try {
		con = DatabaseConnection.getConnection();
		String query = "select username,firstname,lastname,age,phone,email,role from users where role='users'";
		statement = con.prepareStatement(query);
		resultSet = statement.executeQuery();
		while (resultSet.next()) {
			String username = resultSet.getString("username");
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			int age=resultSet.getInt("age");
			String phone=resultSet.getString("phone");
			String email=resultSet.getString("email");
			String role = resultSet.getString("role");
			AdminBean adminBean = new AdminBean(username, firstname, lastname, age, phone, email, role);
			usersList.add(adminBean);
		}
		con.close();
		session.setAttribute("usersList", usersList);
	} catch (SQLException e) {
		e.printStackTrace();
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Admin Page</title>
</head>
<body>
<script>
function downloadCSV(csv, filename) {
    var csvFile;
    var downloadLink;
    // CSV file
    csvFile = new Blob([csv], {type: "text/csv"});
    console.log(csvFile);
    // Download link
    downloadLink = document.createElement("a");
    console.log(downloadLink);
    // File name
    downloadLink.download = filename;
    // Create a link to the file
    downloadLink.href = window.URL.createObjectURL(csvFile);
    // Hide download link
    downloadLink.style.display = "none";
    // Add the link to DOM
    document.body.appendChild(downloadLink);
    // Click download link
    console.log(downloadLink);
    downloadLink.click();
}
function exportTableToCSV(filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    console.log(rows);
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        
        csv.push(row.join(","));        
    }
    console.log(cols);
    console.log(csv);
    // Download CSV file
    downloadCSV(csv.join("\n"), filename);
}
</script>
<style>
.center {
  margin-left: auto;
  margin-right: auto;
}
button {
  float: right;
  clear: both;
}
</style>
<center><h2>Admin's Home</h2></center>
<div style="text-align: right"><a href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></div>
<h3 style="text-align:center">Users List</h3>
	<table class="center" border="1" cellpadding="2" cellspacing="2">
		<tr>
			<th>Username</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Age</th>
			<th>Phone</th>
			<th>Email</th>
			<th>Role</th>
		</tr>
		<c:forEach var='users' items="${usersList }">
			<tr>
				<td>${users.username }</td>
				<td>${users.firstname }</td>
				<td>${users.lastname }</td>
				<td>${users.age }</td>
				<td>${users.phone }</td>
				<td>${users.email }</td>
				<td>${users.role }</td>
			</tr>
		</c:forEach>
	</table>
	<button onclick="exportTableToCSV('users.csv')">Download Users Data</button>
	<form action="<%=request.getContextPath()%>/DataServlet" method="post" enctype="multipart/form-data">
	    <input name="filename" type="file">
	    <input type="submit" value="Import">
   </form>
</body>
</html>