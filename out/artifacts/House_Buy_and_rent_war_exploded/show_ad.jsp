<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 06/12/17
  Time: 11:32 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript" src="js/view_ad.js"></script>
        <script type="text/javascript" src="js/load_ad.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDFJMPEqo48wJhXN5uBq3qBJGNYAawDU3k"></script>
    </head>

    <%
        if(session.getAttribute("name")==null){
            response.sendRedirect("login.jsp");
        }
        int ad_id = Integer.parseInt(request.getParameter("id"));
        int user_id = (int) session.getAttribute("id");
        String user_name = session.getAttribute("name").toString();
    %>
    <body onload="get_ad(<%=ad_id%>,<%=user_id%>,'<%=user_name%>')">
        <div id="ad">
            <h2 id="house_type"></h2>
            <p id="description"></p>
            <h4 id="area"></h4>
            <div id="photo_slideshow"></div>
            <h3>Location on map</h3>
            <div id="map" style="width:400px;height:400px"></div>
            <div class="comments">
                <input id="comment" type="text" placeholder="Write a comment" onkeypress="comment(event)"/>
            </div>
        </div>
    </body>
</html>
