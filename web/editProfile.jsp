<%--
  Created by IntelliJ IDEA.
  User: Rashwan
  Date: 12/7/2017
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit Profile</title>
        <link rel="stylesheet" type="text/css" href="Style/forms.css"/>
        <link rel="stylesheet" type="text/css" href="Style/navbar.css"/>
    </head>

    <body>
        <%
            if(session.getAttribute("name")==null){
                response.sendRedirect("login.jsp");
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
                        <div class="seeAll"><a href="#" >See All</a></div>
                    </div>
                </li>
                <li class="nav_item"><a href="add_ad.jsp" id="new_ad" class="nav_btn">Add new ad</a></li>
                <li class="nav_item"><a href="home.jsp" class="nav_btn">Home</a></li>
            </ul>
        </nav>
        <div id="MainContainer">
            <h2 id="edit-preference">Edit Profile:</h2>
            <form action='EditProfile' method='post'>
                <input name='name' placeholder='Name' required value=<%request.getSession().getAttribute("name");%>>

                <input type='tel' name='phone' placeholder='Phone' required onchange='validateNumber(this)' value=<%request.getSession().getAttribute("phone");%>>

                <input type='password' name='oldPassword' placeholder='Old Password' required>

                <input type='password' name='password'
                       id ='password' placeholder='New Password' onchange='validatePassword(this)'>

                <input name="address" placeholder="Address" value=<%request.getSession().getAttribute("address");%>>

                <button>Submit</button>
            </form>
        </div>
    </body>
    <script>

        function validatePassword(input) {
            var password = input.value;
            if (password.length < 8) {
                input.setCustomValidity('Password must be greater than 8 characters');
            } else {
                input.setCustomValidity('');
            }
        }

        function validateNumber(input) {
            var number = input.value;
            for (i = 0; i < number.length; i++) {
                if (number[i] > '9' || number[i] < '0') {
                    input.setCustomValidity('Numbers only');
                    break;
                } else {
                    input.setCustomValidity('');
                }
            }
        }
    </script>

</html>

