<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserInfo</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/CSS/style1.css">
</head>
<body>
<h2>Confirmation Affectation</h2>

<div class="errors">
	${exception}
	<!-- Le nom de notre exception -->
</div>

	<div title="Rs_Cotes_details">
	<table>
		
					<tr>
					<td> ID Equipment :</td> <td>${affectDetails.id }</td></tr>
						<tr>
					<td> Nom Equipment  :</td><td>${affectDetails.nameEquip }</td> </tr>					
					<tr><td> Model Equipment :</td><td>${affectDetails.modelEquip }</td></tr>
					<tr><td> Num Serie  :</td>	<td>${affectDetails.numSerie }</td></tr>
					<tr><td> Description :</td><td>${affectDetails.decriptionEquip }</td></tr>	
					<tr><td> Annee Fabrication :</td><td>${affectDetails.yearFabrication }</td></tr>	
									
					<tr><td> Photo :</td><td><img src="photoEquip?idEquip=${affectDetails.id }"/></td> </tr>
						<f:form modelAttribute="affectation" action="affectValidationBtn" method="post" enctype="multipart/form-data">
						<tr>
						
						<td> Decriver Affectaction:</td><td>  <f:input path="descriptionAff"   required="true"/>  </td></tr>
					
					<tr><td> Enter le ID ou le Nom  de la personne Pour Affectation  :</td><td></td></tr>
					
					<tr>
					
					
					<td> 
					<f:input path="nameAffected"   required="true"/>  <f:hidden path="nameEquip" value="${affectDetails.id }"/>
					<input type="submit" value="CONFIRMER" />
				</td><td>   
					
					 </td></tr>	
									</f:form>		
						</table>
	</div>
	
</body>
</html>