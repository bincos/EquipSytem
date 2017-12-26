<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.1.0.60046 -->
    <meta charset="utf-8">
    <title>Main</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/loginStyle.css" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/loginStyle.responsive.css" media="all">


      <script src="<%=request.getContextPath()%>/resources/js/loginJquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/loginScript.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/loginScript.responsive.js"></script>


<style>.art-content .art-postcontent-0 .layout-item-0 { padding-right: 10px;padding-left: 10px;  }
.art-content .art-postcontent-0 .layout-item-1 { padding-right: 10px;padding-left: 8px;  }
.art-content .art-postcontent-0 .layout-item-2 { padding: 0px;  }
.ie7 .art-post .art-layout-cell {border:none !important; padding:0 !important; }
.ie6 .art-post .art-layout-cell {border:none !important; padding:0 !important; }

</style></head>
<body onload='document.f.j_username.focus();'>
<div id="art-main">
    <div id="art-hmenu-bg" class="art-bar art-nav">
    </div>
    <div class="art-sheet clearfix">
<header class="art-header">

    <div class="art-shapes">
        
            </div>

<h1 class="art-headline">
    <a href="#">Equip Sys</a>
</h1>
<h2 class="art-slogan">Enregistrer un Utulisateur</h2>



                    
</header>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><div class="art-block clearfix">
        <div class="art-blockheader">
            
             <c:if test="${not empty error}">  
  <div class="errorblock">  
   Your Register attempt was not successful, try again.  
 Caused :  
   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}  
   
  </div> 

 </c:if>
        </div>
        
        <div class="art-blockcontent">
       <f:form modelAttribute="register" action="registerBtn" method="post" enctype="multipart/form-data">
  <fieldset class="input" style="border: 0 none;">
    <p id="form-login-username">
     <br> <label for="modlgn_photoprofile">Profile Picture :</label> 
              <input    type="file" name="file"  class="inputbox"   style="width:100%"/>	      
      <f:errors path="photo" cssClass="error"></f:errors>
     
    </p>
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile">Name :</label>
      
      <f:input type="text" name="nameUser"  class="inputbox"  path="nameUser"   required="true" style="width:100%" /> 
            
       
      <f:errors path="nameUser" cssClass="error"></f:errors>
      
    </p>
    
    
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> Email :</label>
      
      <f:input type="email" name="email"  class="inputbox"  path="email"   required="true" style="width:100%" /> 
      <f:errors path="email" cssClass="error"></f:errors>
     
    </p>
    
     <p id="form-login-password">
      <label for="modlgn_passwd">Password</label>
      
      <f:input type="password"  path="password"   alt="password" style="width:100%" id="modlgn_passwd"/> 
         <f:errors path="password" cssClass="error"></f:errors>
    
    </p>
     
       <p id="form-login-username">
      <label for="modlgn_photoprofile"> Phone number :</label>
      
      <f:input type="text" name="phoneUser"  class="inputbox"  path="phoneUser"   minlength="9" required="true" style="width:100%" />  
      <f:errors path="phoneUser" cssClass="error"></f:errors>
     
    </p>
    
     <p id="form-login-username">
      <label for="modlgn_photoprofile"> Function :</label>
      
      <f:input type="text" name="function"  class="inputbox"  path="function"   minlength="4" required="true" style="width:100%" />  
      <f:errors path="function" cssClass="error"></f:errors>
     
    </p>
    
           <p id="form-login-username">
      <label for="modlgn_photoprofile"> Adress  :</label>
      
      <f:input type="text" name="adressUser"  class="inputbox"  path="adressUser"   required="true" style="width:100%" />  
      <f:errors path="adressUser" cssClass="error"></f:errors>
      </p>
     
    
    
   </fieldset>
    <input type="submit" value="Save" name="Submit" class="art-button" />   <a href="login" class="active">LogIn</a>
 
</f:form></div>
</div>

</div>
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