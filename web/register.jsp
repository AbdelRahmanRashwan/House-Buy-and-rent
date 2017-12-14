<%--
  Created by IntelliJ IDEA.
  User: Rashwan
  Date: 12/5/2017
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="Style/forms.css" type="text/css" rel="stylesheet">
    <title>Register</title>
</head>
<body>

<div id='MainContainer'>
    <a href='home.jsp'><img src='resources/logo.png' id='logo'></a>
    <form action='Register' method='post'>
        <input name='name' placeholder='Name' required>
        <input type='email' name='email' placeholder='Email' required>
        <%
            if (request.getAttribute("error") != null){
                %>
                <label style='font-size: 10px;color:red;'>*Email already exists</label>
        <%
            }
        %>
        <input type='tel' name='phone' placeholder='Phone' required onchange='validateNumber(this)'>

        <input type='password' name='password'
               id ='password' placeholder='Password' onchange='validatePassword(this)' required>

        <input type='password' placeholder='Confirm Password' onchange='confirmPassword(this)' required>

        <input name="address" placeholder="Address">

        <button>Submit</button>
        <a href='login.jsp' id='signIn'>or sign in</a>
    </form>
</div>

</body>
<script>
    function confirmPassword(password) {
//        if (password.value != document.getElementById('password').value) {
//            password.setCustomValidity('password does not match');
//        } else {
//            password.setCustomValidity('');
//        }
    }

    function validatePassword(input) {

//        var password = input.value;
//        if (password.length < 8) {
//            input.setCustomValidity('Password must be greater than 8 characters');
//        } else {
//            input.setCustomValidity('');
//        }

    }

    function validateNumber(input) {
//        var number = input.value;
//        for (i = 0; i < number.length; i++) {
//            if (number[i] > '9' || number[i] < '0') {
//                input.setCustomValidity('Numbers only');
//                break;
//            } else {
//                input.setCustomValidity('');
//            }
//        }
    }
</script>

</html>
