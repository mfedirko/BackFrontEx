<!DOCTYPE HTML>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Users</title>
<link rel="stylesheet" href="/styles.css" />
</head>

<body>


<div class="header">
	<select id="criteria">
		<option value="username">Username</option>
		<option value="firstName">First Name</option>
		<option value="lastName">Last Name</option>
		<option value="id">ID</option>
	</select>
	<input type="text" id="searchKey"/>
	<button id="btnSearch">Search</button>
	<button id="btnAdd">New User</button>
	<button id="btnRole">Delete Role</button>
</div>


<div class="leftArea">
<ul id="wineList"></ul>
</div>



<form id="wineForm">
	
<div class="mainArea">


<h3> User Details: </h3>
<label>Username</label>
<input type="text" id="username" name="username" required><br>

<label>Password:</label>
<input type="password" id="password" name="password"/><br>

<label>First Name:</label>
<input type="text" id="firstName" name="firstName"/><br>

<label>Last Name:</label>
<input type="text" id="lastName" name="lastName"/><br>

 <h3>Account Numbers: </h3>
<div id="linkdiv" class="linkdiv" style="cursor: pointer">
 <label>Default Account Number: </label>
	<input type="text" id="acct_nb" name="acct_nb"/><br>
</div>

<button id="btnSave">Save</button>
<button id="btnDelete">Delete</button>



</div>




</form>
<span class="alignRight"> <c:out value="Logged in as:  ${pageContext.request.remoteUser}"/><br>
<a href="/logout">Logout</a><br></span>
<div class="rightArea">
<h3>User Roles:</h3>



<ul id="roleList"></ul>


</div>
<script src="/jquery-1.7.1.min.js"></script>
<script src="/admin.js"></script>


</body>
</html>