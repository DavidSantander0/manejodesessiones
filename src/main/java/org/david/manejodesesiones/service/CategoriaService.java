package org.david.manejodesesiones.service;

import org.david.manejodesesiones.models.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> porNombre(String nombre);
    Categoria porId(Long id);
    void guardar(Categoria categoria);
    void eliminar(Long id);
}
