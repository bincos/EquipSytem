<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>Besoin</title>
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
    <ul class="art-hmenu"> <li><a href="index" >Tâches</a></li> <li><a href="equipList" >List Equip</a></li> <li><a href="affectEquip">List Affectation</a></li>
    <li><a href="besoins" class="active">List Besoins</a></li> 
    <li><a href="UserList">List User</a></li>   <li><a href="search">Rechercher</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="registerEquip">Ajouter Equip</a></li>   <li><a href="registerCat">Ajouter Cat</a></li>
 <li><a href="addBesion" >Ajouter Besoin</a></li>
<li><a href=registerUser>Ajouter User</a></li>
 <li><a href="addfeadBack">  FeadBack</a></li>
 <li><a href="addMaintenance"> Maintenance</a></li>
 <li><a href="affectEquipAction">Affecter Equip</a></li>
 
<li><a href="trackIndex">Tracker Equip</a></li> 
<li><a href="addPlan">Planifier  Tâches </a></li>  
 <li><a href="getReportIndex">Reporting</a> </li> </ul>
                
        </div>  
</div></div>
                          <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                          <a href="#" class="art-button">${size } Besoins in Pending</a> 
                                           
                                            
                                           <f:form modelAttribute="searForm" action="besoinsBtn" enctype="multipart/form-data">
   
            <f:input type="date" name="date1"  class="inputbox"  path="date1"  style="width: 25%"  required="true"/>   <f:input type="date" name="mc"  class="inputbox"  path="date2"    style="width: 25%"  required="true"/> 
     	<f:hidden   path="page"  required="true" hint="Searche"  value="filtrerB"/>
    
       <input type="submit" value="Filter" name="Submit" class="art-button" />  
   
</f:form>
                                         
                <div class="art-postcontent art-postcontent-0 clearfix">
                
                <div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >    
    
    
    <h3><strong>${error}</strong>	</h3>	
    <c:if test="${ not empty besoinDetails }">
    
    
       						   																						
			<table title="Tableau d'affiche ListEquip Curent"  class="art-article" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<th  >ID</th>
					<th>Date</th>
					<th>From</th>	
				
					<th>Description</th>					
					<th>Status</th>	
					<th>Action</th>					
				</tr>
				
									
									<c:forEach items="${besoinDetails}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.date }</td>
						<td>${hr.nameUser }</td>
					
						<td>${hr.description }</td>
						<td>Pending</td>
																	
					
					<td>
						<f:form modelAttribute="searForm" action="besoinsBtn" method="post">
		
					<f:hidden   path="idBesoin" required="true" hint="Searche"  value="${hr.id }"/>
						<f:hidden   path="page"  required="true" hint="Searche"  value="replyBesoin"/>
					
						<input type="submit" value="REPONDRE"  class="btn btn-primary" />									
	</f:form>											
							
							</td>
							</tr>
									</c:forEach>

			</table>
			
	</c:if>		
   
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