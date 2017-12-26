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
    <a href="#">EquipSys</a>
</h1>
<h2 class="art-slogan">Log and get granted</h2>
                
</header>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-content"><div class="art-block clearfix">
        <div class="art-blockheader">
            <h3 class="t">Login Form</h3>
               ${loginStatus}  
                <c:if test="${not empty error}">    
  <div class="errorblock">  
      Your login attempt was not successful, try again.  
 Caused :  
   ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}   
   
  </div> 
 </c:if>

        </div>
        
        <div class="art-blockcontent"><form  name="f"  action="j_spring_security_check" method="post">
  <fieldset class="input" style="border: 0 none;">
    <p id="form-login-username">
      <label for="modlgn_username">Username</label>
      <br />
      <input type="text" name="j_username" class="inputbox" alt="username" style="width:100%" id="modlgn_username">
     
    </p>
    <p id="form-login-password">
      <label for="modlgn_passwd">Password</label>
      <br />
      <input type="password" name="j_password" class="inputbox" size="18" alt="password" style="width:100%" id="modlgn_passwd">
    
    </p>
    <p id="form-login-remember">
      <label class="art-checkbox">
      <input type="checkbox" id="modlgn_remember" name="remember" value="yes" alt="Remember Me" />Remember Me
      </label>
    </p>
    
    <input type="submit" value="Login" name="Submit" class="art-button" />    
  </fieldset>
  <ul>
    <li>
      <a href="#">Forgot your password?</a>
    </li>
    <li>
      <a href="#">Forgot your username?</a>
    </li>
    
  </ul>
</form></div>
</div><article class="art-post art-article">
                                
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 33%" >
        &nbsp; &nbsp; &nbsp;
    </div><div class="art-layout-cell layout-item-1" style="width: 34%" >
       
    </div><div class="art-layout-cell layout-item-2" style="width: 33%" >
        <br>
    </div>
    </div>
</div>
</div> 
</article></div>
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