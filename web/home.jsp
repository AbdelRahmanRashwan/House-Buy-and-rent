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
    </head>

    <%
        if(session.getAttribute("name")==null){
            response.sendRedirect("register.jsp");
        }
    %>
    <body onload="get_all_ads(); get_notifications(<%=session.getAttribute("id")%>)">
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
                <li class="nav_item"><a href="add_ad.jsp" id="new_ad" class="nav_btn">Add new ad</a></li>
            </ul>
        </nav>

        <div class="container">
            <div class="ads left">
                <ul id="ads_list">
                    <%--get data using ajax--%>
                </ul>
            </div>
            <div class="right">
                <label for="area">Location: </label>
                <input id="area" type="text"/><br>
                <label for="house_type">House type: </label>
                <input id="house_type" type="text"/><br>
                <label>
                    Space:
                    <input type="number" id="lower_bound">
                    <span>to</span>
                    <input type="number" id="upper_bound">
                    <span> m</span>
                </label>
                <br>
                <button class="btn" onclick="get_filtered_ads()">Apply filter</button>
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
