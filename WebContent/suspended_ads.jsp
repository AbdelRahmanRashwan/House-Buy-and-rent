<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 18/12/17
  Time: 03:37 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Suspended Ads</title>
        <script type="text/javascript" src="js/load_suspended_ads.js"></script>
        <script src="js/jquery.js"></script>
        <script type="text/javascript" src="js/show_notifications.js"></script>
        <script type="text/javascript" src="js/navbar.js"></script>

        <link rel="stylesheet" type="text/css" href="Style/navbar.css"/>
    </head>
    <%
        String user_type = session.getAttribute("type").toString();
        int user_id = Integer.parseInt(session.getAttribute("id").toString());
        if(!(user_type.equals("admin"))){
            response.sendRedirect("home.jsp");
        }
    %>
    <body onload="get_suspended_ads(); get_notifications(<%=user_id%>)">
        <nav>
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
        <ul id="ads_list">
            <%--get data using ajax--%>
        </ul>
    </body>
</html>
