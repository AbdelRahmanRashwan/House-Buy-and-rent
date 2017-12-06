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
        <link rel="stylesheet" type="text/css" href="Style/home.css"/>
    </head>

    <body onload="get_all_ads()">
        <nav>
            <a href=""  id="logout" class="btn">Logout</a>
            <a href="profile.jsp" id="profile" class="btn">Profile</a>
            <a href="add_ad.jsp" id="new_ad" class="btn">Add new ad</a>
        </nav>
        <div class="container">
            <div class="ads left">
                <ul id="ads_list">
                    <%--get data using ajax--%>
                </ul>
            </div>
            <div class="right">
                <label for="location">Location: </label>
                <input id="location" type="text"/><br>
                <label for="type">House type: </label>
                <input id="type" type="text"/><br>
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
        </div>
    </body>
</html>
