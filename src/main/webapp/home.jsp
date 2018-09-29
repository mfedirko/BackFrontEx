<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" href="/styles.css" />
</head>
<body>

<div class="rightArea"> <c:out value="Logged in as:  ${pageContext.request.remoteUser}"/><br>
<a href="/logout">Logout</a><br></div>
<h1 class="header">Index</h1>

<div class="mainArea">
<a href="/admin/">Admin User</a>
<a href="/transaction/">Regular User</a>
</div>
</body>
</html>