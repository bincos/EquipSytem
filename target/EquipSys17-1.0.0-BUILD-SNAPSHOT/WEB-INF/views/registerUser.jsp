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

<body>
<div class="errors">
	${exception}
	<!-- Le nom de notre exception -->
</div>
<div id="formCat"  class="cadre">
<h2>Hello Admin register a new user</h2>
	<!-- Comme on va envoyer des fichiers on fait multipart -->
	<f:form modelAttribute="registerUser" action="registerFullUserBtn" method="post">
		<table>	
								
			<tr>
				<td>User Name :</td>				
					<td><f:input path="userName"   required="true"/>
					
					</td>
					<td><f:errors path="userName" cssClass="error">
						</f:errors></td>
					</tr>
					
					<tr>
				<td> PassWord:</td>
				
					<td><f:input type="text" path="password"   required="true"/>
					</td>
					<td><f:errors path="password" cssClass="error"/>
						</td>
					</tr>
						<tr>
				<td> Faculte:</td>
				
					<td><f:input type="text" path="faculte"   required="true"/>
					</td>
					<td><f:errors path="faculte" cssClass="error"/>
						</td>
					</tr>	
					
							<tr>
				<td> Email:</td>
				
					<td><f:input type="email" path="email"   required="true"/>
					</td>
					<td><f:errors path="email" cssClass="error"/>
						</td>
					</tr>					
					<tr>				
				
					<tr>					
				<td></td> <td><input type="submit" value="Save" /></td>
				
			</tr>
				<tr>					
				<td></td> <td> <a  href="#"> <strong>Forget your password</strong></a> </td>
			</tr>
		</table>
	</f:form>
	<!-- Listes des cotes Disponnibles -->	
</div>
</body>
</html>

