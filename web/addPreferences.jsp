<%@ page import="Entities.Preferences" %><%--
  Created by IntelliJ IDEA.
  User: Rashwan
  Date: 12/6/2017
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="AddPreferences" method="get">
        <input type="number" placeholder="Size" name="size" value=
            <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getSize());
%>>
        <input type="number" placeholder="floor" name="floor" value=
            <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getFloor());
%>>
        <input placeholder="type" name="type" value=
            <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getType());
                else out.print("");
%>>
        <input placeholder="area" name="area" value=
            <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getArea());
                else out.print("");
%>>
        <input type="submit" name="submit">
    </form>
</body>
</html>
