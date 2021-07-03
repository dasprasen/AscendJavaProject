<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

button {
  background-color: #24a0ed;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
}

button:hover {
  opacity: 0.8;
}

.regbutton {
  background-color: #555555;
}

.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

.avatar {
  width: 17%;
  border-radius: 50%;
}

.container {
  padding: 16px;
}
.regbutton, .loginbutton {
  float: left;
  width: 50%;
}
</style>
</head>
<body>

<h2>Login Form</h2>

<form action="<%=request.getContextPath()%>/LoginServlet" method="post">
  <c:if test="${not empty errMessage}">
    <div style="color:red;text-align: center">
    	 <c:out value="${errMessage}"/>
	</div>
	   	 <c:set var="errMessage" value="" scope="session"/>
 </c:if>
  <div class="imgcontainer">
    <img src="login.png" alt="Avatar" class="avatar">
  </div>

  <div class="container">
    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>

    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <div class="clearfix">
      <button type="submit" class="loginbutton">Login</button>   
      <button type="button" class="regbutton" onclick="location.href ='<%=request.getContextPath()%>/Registration.jsp'" >Not Yet Registered !!! Click Here</button>
    </div>
  </div>
</form>

</body>
</html>