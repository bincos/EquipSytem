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
    <a href="#">vBoot</a>
</h1>
<h2 class="art-slogan">Excersize To Get In Shape</h2>

<nav class="art-nav">
    <div class="art-nav-inner">
    <ul class="art-hmenu"><li><a href="main.html" class="active">Main</a></li></ul> 
        </div>
    </nav>

                    
</header>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><div class="art-block clearfix">
        <div class="art-blockheader">
            <h3 class="t">Login Form</h3>
             <c:if test="${not empty error}">  
  <div class="errorblock">  
   Your Register attempt was not successful, try again.  
 Caused :  
   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}  
   
  </div> 

 </c:if>
        </div>
        
        <div class="art-blockcontent">
       <f:form modelAttribute="registerUser" action="registerFullUserBtn" method="post" >
  <fieldset class="input" style="border: 0 none;">
   
    <p id="form-login-username">
      <label for="modlgn_photoprofile">  Name :</label>
      
      <f:input type="text" name="nameUser"  class="inputbox"  path="nameUser"   required="true" style="width:100%" />  
      <f:errors path="nameUser" cssClass="error"></f:errors>
     
    </p>
    
    <p id="form-login-username">
      <label for="modlgn_photoprofile"> User Name :</label>
      
      <f:input type="text" name="userName"  class="inputbox"  path="userName"   required="true" style="width:100%" />  
      <f:errors path="userName" cssClass="error"></f:errors>
     
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
      <label for="modlgn_photoprofile"> Faculté  :</label>
      
      <f:input type="text" name="faculte"  class="inputbox"  path="faculte"   required="true" style="width:100%" />  
      <f:errors path="faculte" cssClass="error"></f:errors>
     
    </p>
      <input type="submit" value="Save" name="Submit" class="art-button" />  
    </fieldset>
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