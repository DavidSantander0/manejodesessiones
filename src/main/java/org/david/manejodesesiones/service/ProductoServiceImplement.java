package org.david.manejodesesiones.service;


import org.david.manejodesesiones.models.Productos;
import org.david.manejodesesiones.repository.ProductoRepositoryJdbcImplement;
import org.david.manejodesesiones.util.Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductoServiceImplement implements ProductoService {

    private ProductoRepositoryJdbcImplement repository;

    public ProductoServiceImplement() {
        // Supón que tienes la clase ConexionBD similar a la utilizada para categorías.
        Connection conn = null;
        try {
            conn = Conexion.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.repository = new ProductoRepositoryJdbcImplement(conn);
    }

    @Override
    public List<Productos> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar productos", e);
        }
    }

    @Override
    public Productos porId(Long id) {
        try {
            return repository.porId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el producto por id", e);
        }
    }

    @Override
    public void guardar(Productos producto) {
        try {
            repository.guardar(producto);
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el producto", e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repository.eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el producto", e);
        }
    }
}
