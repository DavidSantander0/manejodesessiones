<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productos.css">
    <title>Nueva Categoría</title>
</head>
<body>
<h1>Nueva Categoría</h1>
<form action="${pageContext.request.contextPath}/categoria" method="post">
    <!-- No se envía ID ya que es una nueva categoría -->
    <div>
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required>
    </div>
    <div>
        <label for="descripcion">Descripción:</label>
        <input type="text" name="descripcion" id="descripcion" required>
    </div>
    <div>
        <button type="submit">Guardar Categoría</button>
    </div>
</form>
<a href="${pageContext.request.contextPath}/categoria">
    <button>Volver al Listado</button>
</a>
</body>
</html>
