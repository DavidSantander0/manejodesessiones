<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 5/19/2025
  Time: 9:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/login.css">
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h1>Login de usuario</h1>
<div>
    <form action="/manejodesesiones/login" method="post">
        <div>
            <label for="username">Nombre de usuario:</label>
            <div>
                <input type="text" name="username" id="username">
            </div>
        </div>

        <div>
            <label for="pass">Password:</label>
            <div>
                <input type="password" name="password" id="pass">
            </div>
        </div>
        <div>
            <input type="submit" value="Enviar">
        </div>
    </form>
</div>

</body>
</html>
