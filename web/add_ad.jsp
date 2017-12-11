<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 05/12/17
  Time: 12:24 ص
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <script type="text/javascript" src="js/show_map.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDFJMPEqo48wJhXN5uBq3qBJGNYAawDU3k&callback=loadMap"></script>

    </head>

    <body>
        <form action="AddAdServlet" method="post"  encType="multipart/form-data">
            <input name="type" type="text" placeholder="Type" required/><br>
            <input name="space" type="number" placeholder="Space" required/><br>
            <input name="description" type="text" placeholder="Description" required/><br>
            <input name="area" id="area" type="text" placeholder="Area" required/><br>
            <input name="floor" type="number" placeholder="floor" required/><br>
            <input type="file" name="file" value="select images..."/>
            <input name="lat" id="lat" type="hidden"/>
            <input name="lng" id="lng" type="hidden"/>

            <button>Add</button>
        </form>
        <button type="button" onclick="loadMap()">Get location on map</button>
        <div id="map" style="width:400px;height:400px"></div>
    </body>
</html>
