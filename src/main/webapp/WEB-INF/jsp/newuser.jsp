<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head></head>
<body>
<h2>What!!!?</h2>
<c:out value="Logged in as:  ${username}"/><br>
<a href="/logout">Logout</a><br>

<h2>Create New User:</h2>
<form action ="/admin/newuser" method="POST">
Username: <input type="text" name="username" required><br>
Password: <input type="text" name="password" required><br>
First Name: <input type="text" name="firstName" required><br>
Last Name: <input type="text" name="lastName" required><br><br>
<h3> Account Details: </h3>
Account Number: <input type="text" name="acct_nb" required><br>
<h3>Roles: </h3>
Role Name: <select name="role">
<option value="ADMIN_USER">Admin</option>
<option value="STANDARD_USER">Standard User</option>
</select>
     	  <input type="submit" value="Submit">

       </form>
</body>
</html>