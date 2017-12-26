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
    <ul class="art-hmenu"><li><a href="index" >T�ches</a></li> <li><a href="equipList" >List Equip</a></li> <li><a href="affectEquip">Affectation</a></li>
     <li><a href="besoins" >List Besoins</a></li><li><a href="UserList">List User</a></li>
     
   <li><a href="search">Rechercher</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">            
        
         <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="affectEquip" >Ajouter Equip</a></li>   <li><a href="registerCat" >Ajouter Cat</a></li>  <li><a href="addBesion" >Ajouter Besoin</a></li>
<li><a href=registerUser>Ajouter User</a></li> <li><a href="addBesion" class="active">Ajouter Besoin</a></li> <li><a href="addfeadBack"> FeadBack</a></li>
 <li><a href="addMaintenance">Maintenance</a></li>

 <li><a href="affectEquipAction">Affecter Equip</a></li>  
<li><a href="trackIndex">Tracker Equip</a></li>  <li><a href="addPlan">Planifier  T�ches </a></li> <li><a href="getReportIndex">Reporting</a> </li> </ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">PLANIFIER UNE TACHE</h2>
                                                                          <div class="errors">
                    
	${exception}
	<!-- Le nom de notre exception -->
</div>
      
                <div class="art-postcontent art-postcontent-0 clearfix">            
             
       <f:form modelAttribute="addPlan" action="addPlanBtn" enctype="multipart/form-data">
  <fieldset class="input" style="border: 0 none;">
   
        <p id="form-login-username">
      <label for="modlgn_photoprofile">Titre  :</label>
      
      
           
            <f:input type="text" name="title"  class="inputbox"  path="title"    required="true"/> 
      <f:errors path="title" cssClass="error"></f:errors>
       
      
      
    </p>
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Type :</label>
      
      <f:select  path="type"  name="type" required="true" class="select">
<option></option>	
<option>Mainetnance</option>	
<option>Affectation</option>	
<option>Divers</option>		
<option></option>	
         
                 
            
        </f:select>
     
       <f:errors path="type" cssClass="error"></f:errors>
     
    </p>
    
    
        
         <p id="form-login-username">
      <label for="modlgn_photoprofile"> Description :</label>
      <f:textarea  type="text" name="description"  class="inputbox"  path="description"   required="true" style="width:100%" /> 
       <f:errors path="description" cssClass="error"></f:errors>
     
    </p>
    
    
    
         <p id="form-login-username">
      <label for="modlgn_photoprofile"> Date :</label>
       <f:input name="date" type="date" class="inputbox"  path="date"  required="true" />  
        <f:hidden name="nameUser"  class="inputbox"  path="nameUser"  value="${profileInSession.nameUser}" required="true" style="width:100%" readonly="true"/> 
        <f:hidden  name="idUser"  class="inputbox"  path="idUser"    value="${profileInSession.id}" required="true" style="width:100%" />
         <f:hidden  name="email"  class="inputbox"  path="email"    value="${profileInSession.email}" required="true" style="width:100%" /> 
         
     
       <f:errors path="description" cssClass="error"></f:errors>
     
    </p>
       <input type="submit" value="PLANIFIER" name="Submit"  class="btn btn-primary" />  
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