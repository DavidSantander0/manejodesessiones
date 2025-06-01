<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.*, org.david.manejodesesiones.models.*" %>
<%
    // Recuperamos la lista de categorías asignada en el servlet con el atributo "categorias"
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    if (categorias == null) {
        categorias = new ArrayList<>();
    }
    // Recuperamos el username asignado en el servlet
    String username = (String) request.getAttribute("username");
%>
<html>
<head>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/productos.css">
    <title>Listado Categoria</title>
</head>
<body>
<%
    if (username != null && !username.isEmpty()) {
%>
<div style="color:blue;">Hola <%= username %>, bienvenido a la aplicación</div>
<div>
    <p><a href="<%= request.getContextPath() %>/nuevoCategoria.jsp">Ingrese el producto</a></p>
</div>
<%
} else {
%>
<div style="color:red;">Usuario No autenticado</div>
<%
    }
%>
<h1>Listado Categoria</h1>
    <thead>
    <tr>
        <th>Id Categoria</th>
        <th>Nombre</th>
        <th>Descripción</th>
        <th>Condición</th>
        <th>Acciones</th>
    </tr>
    </thead>
<table>
    <tbody>
    <% for (Categoria cat : categorias) { %>
    <tr>
        <td><%= cat.getIdCategoria() %></td>
        <td><%= cat.getNombre() %></td>
        <td><%= cat.getDescripcion() %></td>
        <td><%= cat.getCondicion() %></td>
        <td>
            <a href="<%= request.getContextPath() %>/categoria?accion=editar&id=<%= cat.getIdCategoria() %>">Editar</a>
            <a href="<%= request.getContextPath() %>/categoria?accion=eliminar&id=<%= cat.getIdCategoria() %>" onclick="return confirm('¿Desea eliminar esta categoría?');">Activar o Desactivar</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>