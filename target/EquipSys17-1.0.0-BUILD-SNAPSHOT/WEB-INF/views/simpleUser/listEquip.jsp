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
     
<script type="text/javascript">
function confirmDelete(delForm, delUrl) { // <--- changed here
    if (confirm("Are you sure ?")) {
        delForm.action = delUrl;          // <--- changed here
        return true;                      // <--- changed here
    }
    return false;                         // <--- changed here
}
</script>



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
    <ul class="art-hmenu" >  <li><a href="index" >Tâches</a></li>  <li><a href="equipList" class="active">List Equip</a></li> <li><a href="affectEquip">Affectation</a></li>
       
    <li><a href="besoins" >List Besoins</a></li> <li><a href="UserList">List User</a></li> <li><a href="search">Rechercher</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="registerEquip">Ajouter Equip</a></li>   <li><a href="registerCat">Ajouter Cat</a></li>
 <li><a href="addBesion" >Ajouter Besoin</a></li>   <li><a href="addfeadBack"> FeadBack</a></li>
 
 <li><a href="affectEquipAction">Affecter Equip</a></li>
 
<li><a href=registerUser>Ajouter User</a></li> <li><a href="addMaintenance">Maintenance</a></li>  

<li><a href="trackIndex">Tracker Equip</a> </li>  
<li><a href="addPlan">Planifier  Tâches </a></li>
<li><a href="getReportIndex">Reporting</a> </li> </ul>

                
        </div>
</div></div>
                          <div class="art-layout-cell art-content"><article class="art-post art-article">
                                
                                               
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >    
       				
       				
       						   								 <a href="#" class="art-button">${size }  Aviables</a>	 <a href="faultyList" class="art-button"> <strong>Faulty</strong> </a>	 <a href="equipList" class="art-button"> <strong>Aviable</strong> </a>														
			
			<c:if test="${ not empty EquipmentList }">
			<table title="Tableau d'affiche ListEquip Curent"  class="art-article" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<th  >ID</th>
					<th>Non Equipment</th>
					<th>Model</th>
					<th>Num Serie</th>	
					<th>Image</th>	
					<th>Action</th>	
					<th class="danger">DEL</th>					
				</tr>
				
			
													
									<c:forEach items="${EquipmentList}" var="hr">
					<tr>
						<td >${hr.id }</td>
						<td >${hr.nameEquip }</td>
						<td>${hr.modelEquip }</td>
						<td>${hr.numSerie }</td>
							<td><img src="photoEquip?idEquip=${hr.id }" class="img-circle"  height="100px"/></td>
											
					
					<td>
						<f:form modelAttribute="editForm" action="editEquip" method="post">
		
					<f:hidden   path="idEquip"  required="true" hint="Searche"  value="${hr.id }"/>
					
						<input type="submit" value="EDIT" class="art-button">	
												
	</f:form>						
					
							
							</td>
							<td>
													
							<f:form modelAttribute="editForm" action="editEquip"  method="post">
		
					<f:hidden   path="idEquip"  required="true" hint="Searche"  value="${hr.id }"/>
					
						<input type="submit" value="DELL" class="art-button" onclick="if (confirm('Are you sure you want to delete?')) { form.action='delEquip?idPEquip=${hr.id } ';} else { return false; }">	
												
	</f:form>						
					
								
							</td>
							
							</tr>
									</c:forEach>

			</table>
			
			</c:if>
			<h3><strong>${error}</strong>	</h3>	
			
				<c:if test="${ not empty nombrePage }">				
								
									<c:forEach  begin="0"  end="${nombrePage -1}" var="hr">	
									<c:choose>
										<c:when test="${hr== page}">
									<span> Page  ${hr}</span>	
									</c:when>	
									<c:otherwise>
																		
									
												<span> <a href="equipListParam?idPage=${hr}"  class="btn btn-success">Page  ${hr}</a></span>	
												
												</c:otherwise>		
									</c:choose>
									</c:forEach>
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
  
   <%@ include file= "../footer.jsp" %>
	
  
  </div>
</footer>

</div>


</body></html>