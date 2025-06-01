<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.david.manejodesesiones.models.Categoria" %>
<%
  Categoria categoria = (Categoria) request.getAttribute("categoria");
  if (categoria == null) {
    response.sendRedirect(request.getContextPath() + "/categoria");
    return;
  }
%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Editar Categoría</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productos.css">
</head>
<body>
<h1>Editar Categoría</h1>
<form action="${pageContext.request.contextPath}/categoria" method="post">
  <!-- Campo oculto: imprescindible para que el servlet sepa que es edición -->
  <input type="hidden" name="id" value="<%= categoria.getIdCategoria() %>" />
  <div>
    <label for="nombre">Nombre:</label>
    <input type="text" name="nombre" id="nombre" value="<%= categoria.getNombre() %>" required>
  </div>
  <div>
    <label for="descripcion">Descripción:</label>
    <input type="text" name="descripcion" id="descripcion" value="<%= categoria.getDescripcion() %>" required>
  </div>
  <div>
    <button type="submit">Actualizar Categoría</button>
  </div>
</form>
<a href="${pageContext.request.contextPath}/categoria">
  <button>Volver al Listado</button>
</a>
</body>
</html>