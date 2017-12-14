<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 10/12/17
  Time: 12:39 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit</title>
        <script type="text/javascript" src="js/view_edit_ad.js"></script>
        <script type="text/javascript" src="js/load_ad.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDFJMPEqo48wJhXN5uBq3qBJGNYAawDU3k"></script>
    </head>

    <%
        if(session.getAttribute("name")==null){
            response.sendRedirect("login.jsp");
        }
        int ad_id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(session.getAttribute("id").toString());
    %>

    <body onload="get_ad(<%=ad_id%>,<%=user_id%>)">
        <form action="EditAdServlet" method="post"  encType="multipart/form-data">
            <input name="ad_id" id="ad_id" type="hidden">
            <input name="type" id="house_type" type="text" placeholder="Type" required/><br>
            <input name="space" id="space" type="number" placeholder="Space" required/><br>
            <input name="description" id="description" type="text" placeholder="Description" required/><br>
            <input name="area" id="area" type="text" placeholder="Area" required/><br>
            <input name="floor" id="floor" type="number" placeholder="floor" required/><br>
            <div id="photo_slideshow"></div>
            <input type="file" name="file" value="select images..."/>
            <input name="lat" id="lat" type="hidden"/>
            <input name="lng" id="lng" type="hidden"/>

            <button>Edit</button>
        </form>
        <div id="map" style="width:400px;height:400px"></div>
    </body>

</html>
