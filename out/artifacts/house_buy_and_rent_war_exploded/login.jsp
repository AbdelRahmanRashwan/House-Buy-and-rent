<%@ page import="Entities.User" %><%--
  Created by IntelliJ IDEA.
  User: Rashwan
  Date: 12/5/2017
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel='stylesheet' type='text/css' href='Style/forms.css'>
</head>
<body>
    <div id="MainContainer">
        <a href='home.php' ><img src='resources/logo.png' id='logo'></a>
        <form action="Login" method="post">

            <input type="email" name="email" placeholder="Email" required>

            <input type="password" name="password" placeholder="Password" required>
            <%
                if (request.getAttribute("error")!= null){
                    %><label style='font-size: 10px;color:red;'>*wrong email or password</label>
            <%
                }
            %>
            <button>Login</button>

        </form>
    </div>
</body>
</html>
