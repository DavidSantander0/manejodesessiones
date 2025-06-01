package org.david.manejodesesiones.filtro;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.david.manejodesesiones.service.LoginService;
import org.david.manejodesesiones.service.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;
@WebFilter({"/productos"})
public class LoginFilter implements Filter{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginService service = new LoginServiceSessionImplement();
        Optional<String> username = service.getUsername((HttpServletRequest)request);
        // realizo iuna condicional
        if (username.isPresent()) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta pagina");


        }
    }
}
