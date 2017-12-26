<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<a href="<c:url value="/j_spring_security_logout"/>">Logout</a> <!-- POUR FAIR LE LOG OUT -->
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3> <strong>Autor : ${authorName}</strong></h3>
</body>
</html>
