<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 6/1/2025
  Time: 5:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nuevo Producto</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productos.css">
</head>
<body>
<h1>Nuevo Producto</h1>
<form action="${pageContext.request.contextPath}/productos" method="post">
  <!-- Campo oculto para ID. Para un producto nuevo se envía vacío -->
  <input type="hidden" name="id" value="">

  <div>
    <label for="nombre">Nombre:</label>
    <input type="text" id="nombre" name="nombre" required>
  </div>

  <div>
    <label for="tipo">Tipo:</label>
    <input type="text" id="tipo" name="tipo" required>
  </div>

  <div>
    <label for="precio">Precio:</label>
    <input type="number" id="precio" name="precio" step="0.01" required>
  </div>

  <div>
    <button type="submit">Guardar Producto</button>
  </div>
</form>

<a href="${pageContext.request.contextPath}/productos">
  <button>Volver a Listar Productos</button>
</a>
</body>
</html>
