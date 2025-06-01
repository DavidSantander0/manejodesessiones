package org.david.manejodesesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.david.manejodesesiones.service.LoginService;
import org.david.manejodesesiones.service.LoginServiceSessionImplement;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

//path o anotación
@WebServlet({"/login","/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //implementamos el objeto de tipo sesion
        LoginService auth=new LoginServiceSessionImplement();
        //creamos una variable opcional
        //para obtener el nombre del ususario
        Optional<String> usernameOptional = auth.getUsername(req);


        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                //Creo la plantilla html
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Bienvenido</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<link rel='stylesheet' href='/manejodesesiones/css/styles  .css'>");
                out.println("<h1>Hola " + usernameOptional.get() + " ya iniciaste sesión anteriormente!</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Volver al inicio</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            // Crear la sesión y guardar el nombre de usuario
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            // Mostrar mensaje de bienvenida en respuesta
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Bienvenido a la app</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi APP, " + username + "</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>Cerrar sesión</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Inicio</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Redirigir al login si las credenciales son incorrectas
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
    }

}

