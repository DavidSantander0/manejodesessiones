package org.david.manejodesesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.david.manejodesesiones.models.Categoria;
import org.david.manejodesesiones.service.CategoriaService;
import org.david.manejodesesiones.service.CategoriaServiceJdbcImplement;
import org.david.manejodesesiones.service.LoginService;
import org.david.manejodesesiones.service.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se obtiene la conexión desde la request (asegúrate de que un filtro o listener la establezca)
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        // Obtenemos el parámetro "accion" para determinar qué operación realizar
        String accion = req.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "nuevo":
                    // Redirige a la vista para agregar nueva categoría
                    req.getRequestDispatcher("/nuevoCategoria.jsp").forward(req, resp);
                    return;
                case "editar":
                    // Carga la categoría a editar a partir del id recibido
                    String idStr = req.getParameter("id");
                    if (idStr != null && !idStr.isEmpty()) {
                        Long id = Long.parseLong(idStr);
                        Categoria categoria = service.porId(id);
                        req.setAttribute("categoria", categoria);
                        req.getRequestDispatcher("/editarCategoria.jsp").forward(req, resp);
                        return;
                    }
                    break;
                case "eliminar":
                    // Elimina la categoría recibida por el id en la URL
                    String idEliminar = req.getParameter("id");
                    if (idEliminar != null && !idEliminar.isEmpty()) {
                        Long id = Long.parseLong(idEliminar);
                        service.eliminar(id);
                    }
                    resp.sendRedirect(req.getContextPath() + "/categoria");
                    return;
                default:
                    break;
            }
        }

        // Por defecto listar todas las categorías
        List<Categoria> categorias = service.listar();
        req.setAttribute("categorias", categorias);

        // Si manejas sesión de usuario, puedes obtener su username (o cadena vacía)
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> userName = auth.getUsername(req);
        req.setAttribute("username", userName.orElse(""));

        // Redirige a la vista que lista las categorías
        req.getRequestDispatcher("/categoriaListar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Se obtiene la conexión que debe haber sido asignada previamente (por ejemplo, en un filtro)
        Connection conn = (Connection) req.getAttribute("conn");
        // Se instancia el servicio que utilizará el repositorio JDBC
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        // Se recuperan los parámetros enviados desde el formulario
        // Si se trata de una edición, el formulario incluirá el campo "id". En alta, estará vacío o nulo.
        String idStr = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        // Se instancia el objeto Categoria y se asignan los valores del formulario
        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        // Si el campo "id" tiene contenido, se trata de una edición y se asigna el valor al objeto
        if (idStr != null && !idStr.trim().isEmpty()) {
            categoria.setIdCategoria(Long.parseLong(idStr));
        }

        // Se llama al metodo guardar
        service.guardar(categoria);

        // Finalmente, se redirige al listado para visualizar los cambios
        resp.sendRedirect(req.getContextPath() + "/categoria");
    }
}