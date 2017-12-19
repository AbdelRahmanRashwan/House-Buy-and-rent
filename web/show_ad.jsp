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
        <script src="js/jquery.js"></script>
        <script src="js/show_notifications.js"></script>
        <script src="js/navbar.js"></script>

        <link rel="stylesheet" type="text/css" href="Style/navbar.css"/>
    </head>

    <%
        int ad_id = 0;
        int user_id = 0;
        String user_name ="";
        String user_type ="";
        if(session.getAttribute("name")==null){
            response.sendRedirect("register.jsp");
        }else {
            ad_id = Integer.parseInt(request.getParameter("id"));
            user_id = Integer.parseInt(session.getAttribute("id").toString());
            user_name = session.getAttribute("name").toString();
            user_type = session.getAttribute("type").toString();
        }
    %>
    <body onload="get_ad(<%=ad_id%>,<%=user_id%>,'<%=user_name%>'); get_notifications(<%=user_id%>)">
        <nav id="nav-background">
            <ul class="nav">
                <li class="nav_item"><a href="Logout" id="logout" class="nav_btn">Logout</a></li>
                <li class="nav_item"><a href="profile.jsp" id="profile" class="nav_btn">Profile</a></li>
                <li class="nav_item" id="noti_Container">
                    <div id="noti_Counter"></div>   <!--SHOW NOTIFICATIONS COUNT.-->

                    <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                    <div id="noti_Button"></div>

                    <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                    <div id="notifications">
                        <h3>Notifications</h3>
                        <div id="notifications_div">
                            <ul id="notifications_container" style="height:300px;"></ul>
                        </div>
                        <div class="seeAll"><a href="#" class="nav_btn">See All</a></div>
                    </div>
                </li>
                <li class="nav_item">
                    <a href="home.jsp" id="home" class="nav_btn">Home</a>
                </li>
                <%
                    if(user_type.equals("admin")){
                %>
                <li class="nav_item"><a href="suspended_ads.jsp" id="suspended_ads" class="nav_btn">Suspended ads</a></li>
                <%
                } else{
                %>
                <li class="nav_item"><a href="add_ad.jsp" id="new_ad" class="nav_btn">Add new ad</a></li>
                <%}%>
            </ul>
        </nav>
        <div id="ad">
            <h2 id="house_type"></h2>
            <p id="description"></p>
            <p id="size"></p>
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
