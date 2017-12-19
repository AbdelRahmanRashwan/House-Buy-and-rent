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
        <title>Profile</title>
        <link rel='stylesheet' type='text/css' href='Style/profile.css'>
        <link rel="stylesheet" type="text/css" href="Style/navbar.css"/>
    </head>

    <body>
        <%
            if(session.getAttribute("name")==null){
                response.sendRedirect("register.jsp");
            }
        %>
        <nav id="nav-background">
            <a href="home.jsp"><img src="resources/logo.png" id="logo-nav"></a>
            <ul class="nav">
                <li class="nav_item"><a href="Logout" id="logout" class="nav_btn">Logout</a></li>
                <li class="nav_item"><a href="profile.jsp" id="profile" class="nav_btn">Profile</a></li>
                <li class="nav_item" id="noti_Container">
                    <div id="noti_Counter"></div>   <!--SHOW NOTIFICATIONS COUNT.-->

                    <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                    <div id="noti_Button"><img src="resources/notification.png"></div>

                    <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                    <div id="notifications">
                        <h3>Notifications</h3>
                        <ul id="notifications_container" style="height:300px;"></ul>
                        <div class="seeAll"><a href="#">See All</a></div>
                    </div>
                </li>
                <li class="nav_item"><a href="add_ad.jsp" id="new_ad" class="nav_btn">Add new ad</a></li>
                <li class="nav_item"><a href="home.jsp" class="nav_btn">Home</a></li>
            </ul>
        </nav>


        <div id="container">
            <div class="data">Name: <% out.print(session.getAttribute("name")); %></div>
            <div class="data">Email: <% out.print(session.getAttribute("email")); %></div>
            <div class="data">Phone: <% out.print(session.getAttribute("phone")); %></div>
            <div class="data">Address: <% out.print(session.getAttribute("address")); %></div>
            <a href="editProfile.jsp"><button class="submit">Edit Profile</button></a>
            <a href="addPreferences.jsp"><button class="submit" onclick="addPreferences.jsp">edit preference</button></a>

            <%
                List<Advertisement> advertisements = (List<Advertisement>) request.getAttribute("advertisements");
                if (advertisements != null){
                    for (int i = 0; i < advertisements.size(); i++) {
            %>
                <div class="advertisement">
            <%
                        out.print(advertisements.get(i).getType()+"<br>");
                        out.print(advertisements.get(i).getArea()+"<br>");
                        out.print(advertisements.get(i).getDescription()+"<br>");
                        out.print(advertisements.get(i).getFloor()+"<br>");
                        out.print(advertisements.get(i).getSize()+"<br>");
            %>
                </div>
            <%
                    }
                }

                else{
            %>
                <div id="no-ads">
                    <h2>No Ads to show</h2>
                </div>
            <%
                }
            %>
        </div>

        <script src="js/jquery.js"></script>
        <script src="js/show_notifications.js"></script>
        <script src="js/navbar.js"></script>
    </body>
</html>
