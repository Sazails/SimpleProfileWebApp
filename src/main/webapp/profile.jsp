<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>

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

<div style="text-align: center;"><h2>Welcome Back</h2></div>

<table>
    <tr>
        <td>
            Email: <%=request.getAttribute("userEmail")%>
        </td>
    </tr>
    <tr>
        <td>
            Username: <%=request.getAttribute("userUsername")%>
        </td>
    </tr>
</table>

<form name="friendFinder" action="">
    <table align="center">
        <tr>
            <td>

            </td>
        </tr>
    </table>
</form>

</body>
</html>
