<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>Rs Recours Liste</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

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
    <ul class="art-hmenu"><li><a href="index" class="">Horraire</a></li><li><a href="UserHExam" class="">Horraire Examen</a></li><li><a href="userInformations" class="">Informations</a></li><li><a href="fullUserSearch" class="">Recherche</a></li><li><a href="UserProfileDetails" class="">Profile</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="fullUserRsCotes" class="">Résultat Cotes</a></li><li><a href="simpleUserRsRecours" class="active">Resultat Recours</a></li><li><a href="job">Job</a></li></ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">Rs Recours Liste Here Guy</h2>
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><p> <a href="fullUserAddRsRecours" ><h3>Ajouter Recours Ic..!!</h3></a></p>
                
                
                <p><table  class="art-article" border="0" cellspacing="0" cellpadding="0" title="Table des résultat des cotes">
		
				 <tr>
        <th> Desrciption </th> 
        <th>Date Publication</th> 
        <th>Promotion</th> 
        <th>Aprouver Par</th>
        <th>Année</th> 
          <th>Supprimer</th> 
               <th>Telecharger</th>     
       </tr>
         
       <c:forEach items="${listResultsRecours}" var="hrEx">
					<tr>
						<td>${hrEx.description }</td>
						<td>${hrEx.datePublication }</td>
						<td>${hrEx.promotion }</td>
						<td>${hrEx.appouvedBy }</td>
						<td>${hrEx.year }</td>				
						<td> <a href="suppResultCotes?idResultatCotes=${hrEx.idResultatCotes }">Supp</a></td>	
							
						<td> <c:if test="${ hrEx.fileUpload !=null }"><a href="../downloadRsRecours?idResultatCotes=${hrEx.idResultatCotes}">Ouvrir</a></c:if></td>
											
											</tr>
					
									</c:forEach>
									</table>
                               
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