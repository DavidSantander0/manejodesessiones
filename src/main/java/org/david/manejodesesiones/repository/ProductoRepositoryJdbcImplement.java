package org.david.manejodesesiones.repository;

import org.david.manejodesesiones.models.Productos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryJdbcImplement implements Repository<Productos> {

    private Connection conn;

    public ProductoRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Productos> listar() throws SQLException {
        List<Productos> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {
            while (rs.next()) {
                Productos p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Productos porId(Long id) throws SQLException {
        Productos producto = null;
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Productos producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre=?, tipo=?, precio=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, tipo, precio) VALUES (?, ?, ?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getTipo());
            stmt.setDouble(3, producto.getPrecio());
            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(4, producto.getId());
            }
            int rows = stmt.executeUpdate();
            if (rows > 0 && (producto.getId() == null || producto.getId() == 0)) {
                try (ResultSet rs = stmt.getGeneratedKeys()){
                    if (rs.next()){
                        producto.setId(rs.getLong(1));
                    }
                }
            }
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private static Productos getProducto(ResultSet rs) throws SQLException {
        Productos p = new Productos();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setTipo(rs.getString("tipo"));
        p.setPrecio(rs.getDouble("precio"));
        return p;
    }
}
