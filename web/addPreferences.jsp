<%@ page import="Entities.Preferences" %><%--
  Created by IntelliJ IDEA.
  User: Rashwan
  Date: 12/6/2017
  Time: 10:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Preferences</title>
        <link rel="stylesheet" type="text/css" href="Style/forms.css"/>
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
                    <div id="noti_Counter">5</div>   <!--SHOW NOTIFICATIONS COUNT.-->

                    <!--A CIRCLE LIKE BUTTON TO DISPLAY NOTIFICATION DROPDOWN.-->
                    <div id="noti_Button"><img src="resources/notification.png"></div>

                    <!--THE NOTIFICAIONS DROPDOWN BOX.-->
                    <div id="notifications">
                        <h3>Notifications</h3>
                        <ul id="notifications_container" style="height:300px;"></ul>
                        <div class="seeAll"><a href="#" class="nav_btn">See All</a></div>
                    </div>
                </li>
                <li class="nav_item"><a href="add_ad.jsp" id="new_ad" class="nav_btn">Add new ad</a></li>
                <li class="nav_item"><a href="home.jsp" >Home</a></li>
            </ul>
        </nav>
        <div id="MainContainer">
            <h2 id="edit-preference">Preferences:</h2>
            <form action="AddPreferences" method="get">
                <input type="number" placeholder="Size" name="size" value=
                    <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getSize());
        %>>
                <input type="number" placeholder="floor" name="floor" value=
                    <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getFloor());
        %>>
                <input placeholder="type" name="type" value=
                    <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getType());
                        else out.print("");
        %>>
                <input placeholder="area" name="area" value=
                    <%if (request.getSession().getAttribute("preferences") != null) out.print(((Preferences)request.getSession().getAttribute("preferences")).getArea());
                        else out.print("");
        %>>
                <button>submit</button>
            </form>
        </div>
    </body>
</html>
