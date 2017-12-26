<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.3.0.60745 -->
    <meta charset="utf-8">
    <title>Search Here!</title>
    <meta name="viewport" content="initial-scale = 1.0, maximum-scale = 1.0, user-scalable = no, width = device-width">

   
    <!--[if lt IE 9]><script src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.css" media="screen">
    <!--[if lte IE 7]><link rel="stylesheet" href="style.ie7.css" media="screen" /><![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/CSS/TestArt/style.responsive.css" media="all">


    <script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/script.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/script.responsive.js"></script>






</head>
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
    <ul class="art-hmenu"><li><a href="index" class="">Horraire</a></li><li><a href="UserHExam" class="active">Horraire Examen</a></li><li><a href="userInformations">Informations</a></li><li><a href="fullUserSearch">Recherche</a></li><li><a href="UserProfileDetails">Profile</a></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="fullUserRsCotes">Résultat Cotes</a></li><li><a href="fullUserRsRecours">Resultat Recours</a></li><li><a href="job">Job</a></li></ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">Search Here!</h2>
                                                
                <div class="art-postcontent art-postcontent-0 clearfix"><p><br></p>
                
                 <table title="Tables Horraore Examen" class="art-article" border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <th> Title </th> 
        <th>Date Publication</th> 
        <th>Description</th> 
        <th>Aprouver Par</th> 
         <th>Telecharger</th>     
       </tr>
       <c:forEach items="${horraireListeExamens}" var="hrEx">
					<tr>
						<td>${hrEx.titreHorraire }</td>
						<td>${hrEx.datePublication }</td>
						<td >${hrEx.description } </td>
						<td>${hrEx.approuvedBuy }</td>
										
												<td><c:if test="${ hrEx.fileupload !=null }">
						<a href="downloadHorraireExamen?idHorraire=${hrEx.idHorraire}">Ouvrir</a>
						</c:if> </td>
											</tr>
														</c:forEach>     
       </table>
                  <p> <a href="userHExamEdite"> <h3>Modifier Ici</h3></a>
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