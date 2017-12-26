<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-US"><head><!-- Created by Artisteer v4.1.0.60046 -->
    <meta charset="utf-8">
    <title>New Page</title>
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

    <div class="art-shapes">
        
            </div>

<h1 class="art-headline" data-left="25.46%">
    <a href="#">vBoot</a>
</h1>
<h2 class="art-slogan" data-left="95%">Excersize To Get In Shape</h2>
<div class="art-textblock art-object1034248187" data-left="93.87%">
    <form class="art-search" name="Search" action="javascript:void(0)">
    <input type="text" value="">
            <input type="submit" value="Search" name="search" class="art-search-button">
    </form>
</div>
                
                    
</header>
<div class="art-sheet clearfix">
<nav class="art-nav">
    <div class="art-nav-inner">
    <ul class="art-hmenu"><li><a href="main.html" class="">Main</a></li><li><a href="calendar.html" class="">Calendar</a></li><li><a href="about.html" class="">About Us</a></li><li><a href="contacts.html" class="">Contacts</a></li><li><a href="horraire.html" class="active">horraire</a></li><li><a href="search.html">Search</a><ul><li><a href="search/rssearc.html">rssearc</a></li></ul></li></ul> 
        </div>
    </nav>
<div class="art-layout-wrapper">
                <div class="art-content-layout">
                    <div class="art-content-layout-row">
                        <div class="art-layout-cell art-sidebar1"><div class="art-vmenublock clearfix">
        <div class="art-vmenublockcontent">
<ul class="art-vmenu"><li><a href="home.html" class="">Announcements</a></li><li><a href="calendar.html" class="">Calendar</a></li><li><a href="statistics.html" class="">Statistics</a></li><li><a href="about.html" class="">About Us</a></li><li><a href="contacts.html" class="">Contacts</a></li><li><a href="shopping.html">Shopping</a></li><li><a href="macabee.html">Macabee</a></li><li><a href="recours.html">Recours</a></li></ul>
                
        </div>
</div></div>
                        <div class="art-layout-cell art-content"><article class="art-post art-article">
                                <h2 class="art-postheader">New Page</h2>
                            ++++++++++++++++++++++++                    
               <c:forEach items="${HorraireDetails}" var="dh">	
					
	<p> <h2>  ${dh.titreHorraire } </h2>  <h3>Du :     ${dh.datefrom }  Au: ${dh.dateTo } </h3> </p>						  
						  <p style="text-align: center;"> <h3><strong>Faculte de 
						${dh.faculty } </strong></h3> </p>
																				
									</c:forEach>  
									   
									
									
									
			<table title="Tableau d'affiche horraire Curent" class="table1">

				<tr>
					<th  >Promotion</th>
					<th>Lundi</th>
					<th>Mardi</th>
					<th >Mercredi</th>
					<th>jeudi</th> 
					<th>vendredi</th>
					<th>Samedi</th>					
				</tr>
				<c:forEach items="${HorraireListe}" var="hr">
					<tr>
						<td >${hr.promotion }</td>
						<td>${hr.lundi }</td>
						<td>${hr.mardi }</td>
						<td>${hr.mercredi }</td>
						<td>${hr.jeudi }</td>
						<td>${hr.vendredi }</td>
						<td>${hr.samedi }</td>
											</tr>
					
									</c:forEach>

			</table>
                                
                

</article></div>
                        <div class="art-layout-cell art-sidebar2"></div>
                    </div>
                </div>
            </div>
    </div>
<footer class="art-footer">
  <div class="art-footer-inner">
<div class="art-content-layout">
    <div class="art-content-layout-row">
    <div class="art-layout-cell layout-item-0" style="width: 100%">
        <p>Copyright © 2011-2012, Aerobics Gym. All Rights Reserved.</p>
    </div>
    </div>
</div>

    <p class="art-page-footer">
        <span id="art-footnote-links"><a href="http://www.artisteer.com/" target="_blank">Web Template</a> created with Artisteer.</span>
    </p>
  </div>
</footer>

</div>


</body></html>