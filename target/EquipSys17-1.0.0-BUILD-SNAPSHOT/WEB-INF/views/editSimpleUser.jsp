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

	<!-- Comme on va envoyer des fichiers on fait multipart -->
	<f:form modelAttribute="editProfile" action="registerUpdateBtn" method="post" enctype="multipart/form-data">
		<table>	
		
			<tr>
				<td>Profile Picture : </td>
				<!-- On Fait Un Test Pour LÁFFICHAGE Img -->
				
							<td><input  type="file" name="file" />	
							<td>
				<c:if test="${profilePicture.idUser !=null}">
				<img src="editsimpleUser?idSimpleUser=${profilePicture.idUser}"/>			
				</c:if>
				</td>	
							<td><f:errors path="imageSimpleUser" cssClass="error">
						</f:errors></td>					
		
			</tr>		
			<tr>
				<td>Id User. :</td>				
					<td><f:input path="idUser"  disabled="true"/>
					
					</td>
					<td><f:errors path="idUser" cssClass="error">
						</f:errors></td>
					</tr>			
			<tr>
				<td>First Name: :</td>				
					<td><f:input path="firstName"   required="true" />
					
					</td>
					<td><f:errors path="firstName" cssClass="error">
						</f:errors></td>
					</tr>
					
					<tr>
				<td> Last NameUser:</td>
				
					<td><f:input type="text" path="nameUser"   required="true"/>
					</td>
					<td><f:errors path="nameUser" cssClass="error"/>
						</td>
					</tr>
						<tr>
				<td> Pseudo:</td>
				
					<td><f:input type="text" path="userName"   required="true"/>
					</td>
					<td><f:errors path="userName" cssClass="error"/>
						</td>
					</tr>
					
					<tr>
			<td> Email :</td>
				
					<td><f:input type="email"  path="email"   required="true" />
					</td>
					<td><f:errors path="email" cssClass="error"/>
						</td>
					</tr>			
				
					<tr>
				<td>PassWord :</td>
				
					<td><f:input path="password"   required="true"/>
					
					</td>
					<td><f:errors path="password" cssClass="error"  >
						</f:errors></td>
					</tr>					
						<tr>
				<td>Phone number :</td>
				
					<td><f:input path="phonenumber"   required="true"/>
					
					</td>
					<td><f:errors path="phonenumber" cssClass="error"    >
						</f:errors></td>
					</tr>						
					<tr>
				<td>Promotion:</td>
				
					<td><f:input path="promotion"   required="true" />
					
					</td>
					<td><f:errors path="promotion" cssClass="error"    >
						</f:errors></td>
					</tr>						
					<tr>
				<td>Year :</td>
				
					<td><f:input path="year"   required="true"/>					
					</td>
					<td><f:errors path="year" cssClass="error" >
						</f:errors></td>
					</tr>						
					<tr>
				<td>Cours Prefered :</td>				
					<td><f:input path="coursYourLike" />					
					</td>
					<td><f:errors path="coursYourLike" cssClass="error"   >
						</f:errors></td>
					</tr>					
					<tr>					
				<td></td> <td><input type="submit" value="Save" /></td>
				
			</tr>
				<tr>					
				<td></td> <td> <a  href="#"> <strong>Forget your password</strong></a> </td>
			</tr>
		</table>
	</f:form>
	<!-- Listes des cotes Disponnibles -->	
	
	
	<h4>Your picture</h4>
	<img src="profilePhotoSimpleUser?idUser=${profilePicture.idUser}"/>

</body>
</html>

