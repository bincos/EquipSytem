<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>CatAdd</title>
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
<li><a href="addfeadBack" class="active" > FeadBack</a></li>
 
<li><a href="addPlan">Planifier  Tâches </a></li></ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">Ajouter Maintenance</h2>
                                                                          <div class="errors">
	${exception}
	<!-- Le nom de notre exception -->
</div>
      
                <div class="art-postcontent art-postcontent-0 clearfix">            
             
             
               <p id="form-login-username">
                   	
                   	 <c:if test="${ not empty btnSearch }">  
       	<f:form modelAttribute="searForm" action="searchBtn" method="post">
						
			<tr>
				
					<td><f:errors path="mc" cssClass="error">
						</f:errors></td>
						<td><f:hidden path="page"  value="MaintAdd" hint="Enter un Mot clef"  />
						<f:hidden   path="destinPage"  required="true" hint="Searche"  value="Maintenance"/>
						<input type="submit" value="Select Equip" class="btn btn-success"  style="width:30%"/></td>
					</tr>										

	
	</f:form>
	
	</c:if>
	
	
	
	   <c:if test="${ not empty idEquip }"> 
       <f:form modelAttribute="maintenance" action="addMaintBtn" enctype="multipart/form-data">
  <fieldset class="input" style="border: 0 none;">
    
        <p id="form-login-username">
      <label for="modlgn_photoprofile">ID:</label>      
           
            <f:input type="number" name="idEquip"  class="inputbox"  path="idEquip"  value="${idEquip }"  style="width:60%"  required="true" min="1" readonly="true"/> 
      <f:errors path="idEquip" cssClass="error"></f:errors>
       	
     
           </p>
           
           
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Description Problem :</label>
      <f:textarea  type="text" name="descriptionProblem"  class="inputbox"  path="descriptionProblem"   required="true" style="width:100%" /> 
       <f:errors path="descriptionProblem" cssClass="error"></f:errors>
     
    </p>
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Solution Problem :</label>
      <f:textarea  type="text" name="solutionProblem"  class="inputbox"  path="solutionProblem"   required="true" style="width:100%" /> 
       <f:errors path="solutionProblem" cssClass="error"></f:errors>
     
    </p>
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Nom Technicien :</label>
      <f:input   name="nameUser"  class="inputbox"  path="nameUser"  value="${profileInSession.nameUser}" required="true" style="width:100%" readonly="true" /> 
       <f:errors path="nameUser" cssClass="error"></f:errors>
     
    </p>
        
    
       <input type="submit" value="Save" name="Submit" class="art-button" />  
    </fieldset>
</f:form>
     
     </c:if>
     <h2>${error }</h2>
     
     
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