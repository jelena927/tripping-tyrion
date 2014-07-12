<%-- 
    Document   : index
    Created on : Apr 26, 2014, 9:56:34 PM
    Author     : Jelena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
          <h1>This is secured!</h1>
          <p>
            Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
          </p>
        </div>
    </body> 
</html>
