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

	
	<h2>LIST Equippement  Affectable</h2>
	 <table title="Tableau d'affiche horraire Curent" class="table1"  width="60%">

				<tr>
					<th  >ID</th>
					<th>Non Equipment</th>
					<th>Model</th>
					<th>Date Fabrication</th>
					
					<th>Action</th>					
													
				</tr>
				<c:forEach items="${equipAviable}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.nameEquip }</td>
						<td>${hr.modelEquip }</td>	
						<td>${hr.yearFabrication }</td>						
					
							<td><img src="photoEquip?idEquip=${hr.id }"/></td>
							<td><a href="affectActionConf?idEquip=${hr.id }"> <strong>Affecter</strong></a></td>
											</tr>
					
									</c:forEach>

			</table>
	
	<!-- Listes des cotes Disponnibles -->	
</div>
</body>
</html>

