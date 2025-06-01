package org.david.manejodesesiones.service;

import org.david.manejodesesiones.models.Categoria;
import org.david.manejodesesiones.repository.CategoriaRepositoryJdbcImplemt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImplement implements CategoriaService {

    private CategoriaRepositoryJdbcImplemt repositoryJdbc;

    public CategoriaServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc = new CategoriaRepositoryJdbcImplemt(conn);
    }

    @Override
    public List<Categoria> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException e) {
            // Se encapsula la excepción SQL en una excepción custom definida en el proyecto.
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Categoria> porNombre(String nombre) {
        try {
            // Se obtiene la lista de categorías y se filtra por nombre, ignorando mayúsculas/minúsculas.
            return repositoryJdbc.listar()
                    .stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(nombre))
                    .findFirst();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public Categoria porId(Long id) {
        try {
            return repositoryJdbc.porId(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public void guardar(Categoria categoria) {
        try {
            repositoryJdbc.guardar(categoria);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e);
        }
    }
}
