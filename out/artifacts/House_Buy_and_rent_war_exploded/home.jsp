<%--
  Created by IntelliJ IDEA.
  User: ahmed
  Date: 03/12/17
  Time: 10:10 م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home</title>
        <script type="text/javascript" src="js/ajax_load_ads.js"></script>
        <script src="js/jquery.js"></script>
        <script src="js/show_notifications.js"></script>
        <script src="js/navbar.js"></script>

        <link rel="stylesheet" type="text/css" href="Style/home.css"/>
        <link rel="stylesheet" type="text/css" href="Style/navbar.css"/>
        <link rel="stylesheet" type="text/css" href="Style/forms.css"/>
    </head>

    <%
        int user_id =0;
        String user_type ="";
        if(session.getAttribute("name")==null){
            response.sendRedirect("register.jsp");
        }else {
            user_type = session.getAttribute("type").toString();
            user_id = Integer.parseInt(session.getAttribute("id").toString());
        }

    %>
    <body onload="get_all_ads(user_type); get_notifications(<%=user_id%>)">
        <nav id="nav-background">
            <a href="home.jsp"><img src="resources/logo.png" id="logo-nav"></a>

            <ul class="nav">
                <li class="nav_item"><a href="Logout" id="logout" class="nav_btn">Logout</a></li>
                <li class="nav_item"><a href="Profile" id="profile" class="nav_btn">Profile</a></li>
                <li class="nav_item" id="noti_Container">
                    <div id="noti_Counter"></div>   <!--SHOW NOTIFICATIONS COUNT.-->

                    <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                    <div id="noti_Button"><img src="resources/notification.png"></div>

                    <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                    <div id="notifications">
                        <h3>Notifications</h3>

                        <div id="notifications_div">
                            <ul id="notifications_container" style="height:300px;"></ul>
                        </div>
                        <div class="seeAll"><a href="#">See All</a></div>
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
                <li class="nav_item"><a href="home.jsp" class="nav_btn">Home</a></li>
                <%}%>
            </ul>
        </nav>

        <div class="container">
            <div class="ads left">
                <ul id="ads_list">
                    <%--get data using ajax--%>
                </ul>
            </div>
            <div class="right">
                <div id="MainContainer">
                    <input id="area" type="text" placeholder="Area"/><br>
                    <input id="house_type" type="text" placeholder="House Type"/><br>
                    <input type="number" id="lower_bound" placeholder="From">
                    <input type="number" id="upper_bound" placeholder="To">
                    <button class="btn" onclick="get_filtered_ads()">Apply filter</button>
                </div>
            </div>

            <%--<form id="hidden_form" method="post" hidden>--%>
                <%--<input id="houseType" name="toto"/>--%>
                <%--<input id="space" type="number"/>--%>
                <%--<input id="description" type="text"/>--%>
                <%--<input id="location" type="text"/>--%>
                <%--<input id="floor" type="number"/>--%>
                <%--<input id="lng" name="lng"/>--%>
                <%--<input id="lat" name="lat"/>--%>
            <%--</form>--%>
        </div>
    </body>
</html>
