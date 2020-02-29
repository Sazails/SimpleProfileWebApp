<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>

    <script type="text/javascript">
        function openPage(pageName){
            window.location.href = pageName;
        }
    </script>

<%--    <script>--%>
<%--        function validate(){--%>
<%--            var email = document.registerForm.email.value;--%>
<%--            var username = document.registerForm.username.value;--%>
<%--            var password = document.registerForm.password.value;--%>
<%--            var passwordConfirm = document.registerForm.passwordConfirm.value;--%>

<%--            if(email==null || email==""){--%>
<%--                alert("Email can't be blank.");--%>
<%--                return false;--%>
<%--            } else if(username==null || username==""){--%>
<%--                alert("Username can't be blank.");--%>
<%--                return false;--%>
<%--            } else if(password.length < 8){--%>
<%--                alert("Password must be at least 8 characters long.");--%>
<%--                return false;--%>
<%--            } else if(password != passwordConfirm){--%>
<%--                alert("Confirm password does not match password.");--%>
<%--                return false;--%>
<%--            }--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
<tr>
    <td>
        <input type="button" value="Home" name="logInButton"
               onclick="openPage('home.jsp')"/>
    </td>
</tr>

<div style="text-align: center;"><h2>Sign Up</h2></div>

<%--<form name="registerForm" action="<%= request.getContextPath() %>/registerServlet" method="post">--%>
<form name="registerForm" action="<%= request.getContextPath() %>/registerServlet" method="post">
    <table align="center">
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Confirm Password:</td>
            <td>
                <input type="password" name="passwordConfirm"/>
            </td>
        </tr>

        <tr>
            <td>
                <%=(request.getAttribute("errorMessage") == null) ? "": request.getAttribute("errorMessage")%>
            </td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Register"/>
                <input type="reset" value="Clear"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
