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
        <script type="text/javascript" src="js/load_ad.js"></script>
    </head>

    <body onload="get_ad(<%=request.getParameter("id")%>)">
        <div class="ad">
            <h2 id="house_type"></h2>
            <p id="description"></p>
            <h4 id="area"></h4>
            <div id="photo_slideshow"></div>
            <h3>Location on map</h3>
            <div id="map"></div>
        </div>
    </body>
</html>
