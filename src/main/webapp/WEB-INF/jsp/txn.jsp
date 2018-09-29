<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

    <head>
    <link rel="stylesheet" href="/styles.css" />
    </head>
    <body>
    
    
    <div class="leftArea">
<table id="txnList">
<tr>
<td>Amount</td>
<td>Date</td>
<td>From Account ID</td>
<td>To Account ID</td>
</tr>
</table>
</div>
    
        <h3>Enter Transaction:</h3>
      <form id="txnForm">
       
        <div class="mainArea">
         Amount: <input type="text" name="amount" id="amount" required><br>
         Post Date: <input type="date" name="date" id="date"><br>
         Pay from Account ID: <input type="text" name="fromAccountId" id="fromAccountId"><br>
         Pay to Account ID: <input type="text" name="toAccountId" id="toAccountId" required/>
       	 <button id="btnSubmit">Submit</button>
		</div>
		
       </form>
       <div class="rightArea">

<span class="alignRight"> <c:out value="Logged in as: "/> <span id="user">${username}</span><br>

<a href="/logout">Logout</a><br></span>
</div>
    <script src="/jquery-1.7.1.min.js"></script>
<script src="/transact.js"></script>
  
    </body>
</html>