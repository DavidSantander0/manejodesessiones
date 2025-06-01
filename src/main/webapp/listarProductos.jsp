<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 6/1/2025
  Time: 4:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.david.manejodesesiones.models.Productos" %>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productos.css">
  <title>Listado de Productos</title>
</head>
<body>
<h1>Listado de Productos</h1>
<table>
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Tipo</th>
    <th>Precio</th>
    <th>Acciones</th>
  </tr>
  <%
    List<org.david.manejodesesiones.models.Productos> productos = (List<org.david.manejodesesiones.models.Productos>) request.getAttribute("productos");
    for(org.david.manejodesesiones.models.Productos p : productos){
  %>
  <tr>
    <td><%= p.getId() %></td>
    <td><%= p.getNombre() %></td>
    <td><%= p.getTipo() %></td>
    <td><%= p.getPrecio() %></td>
    <td>
      <a href="${pageContext.request.contextPath}/productos?action=editar&id=<%= p.getId() %>">Editar</a>
      <a href="${pageContext.request.contextPath}/productos?action=eliminar&id=<%= p.getId() %>" onclick="return confirm('¿Confirmar eliminación?');">Eliminar</a>
    </td>
  </tr>
  <%
    }
  %>
</table>
<a href="${pageContext.request.contextPath}/index.html">
  <button class="btn">Inicio</button>
</a>
<a href="${pageContext.request.contextPath}/nuevoProducto.jsp"><button class="btn">Agregar Nuevo Producto</button></a>
</body>
</html>
