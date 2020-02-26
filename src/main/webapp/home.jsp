<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript">
        function openPage(pageName){
            window.location.href = pageName;
        }
    </script>

</head>
<body>
<div style="text-align: center;"><h2>Homepage</h2></div>
<form name="mainButtonForm">
    <table align="center">
        <tr>
            <td>
                <input type="button" value="Sign Up" name="signUpButton"
                       onclick="openPage('register.jsp')"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="button" value="Log In" name="logInButton"
                       onclick="openPage('login.jsp')"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
