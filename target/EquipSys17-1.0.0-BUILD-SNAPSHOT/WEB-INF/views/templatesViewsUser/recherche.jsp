<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>Rechercher</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

     <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.css" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.responsive.css" media="all">


      <script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/script.responsive.js"></script>




<style>.art-content .art-postcontent-0 .layout-item-0 { padding-right: 10px;padding-left: 10px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style></head>
<body>
<div id="art-main">
    <div id="art-hmenu-bg" class="art-bar art-nav">
    </div>
<header class="art-header">
<%@ include file="../header.jsp" %>
<div class="art-textblock art-object1034248187">
    <form class="art-search" name="Search" action="javascript:void(0)">
    <input type="text" value="">
            <input type="submit" value="Search" name="search" class="art-search-button">
    </form>
</div>
           
                    
</header>
               

<div class="art-sheet clearfix">
<nav class="art-nav">
    <div class="art-nav-inner">
    <ul class="art-hmenu"><li><a href="index" class="">Horraire</a></li><li><a href="UserHExam" class="">Horraire Examen</a></li><li><a href="userInformations" class="">Informations</a></li><li><a href="fullUserSearch" class="active">Recherche</a></li><li><a href="UserProfileDetails">Profile</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="fullUserRsCotes" class="">Résultat Cotes</a></li><li><a href="fullUserRsRecours">Resultat Recours</a></li><li><a href="job">Job</a></li></ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">Rechercher Ici !</h2>
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
                           <div class="errors">
	${exception}
	<!-- Le nom de notre exception -->
</div>
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >
       <div id="infoH"  class="cadre">
	<!-- Comme on va envoyer des fichiers on fait multipart -->
	<f:form modelAttribute="searchForm" action="../fullUserSearchBtn" method="post">
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
						
				
				<f:select  path="promotion" name="promotion" required="true" >
			<option ></option>
            <option > G0</option>
            <option> G1</option>
            <option>G2</option>
            <option>G3</option>
            <option>TECH1</option>
            <option>TECH2</option>
            <option></option>            
         </f:select>			
									
									
									<td><f:errors path="promotion" cssClass="error" /></td>				
							
             <td><f:errors path="param" cssClass="error">
						</f:errors></td>
       
				<td> </td><td><input type="submit" value="Save" /></td>
			</tr>
		</table>
	</f:form>
	</div>
	<p>    <br> </p>
   
    </div>
    </div>
</div>
</div>
       
       
	<div title="Searcheres" style="text-align: center;">
	<table class="table1">
	<c:if test="${searchForm.param=='Etudiant'} "> 
		*************************************************
	</c:if>
	 <c:forEach items="${etudiant}" var="et">
					<tr>
						<td>${et.nameUser }</td>
						<td>${et.firstName }</td>
						<td><a href="rsEtudiantSearch?idSimpleUser=${et.idUser }"><strong>Lire Decription</strong></a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				</c:forEach>
	   
	   <c:forEach items="${recours}" var="rec">
					<tr>
						<td>${rec.promotion }</td>
						<td>${rec.year }</td>
						<td>${rec.description }</td>						
						<td><a href="rsRecoursSearch?idResultatCotes=${rec.idResultatCotes }"><strong>Lire Decription</strong></a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				</c:forEach>
				
				 <c:forEach items="${cotes}" var="rec">
					<tr>
						<td>${rec.promotion }</td>
						<td>${rec.year }</td>	
						<td>${rec.description }</td>						
						<td><a href="rsCotesSearch?idResultatCotes=${rec.idResultatCotes }"><strong>Lire Decription</strong></a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				
				</c:forEach>
				 <c:forEach items="${macabeeListMc}" var="rec">
					<tr>
						<td>${rec.promotion }</td>
						<td>${rec.year }</td>
						<td>${rec.nomCours }</td>	
						<td>${rec.nomEseignant }</td>					
						<td><a href="adminSimpleUser/macabeeDetails?idMacabee=${rec.idMacabee }">Lire Description</a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				</c:forEach>
	
	
				 <c:forEach items="${horraireListeExamens}" var="rec">
					<tr>
						<td>${rec.titreHorraire }</td>
						<td>${rec.anneeAcdem }</td>
						<td>${rec.typHorraire }</td>										
						<td><a href="rsHorraireExamen?idHorraire=${rec.idHorraire }">Lire Description</a></td>
						
					</tr>
					<!-- ${op } quqnd on fqit celq c'est lq méthodes tostring qui est appellé par défaut -->
				</c:forEach>
	</table>
	</div>                         
                

</article></div>
                        <div class="art-layout-cell art-sidebar2"></div>
                    </div>
                </div>
            </div>
    </div>
<footer class="art-footer">
  <div class="art-footer-inner">
<%@ include file="../footer.jsp" %>
  </div>
</footer>

</div>


</body></html>