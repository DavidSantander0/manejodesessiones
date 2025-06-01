package org.david.manejodesesiones.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {
        //obtenemos la sesion
        HttpSession session = request.getSession();
        //Convierto los datos de la sesion en un String
        String username = (String)session.getAttribute("username");
        /* creo una condicion en la cual valido
        si al obtener el nombre del usuario es distinto de nulo
        Obtengo el username
        caso contrario devuelvo la sesion vacia
         */
        if(username!=null){
            return Optional.of(username);
        }
        return Optional.empty();
    }
}
