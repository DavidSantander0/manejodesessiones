package org.david.manejodesesiones.controllers;

import java.io.IOException;
import java.util.Optional;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.david.manejodesesiones.service.LoginService;
import org.david.manejodesesiones.service.LoginServiceSessionImplement;

@WebServlet({"/logout"})
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginService auth=new LoginServiceSessionImplement();
        Optional<String> userNameOptional = auth.getUsername(req);
        if(userNameOptional.isPresent()){
            HttpSession session = req.getSession();
            //cerramos la sesion
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath()+"/login.html");
    }

}
