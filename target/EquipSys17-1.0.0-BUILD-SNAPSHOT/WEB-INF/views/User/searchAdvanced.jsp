<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>index</title>
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
    <ul class="art-hmenu"><li><a href="index"  >Tâches</a></li>  <li><a href="equipList" >Affectation</a></li> 
     <li><a href="besoins"  >List Besoins</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="addBesion"  >Ajouter Besoin</a></li>
<li><a href="addfeadBack"  > FeadBack</a></li>
 
<li><a href="addPlan" class="active">Planifier  Tâches </a></li> </ul>
                
        </div>
</div></div>
                          <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >    
       						   																						
			<f:form modelAttribute="searForm" action="searchBtn" method="post">
		<table class="art-article" border="0" cellspacing="0" cellpadding="0">						
			<tr>
				<td>Mot Chef :</td>
				
					<td><f:input path="mc"  required="true" hint="Searche" minlength="1"  />
					
					</td>
					
			
       <td><f:hidden path="page"  value="MaintAddRs" hint="Enter un Mot clef"  />
       <f:input   path="destinPage"  required="true" hint="Searche"  value="${destinPage }" readonly="true"/>
       <f:hidden   path="idEquip"  hint="Searche"  value="${idEquip }" readonly="true"/>
       
				<td> </td><td><input type="submit" value="Rechercher"  class="art-button"/></td>
			</tr>
		</table>
	</f:form>
	
	
			
			<h3><strong>${error}</strong>	</h3>	
			
			<c:if test="${ not empty searcheP }">
    
    <table title="Tableau d'affiche ListEquip Curent"  class="art-article" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<th>ID</th>
					<th>Details</th>
										
					<th>Action</th>	
									
				</tr>
				
									
									<c:forEach items="${searRs}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.nameEquip } ->
						${hr.modelEquip } -> ${hr.decriptionEquip }</td>												
					
					<td>
					
						<f:form modelAttribute="searForm" action="rsDetails" method="post">
		
					<f:hidden   path="idEquip"  required="true" hint="Searche"  value="${hr.id }"/>
					<f:hidden   path="destinPage"  required="true" hint="Searche"  value="${destinPage }"/>
					<f:hidden   path="param"  required="true" hint="Searche"  value="equip"/>
					
						<input type="submit" value="Ouvrir"  class="art-button"/>									
	</f:form>											
							
							</td>
							
							
							</tr>
							
									</c:forEach>

			</table>
     
    </c:if>
    
    
    <!-- IF User details -->
    <c:if test="${ not empty searcheUser }">
    
     <table title="Tableau rsUser"  class="art-article" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<th>ID</th>
					<th>Details</th>
										
					<th>Action</th>	
									
				</tr>
				
									
									<c:forEach items="${searRsUser}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.nameUser } [-->]
						${hr.email } [-->]${hr.phoneUser }</td>												
					
					<td>
						<f:form modelAttribute="searForm" action="rsDetails" method="post">
		
					<f:hidden   path="idEquip"  required="true" hint="Searche"  value="${hr.id }"/>
					<f:hidden   path="param"  required="true" hint="Searche"  value="user"/>
					
						<input type="submit" value="Ouvrir"   class="art-button"/>									
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