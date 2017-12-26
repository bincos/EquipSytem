<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%> 
<head>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style1.css">
</head>

<body>
<div class="errors">
	${exception}
	<!-- Le nom de notre exception -->
</div>
<div id="formCat"  class="cadre">
	<!-- Comme on va envoyer des fichiers on fait multipart -->
	
	<f:form modelAttribute="fileForm" action="excelTest" method="post" enctype="multipart/form-data">
		<table>
		
			
				<tr>
				<td>Selectionner Votre Fichier Excel : </td>
				<!-- On Fait Un Test Pour LÁFFICHAGE Img -->
							<td><input type="file" name="file" />							
		
			</tr>

			<tr>
				<td>Titre :</td>
				
					<td><f:input  typ="text" path="titreHorraire" />
					
					</td>
					<td><f:errors path="titreHorraire" cssClass="error">
						</f:errors></td>
					</tr>
			
			<tr>
				<td>Aproved By :</td>
				
					<td><f:input path="AprouvedBuy" />
					
					</td>
					<td><f:errors path="AprouvedBuy" cssClass="error">
						</f:errors></td>
					</tr>
					
					<tr>
				<td> Valable du :</td>
				
					<td><f:input path="datefrom" />
					</td>
					<td><f:errors path="datefrom" cssClass="error">
						</f:errors></td>
					</tr>
					
					<tr>
				<td> Au :</td>
				
					<td><f:input path="dateTo" />
					</td>
					<td><f:errors path="dateTo" cssClass="error">
						</f:errors></td>
					</tr>
					<tr>
					
				<td></td> <td><input type="submit" value="Save" /></td>
			</tr>
		</table>

	</f:form>
</div>


</body>




