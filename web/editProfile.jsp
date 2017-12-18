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
        <title>Title</title>
    </head>

    <body>
        <%
            if(session.getAttribute("name")==null){
                response.sendRedirect("register.jsp");
            }
        %>
        <form action='EditProfile' method='post'>
            <input name='name' placeholder='Name' required value=<%request.getSession().getAttribute("name");%>>

            <input type='tel' name='phone' placeholder='Phone' required onchange='validateNumber(this)' value=<%request.getSession().getAttribute("phone");%>>

            <input type='password' name='oldPassword' placeholder='Old Password' required>

            <input type='password' name='password'
                   id ='password' placeholder='New Password' onchange='validatePassword(this)'>

            <input name="address" placeholder="Address" value=<%request.getSession().getAttribute("address");%>>

            <button>Submit</button>
        </form>
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

