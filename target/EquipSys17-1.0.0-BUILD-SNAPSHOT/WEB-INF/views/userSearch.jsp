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
<h2>Hello Search GateWay</h2>

<div class="errors">
	${exception}
	<!-- Le nom de notre exception -->
</div>
<div id="infoH"  class="cadre">
	<!-- Comme on va envoyer des fichiers on fait multipart -->
	<f:form modelAttribute="searchForm" action="userSearchBtn" method="post">
		<table>						
			<tr>
				<td>Mot Chef :</td>
				
					<td><f:input path="mc"  required="true" hint="Searche" minlength="3" />
					
					</td>
					<td><f:errors path="mc" cssClass="error">
						</f:errors></td>
					</tr>										
					
<tr>  <td> ParaMiter </td>
<td>
<f:select  path="param" name="param" required="true" >
			<option ></option>
            <option >Etudiant</option>
            <option>Recours</option>
            <option>ResultatCote</option>
            <option>HorraireExamen</option>
            <option>Information</option>
            <option>Macabee</option>
            <option></option>            
        </f:select>
        
        </td>
             <td><f:errors path="param" cssClass="error">
						</f:errors></td>
       
				<td> </td><td><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</f:form>
	</div>
	
	<div title="Searcheres">
	<table>
	<c:if test="${searchForm.param=='Etudiant'} "> 
		*************************************************
	</c:if>
	 <c:forEach items="${etudiant}" var="et">
					<tr>
						<td>${et.nameUser }</td>
						<td>${et.firstName }</td>
						<td><a href="rsEtudiantSearch?idSimpleUser=${et.idUser }">ReadfullInfo</a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				</c:forEach>
	   
	   <c:forEach items="${recours}" var="rec">
					<tr>
						<td>${rec.promotion }</td>
						<td>${rec.description }</td>						
						<td><a href="rsRecoursSearch?idResultatCotes=${rec.idResultatCotes }">ReadfullInfo</a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				</c:forEach>
				
				 <c:forEach items="${cotes}" var="rec">
					<tr>
						<td>${rec.promotion }</td>
						<td>${rec.year }</td>						
						<td><a href="rsCotesSearch?idResultatCotes=${rec.idResultatCotes }">ReadfullInfo</a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				</c:forEach>
	
	</table>
	</div>
	
</body>
</html>