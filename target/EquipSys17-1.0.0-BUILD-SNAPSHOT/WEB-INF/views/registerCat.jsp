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
	<!-- Comme on va envoyer des fichiers on fait multipart -->
	<h2>Register  Cat</h2>
	<f:form modelAttribute="registerCat" action="registerCatBtn" method="post" enctype="multipart/form-data">
		<table>	
			<tr>
				<td>Profile Cat pic : </td>
				<!-- On Fait Un Test Pour LÁFFICHAGE Img -->
							<td><input  type="file" name="file" />		
							<td><f:errors path="photo" cssClass="error">
						</f:errors></td>					
		
			</tr>					
			<tr>
				<td>NameCategory :</td>				
					<td><f:input path="nameCat"   required="true"/>
					
					</td>
					<td><f:errors path="nameCat" cssClass="error">
						</f:errors></td>
					</tr>
					
					<td> description :</td>
				
					<td><f:input type="text"  path="descriptionCat"   required="true" />
					</td>
					<td><f:errors path="descriptionCat" cssClass="error"/>
						</td>
					</tr>			
											
						<tr>
					
				<td></td> <td><input type="submit" value="Save" /></td>
				
			</tr>
				<tr>					
				
			</tr>
		</table>
	</f:form>
	
	<table title="Tableau d'affiche horraire Curent" class="table1"  width="1500px">

				<tr>
					<th  >ID</th>
					<th>Non Category</th>
					<th>Description</th>
						<th>Photo</th>										
				</tr>
				<c:forEach items="${CategoryList}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.nameCat }</td>
						<td>${hr.descriptionCat }</td>
						
							<td><img src="photoCat?idCat=${hr.id }"/></td>
											</tr>
					
									</c:forEach>

			</table>
	
	<!-- Listes des cotes Disponnibles -->	
</div>
</body>
</html>

