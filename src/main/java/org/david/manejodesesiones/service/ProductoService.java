package org.david.manejodesesiones.service;

import org.david.manejodesesiones.models.Productos;
import java.util.List;

public interface ProductoService {
    List<Productos> listar();
    Productos porId(Long id);
    void guardar(Productos producto);
    void eliminar(Long id);
}