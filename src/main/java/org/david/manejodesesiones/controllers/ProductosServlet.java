package org.david.manejodesesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.david.manejodesesiones.models.Productos;
import org.david.manejodesesiones.service.ProductoService;
import org.david.manejodesesiones.service.ProductoServiceImplement;
import java.io.IOException;
import java.util.List;

@WebServlet("/productos")
public class ProductosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        String action = req.getParameter("action");
        if(action == null) {
            action = "listar";
        }
        switch(action) {
            case "editar": {
                Long id = Long.parseLong(req.getParameter("id"));
                Productos producto = service.porId(id);
                req.setAttribute("producto", producto);
                req.getRequestDispatcher("/editarProducto.jsp").forward(req, resp);
                break;
            }
            case "eliminar": {
                Long id = Long.parseLong(req.getParameter("id"));
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/productos");
                break;
            }
            default: {
                List<Productos> productos = service.listar();
                req.setAttribute("productos", productos);
                req.getRequestDispatcher("/listarProductos.jsp").forward(req, resp);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        Long id = (req.getParameter("id") != null && !req.getParameter("id").isEmpty())
                ? Long.parseLong(req.getParameter("id"))
                : null;
        String nombre = req.getParameter("nombre");
        String tipo = req.getParameter("tipo");
        Double precio = Double.parseDouble(req.getParameter("precio"));

        Productos producto = new Productos(id, nombre, tipo, precio);
        service.guardar(producto);
        resp.sendRedirect(req.getContextPath() + "/productos");
    }
}