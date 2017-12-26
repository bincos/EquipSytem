<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>Besoins</title>
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
     <li><a href="besoins"  class="active" >List Besoins</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">            
        
         <div class="art-vmenublockcontent">
<ul class="art-vmenu"> <li><a href="addBesion" >Ajouter Besoin</a></li>
<li><a href="addfeadBack"> FeadBack</a></li>
 
<li><a href="addPlan">Planifier  Tâches </a></li>  </ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">Ajouter un Besoin</h2>
                                                                          <div class="errors">
                    
	${exception}
	<!-- Le nom de notre exception -->
</div>
      
                <div class="art-postcontent art-postcontent-0 clearfix">            
             
       <f:form modelAttribute="addBesion" action="addBesionBtn" enctype="multipart/form-data">
  <fieldset class="input" style="border: 0 none;">
   
        <p id="form-login-username">
      <label for="modlgn_photoprofile">Titre  :</label>
      
      
           
            <f:input type="text" name="title"  class="inputbox"  path="title"    required="true"/> 
      <f:errors path="title" cssClass="error"></f:errors>
       
      
      
    </p>
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Category :</label>
      
      <f:select  path="category"  name="category" required="true" class="select">
<option></option>		
           <c:forEach items="${CategoryList}" var="hr">
               
					<option>${hr.nameCat }</option>
						 				
									</c:forEach>
                 
            
        </f:select>
     
       <f:errors path="category" cssClass="error"></f:errors>
     
    </p>
    
    
        
         <p id="form-login-username">
      <label for="modlgn_photoprofile"> Description :</label>
      <f:input  type="text" name="description"  class="inputbox"  path="description"   required="true" style="width:100%" /> 
       <f:errors path="description" cssClass="error"></f:errors>
     
    </p>
    
     <p id="form-login-username">
      <label for="modlgn_photoprofile"> Motif :</label>
      <f:input  type="text" name="motif"  class="inputbox"  path="motif"   required="true" style="width:100%" /> 
       <f:errors path="motif" cssClass="error"></f:errors>
     
    
      <f:input name="nameUser"  class="inputbox"  path="nameUser"  value="${profileInSession.nameUser}" required="true" style="width:100%" readonly="true"/> 
        <f:hidden  name="idUser"  class="inputbox"  path="idUser"    value="${profileInSession.id}" required="true" style="width:100%" /> 
          <f:hidden  name="email"  class="inputbox"  path="email"    value="${profileInSession.email}" required="true" style="width:100%" /> 
    </p>
       <input type="submit" value="Save" name="Submit" class="art-button" />  
    </fieldset>
</f:form>
     
          
     
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