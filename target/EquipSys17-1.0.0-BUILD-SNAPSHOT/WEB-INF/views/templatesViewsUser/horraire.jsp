<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>Macabee</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

   <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.css" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.responsive.css" media="all">


    <script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/script.responsive.js"></script>



<style>.art-content .art-postcontent-0 .layout-item-0 { padding-right: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-1 { padding-right: 10px;padding-left: 8px;  }
.art-content .art-postcontent-0 .layout-item-2 { padding: 0px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style>
</head>
<body>
<div id="art-main">
    <div id="art-hmenu-bg" class="art-bar art-nav">
     </div>
<header class="art-header">
<%@ include file="../header.jsp" %>             
</header>
<div class="art-sheet clearfix">
<nav class="art-nav">
    <div class="art-nav-inner">
    <ul class="art-hmenu"><li><a href="index" class="active">Horraire</a></li><li><a href="UserHExam">Horraire Examen</a></li><li><a href="userInformations">Informations</a></li><li><a href="fullUserSearch">Recherche</a></li><li><a href="UserProfileDetails">Profile</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="fullUserRsCotes">Résultat Cotes</a></li><li><a href="fullUserRsRecours">Resultat Recours</a></li><li><a href="job.html">Job</a></li></ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">                                
               <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >
       
       
       
       <f:form modelAttribute="vbootForm" action="userHBtn"  method="post" enctype="multipart/form-data">
  <fieldset class="input" style="border: 0 none;">
    <p id="form-login-username">
     <br> <label for="modlgn_photoprofile">Selectionner votre Fichier Excel :</label> 
              <input    type="file" name="file"  class="inputbox"   style="width:100%" required="true"/>	      
      <f:errors  cssClass="error"></f:errors>
     
    </p>
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile">Titre :</label>
      
      <f:input type="text" name="titreHorraire"  class="inputbox"  path="titreHorraire"   required="true" style="width:100%" /> 
            
       
      <f:errors path="titreHorraire" cssClass="error"></f:errors>
      
    </p>
    
      <p id="form-login-username">
      <label for="modlgn_photoprofile"> Aproved By  :</label>
      
      <f:input type="text" name="AprouvedBuy"  class="inputbox"  path="AprouvedBuy"   required="true" style="width:100%" /> 
      <f:errors path="AprouvedBuy" cssClass="error"></f:errors>
     
    </p>
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Valable du : :</label>
      
      <f:input type="text" name="datefrom"  class="inputbox"  path="datefrom"   required="true" style="width:100%" />  
      <f:errors path="datefrom" cssClass="error"></f:errors>
     
    </p>
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Au : :</label>
      
      <f:input type="text" name="dateTo"  class="inputbox"  path="dateTo"   required="true" style="width:100%" /> 
      <f:errors path="dateTo" cssClass="error"></f:errors>
     
    </p>
    
     <p id="form-login-password">
      <label for="modlgn_passwd">Faculte</label>
      
      <f:select  type="text"  path="faculte"   alt="password" style="width:100%" id="modlgn_passwd" required="true">
      		<option ></option>
            <option >FSTA</option>
            <option>ECONOMI</option>
            <option>BI-OMED</option>
            <option>SANTE</option>
            <option>DROIT</option>
      
      </f:select> 
         <f:errors path="faculte" cssClass="error"></f:errors>
    
    </p>
     
     <p id="form-login-password">
      <label for="modlgn_passwd"> Notification</label>
      
      <f:select  type="text"  path="sendList"   alt="sendList" style="width:100%" id="modlgn_passwd" required="true">
      		<option ></option>
      		 <option>ATousLeMonde</option>
            <option >G0</option>
            <option>G1</option>
            <option>G2</option>
             <option>G3</option>
              <option> TECH1/L1</option>
               <option>TECH2/L2</option>                  
                       <option>Aucun</option>
                       <option></option>
      
      </f:select> 
         <f:errors path="sendList" cssClass="error"></f:errors>
    
    </p>
       <input type="submit" value="Save" name="Submit" class="art-button" />  
    </fieldset>
</f:form>
       
       
       
       
       </div>
    </div>
</div>
<div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%" >
        <p><br></p>
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