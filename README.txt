This project was made for purpose of learning Spring Security, JAX-RS and Hibernate. 

It contains an admin-only page for managing users and a page for standard users to make transactions from current user's account
to other accounts. Default Spring Security login/logout page and custom Web Security config with method-level authorization. 
All user, account and transaction data is stored in a MySQL database and retrieved using Hibernate or native queries. 

The front-end contains JSP pages and uses AJAX to call the backend ReST service.  
The front end is heavily based on the wine cellar project at https://github.com/ccoenraets/wine-cellar-java/blob/master/WebContent/js/jquery-1.7.1.min.js

Credit to other Projects:
Some parts of the backend of this project (Spring Security config, method-level authorization) were adapted from https://medium.com/@nydiarra/secure-a-spring-boot-rest-api-with-json-web-token-reference-to-angular-integration-e57a25806c50.

Front end is heavily based on wine cellar project at https://github.com/ccoenraets/wine-cellar-java/blob/master/WebContent/js/jquery-1.7.1.min.js.
