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
	<h2>Register  Equip</h2>
	<f:form modelAttribute="registerEquip" action="registerBtn" method="post" enctype="multipart/form-data">
		<table>	
			<tr>
				<td>Profile Picture : </td>
				<!-- On Fait Un Test Pour LÁFFICHAGE Img -->
							<td><input  type="file" name="file" />		
							<td><f:errors path="photo" cssClass="error">
						</f:errors></td>					
		
			</tr>					
			<tr>
				<td>NameEquip :</td>				
					<td><f:input path="nameEquip"   required="true"/>
					
					</td>
					<td><f:errors path="nameEquip" cssClass="error">
						</f:errors></td>
					</tr>
					
					<tr>
				<td> modelEquip:</td>
				
					<td><f:input type="text" path="modelEquip"   required="true"/>
					</td>
					<td><f:errors path="modelEquip" cssClass="error"/>
						</td>
					</tr>
						<tr>
				<td> numSerie:</td>
				
					<td><f:input type="text" path="numSerie"   required="true"/>
					</td>
					<td><f:errors path="numSerie" cssClass="error"/>
						</td>
					</tr>
					<tr><td>Category</td>
					<td><f:select  path="categoryName" type="text" name="promotion" required="true">
			<option ></option>
           <c:forEach items="${CategoryList}" var="hr">
					<option>${hr.nameCat }</option>
						 
					
									</c:forEach>
                   <option></option>
            
        </f:select></td> <td> Or Create a category Firts:<a href="registerCat"> <strong>Category</strong></a></td>
					
					</tr>
					<tr>
					
			<td> description :</td>
				
					<td><f:input type="text"  path="decriptionEquip"   required="true" />
					</td>
					<td><f:errors path="decriptionEquip" cssClass="error"/>
						</td>
					</tr>			
				
					<tr>
				<td>yearFabrication:</td>
				
					<td><f:input path="yearFabrication"   required="true"/>
					
					</td>
					<td><f:errors path="yearFabrication" cssClass="error"  >
						</f:errors></td>
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
					<th>Non Equipment</th>
					<th>Model</th>
						<th>Num Serie</th>										
				</tr>
				<c:forEach items="${EquipmentList}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.nameEquip }</td>
						<td>${hr.modelEquip }</td>
						<td>${hr.numSerie }</td>
							<td><img src="photoEquip?idEquip=${hr.id }"/></td>
											</tr>
					
									</c:forEach>

			</table>
	
	<!-- Listes des cotes Disponnibles -->	
</div>
</body>
</html>

