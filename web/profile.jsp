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
        <%
            if(session.getAttribute("name")==null){
                response.sendRedirect("register.jsp");
            }
        %>
        <h1><% out.print(session.getAttribute("name")); %></h1>
        <h1><% out.print(session.getAttribute("email")); %></h1>
        <h1><% out.print(session.getAttribute("phone")); %></h1>
        <h1><% out.print(session.getAttribute("address")); %></h1>
        <a href="editProfile.jsp"><button >Edit Profile</button></a>
        <a href="addPreferences.jsp"><button onclick="addPreferences.jsp">edit preference</button></a>

        <%--<%--%>
            <%--List<Advertisement> advertisements = (List<Advertisement>) request.getAttribute("advertisements");--%>
            <%--for (int i = 0; i < advertisements.size(); i++) {--%>
                <%--out.print(advertisements.get(i).getType()+"<br>");--%>
                <%--out.print(advertisements.get(i).getArea()+"<br>");--%>
                <%--out.print(advertisements.get(i).getDescription()+"<br>");--%>
                <%--out.print(advertisements.get(i).getFloor()+"<br>");--%>
                <%--out.print(advertisements.get(i).getSize()+"<br>");--%>
            <%--}--%>

        <%--%>--%>
    </body>
</html>
