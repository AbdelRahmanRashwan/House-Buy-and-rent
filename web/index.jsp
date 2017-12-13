<%--
  Created by IntelliJ IDEA.
  User: Rashwan
  Date: 11/26/2017
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <header></header>
  <img id="logo" src="../resources/logo.png">
    <%
      out.print(request.getSession().getAttribute("name"));
      out.print(request.getSession().getAttribute("email"));
      out.print(request.getSession().getAttribute("phone"));
      out.print(request.getSession().getAttribute("address"));
    %>

  </body>
</html>
