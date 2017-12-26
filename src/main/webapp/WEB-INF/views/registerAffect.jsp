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
	<h2>Affectation Page</h2>

	
	<h2>LIST AfFECTATIONS Equip</h2>
	  
	<table title="Tableau d'affiche horraire Curent" class="table1"  width="1500px">
<tr><th>Affecter Equip: </th><th><a href="affectEquipAction"> <strong>Affcter Ici</strong></th></tr>
				<tr>
					<th>ID</th>
					<th> Nom Equipment</th>
					<th>Affected To</th>
						<th> Date Debut</th>		
							<th> Date Fin</th>								
				</tr>
				<c:forEach items="${AffectList}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.nameEquip }</td>
						<td>${hr.nameAffected }</td>
						<td>${hr.dateStartAff }</td>
						<td>${hr.dateEndAff }</td>
											</tr>
					
									</c:forEach>

			</table>
	
	<!-- Listes des cotes Disponnibles -->	
</div>
</body>
</html>

