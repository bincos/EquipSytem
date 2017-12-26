<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>Affectation</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

   <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.css" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.responsive.css" media="all">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/bootstrap.min.css" media="all">
    


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
    <ul class="art-hmenu"><li><a href="index" >Tâches</a></li> <li><a href="equipList" >List Equip</a></li> <li><a href="affectEquip">Affectation</a></li> 
     <li><a href="besoins" >List Besoins</a></li> <li><a href="UserList">List User</a></li>
     <li><a href="search">Rechercher</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="registerEquip">Ajouter Equip</a></li>   <li><a href="registerCat">Ajouter Cat</a></li>  <li><a href="addBesion" >Ajouter Besoin</a></li>
<li><a href=registerUser>Ajouter User</a></li>  <li><a href="addfeadBack">  FeadBack</a></li> <li><a href="addMaintenance">Maintenance</a></li>

 <li><a href="affectEquipAction" class="active">Affecter Equip</a></li>  
<li><a href="trackIndex">Tracker Equip</a></li>   <li><a href="addPlan">Planifier  Tâches </a></li>  <li><a href="getReportIndex">Reporting</a> </li> </ul>
                
        </div>
</div></div>
                          <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >  
     
    	
       			
    		   																										
			<table title="Tableau Confirmation affectation"  class="art-article" border="0" cellspacing="0" cellpadding="0">
			
		
					<tr>
					<td> ID Equipment :</td> <td>${equipAffSession.id }</td></tr>
						<tr>
					<td> Nom Equipment  :</td><td>${equipAffSession.nameEquip }</td> </tr>					
					<tr><td> Model Equipment :</td><td>${equipAffSession.modelEquip }</td></tr>
					<tr><td> Num Serie  :</td>	<td>${equipAffSession.numSerie }</td></tr>
					<tr><td> Description :</td><td>${equipAffSession.decriptionEquip }</td></tr>	
					<tr><td> Annee Fabrication :</td><td>${equipAffSession.yearFabrication }</td></tr>	
									
					<tr><td> Photo :</td><td> </td> </tr>
					<tr><img src="photoEquip?idEquip=${equipAffSession.id }"  class="img-circle" /> </tr>
					
						<f:form modelAttribute="affectation" action="affectValidationBtn" method="post" enctype="multipart/form-data">
						<tr>
						
						<td> Decriver Affectaction:</td><td>  <f:textarea path="descriptionAff"   required="true"/>  </td></tr>
					
					<tr><td> Equipment Affecter à  :</td><td> <h3>${UserName }</h3></td>
					<td><f:hidden   path="idUser"  required="true" hint="Searche"  value="${idUser }"/></td></tr>					
					<tr><td> Date Fin  :</td><td><f:input path="dateEndAff" type="date" required="required"/></td></tr>
					
					<tr>					
									<td> 
					
					 <f:hidden path="nameEquip" value="${affectDetails.nameEquip }" readonly="true"/>
					<f:hidden path="idEquip" value="${idEquip }"/>
					<input type="submit" value="CONFIRMER"   class="art-button"/>
				</td><td>   
					
					 </td></tr>	
									</f:form>		
						</table>
					
						<h3> ${error }</h3>
   
    </div>
    </div>
</div>

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