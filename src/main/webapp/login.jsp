<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <script type="text/javascript">
        function openPage(pageName){
            window.location.href = pageName;
        }
    </script>
</head>
<body>
<tr>
    <td>
        <input type="button" value="Home" name="logInButton"
               onclick="openPage('home.jsp')"/>
    </td>
</tr>

<div style="text-align: center;"><h2>Login</h2></div>

<form name="loginForm" action="<%= request.getContextPath() %>/loginServlet" method="post">
    <table align="center">
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>
                <%=(request.getAttribute("errorMessage") == null) ? "": request.getAttribute("errorMessage")%>
            </td>
        </tr>

        <tr>
            <td>
                <input type="submit" value="Login"/>
                <input type="reset" value="Clear"/>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
