<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 6/1/2025
  Time: 4:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    org.david.manejodesesiones.models.Productos producto = (org.david.manejodesesiones.models.Productos) request.getAttribute("producto");
    boolean isEdit = (producto != null);
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productos.css">
    <title><%= isEdit ? "Editar Producto" : "Nuevo Producto" %></title>
</head>
<body>
<h1><%= isEdit ? "Editar Producto" : "Nuevo Producto" %></h1>
<form action="${pageContext.request.contextPath}/productos" method="post">
    <input type="hidden" name="id" value="<%= isEdit ? producto.getId() : "" %>">
    <div>
        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%= isEdit ? producto.getNombre() : "" %>" required>
    </div>
    <div>
        <label>Tipo:</label>
        <input type="text" name="tipo" value="<%= isEdit ? producto.getTipo() : "" %>">
    </div>
    <div>
        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" value="<%= isEdit ? producto.getPrecio() : "" %>" required>
    </div>
    <div>
        <button type="submit" class="btn"><%= isEdit ? "Actualizar" : "Guardar" %></button>
    </div>
</form>
<a href="${pageContext.request.contextPath}/productos"><button class="btn">Volver al Listado</button></a>
</body>
</html>
