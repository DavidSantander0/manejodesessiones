package org.david.manejodesesiones.service;

import java.util.Optional;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
    Optional<String> getUsername(HttpServletRequest request);

}
