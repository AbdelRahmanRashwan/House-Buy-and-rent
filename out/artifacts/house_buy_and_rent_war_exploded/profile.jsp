<%@ page import="Entities.Advertisement" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Rashwan
  Date: 12/6/2017
  Time: 1:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1><% out.print(request.getSession().getAttribute("name")); %></h1>
    <h1><% out.print(request.getSession().getAttribute("email")); %></h1>
    <h1><% out.print(request.getSession().getAttribute("phone")); %></h1>
    <h1><% out.print(request.getSession().getAttribute("address")); %></h1>
    <a href="editProfile.jsp"><button >Edit Profile</button></a>
    <a href="addPreferences.jsp"><button onclick="addPreferences.jsp">edit preference</button></a>

    <%
        List<Advertisement> advertisements = (List<Advertisement>) request.getAttribute("advertisements");
        for (int i = 0; i < advertisements.size(); i++) {
            out.print(advertisements.get(i).getType()+"<br>");
            out.print(advertisements.get(i).getHouse().getArea()+"<br>");
            out.print(advertisements.get(i).getHouse().getDescription()+"<br>");
            out.print(advertisements.get(i).getHouse().getFloor()+"<br>");
            out.print(advertisements.get(i).getHouse().getSize()+"<br>");
        }

    %>
</body>
</html>
