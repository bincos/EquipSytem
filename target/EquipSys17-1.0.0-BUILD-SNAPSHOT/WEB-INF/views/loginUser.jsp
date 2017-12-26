<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/CSS/style1.css">
</head>

<body onload='document.f.j_username.focus();'>  
 <h3>Login with Username and Password (Custom Login Page)</h3>  
   
 <c:if test="${not empty error}">  
  <div class="errorblock">  
   Your login attempt was not successful, try again.  
 Caused :  
   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}  
   
  </div> 

 </c:if>
  
  
<div id="formCat"  class="cadre">
	
	<form name="f"   action="j_spring_security_check" method="post">
		<table>
		<tr>  
      <td colspan="2" style="color: red">${loginStatus}</td>    
     </tr>
			<tr>
				<td>Login</td>
				<td><input type="text" name="j_username"></td>
			</tr>
			
			<tr>
				<td>Pass word</td>
				<td><input type="password" name="j_password"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>

</div>
</body>
</html>

