<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
form {border: 3px solid #f1f1f1;}

.container {
  padding: 8px;
}

input[type=text], input[type=password], input[type=number], input[type=email] {
  width: 98%;
  padding: 13px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

.registerbutton {
  background-color: #555555;
  color:white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
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

.registerbutton, .loginbutton {
  float: left;
  width: 50%;
}

</style>
</head>
<body>
    <form name="form" action="<%=request.getContextPath()%>/RegisterServlet" method="post">
   	 	<c:if test="${not empty successMessage}">
		    <div style="color:green;text-align: center">
		       <c:out value="${successMessage}"/>
		   	</div>
	   			<c:set var="successMessage" value="" scope="session"/>
		</c:if>
		<div class="container">
		     <label for="firstname"><b>First Name</b></label>
		     <input type="text" placeholder="Enter Firstname" name="firstname" required/>
	             
			  <label for="lastname"><b>Last Name</b></label>
		      <input type="text" placeholder="Enter Lastname" name="lastname" required/>
	     
		      <label for="age"><b>Age</b></label>
		      <input type="number" name="age" placeholder="Enter Age" min="10" max="95" required/>
	        
		      <label for="phone"><b>Phone</b></label>
		      <input type="text" name="phone" placeholder="Enter Phone Number" required pattern="[6789][0-9]{9}"/>
	       
		      <label for="email"><b>Email Address</b></label>
		      <input type="email" name="email" placeholder="Enter Email Address" required/>
	       
			  <label for="password"><b>Password</b></label>	         
			  <input type="password" name="password" id="password" placeholder="Enter the password" required onChange="onChange()"/>
			  
			  <label for="conpassword"><b>Confirm Password</b></label>
		      <input type="password" name="conpassword" placeholder="Enter the confirm password" id="password" required onChange="onChange()"/>
	         <%=(request.getAttribute("errMessage") == null) ? "": request.getAttribute("errMessage")%>
	        <div class="clearfix">
	    	 <button type="submit" value="Register" class="registerbutton" name="register">Register</button>
	         <button type="button" class="loginbutton" onclick="location.href ='<%=request.getContextPath()%>/Login.jsp'" >Click here to Login</button>
	    	</div>
		</div> 
    </form>
    <script>
    function onChange() {
    	  const password = document.querySelector('input[name=password]');
    	  const conpassword = document.querySelector('input[name=conpassword]');
    	  if (conpassword.value === password.value) {
    	    conpassword.setCustomValidity('');
    	  } else {
    	    conpassword.setCustomValidity('Passwords do not match');
    	  }
    	}
    </script>
</body>
</html>