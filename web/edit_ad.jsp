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
        <script src="js/jquery.js"></script>
        <script type="text/javascript" src="js/show_notifications.js"></script>
        <script type="text/javascript" src="js/navbar.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDFJMPEqo48wJhXN5uBq3qBJGNYAawDU3k"></script>

        <link rel="stylesheet" type="text/css" href="Style/navbar.css"/>
    </head>

    <%
        int ad_id = 0;
        int user_id = 0;
        String user_type ="";
        if(session.getAttribute("name")==null){
            response.sendRedirect("register.jsp");
        }else {
            ad_id = Integer.parseInt(request.getParameter("id"));
            user_id = Integer.parseInt(session.getAttribute("id").toString());
            user_type = session.getAttribute("type").toString();
        }
    %>

    <body onload="get_ad(<%=ad_id%>,<%=user_id%>); get_notifications(<%=user_id%>)">
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
        <form action="EditAdServlet" method="post"  encType="multipart/form-data">
            <input name="ad_id" id="ad_id" type="hidden">
            <input name="type" id="house_type" type="text" placeholder="Type" required/><br>
            <input name="space" id="space" type="number" placeholder="Space" required/><br>
            <input name="description" id="description" type="text" placeholder="Description" required/><br>
            <input name="area" id="area" type="text" placeholder="Area" required/><br>
            <input name="floor" id="floor" type="number" placeholder="floor" required/><br>
            <div id="photo_slideshow"></div>
            <input type="file" name="file" value="select images..." onchange="loadPicture(this)"/>
            <input name="lat" id="lat" type="hidden"/>
            <input name="lng" id="lng" type="hidden"/>

            <button>Edit</button>
        </form>
        <div id="map" style="width:400px;height:400px"></div>
    </body>

</html>
