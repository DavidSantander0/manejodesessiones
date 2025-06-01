package org.david.manejodesesiones.repository;

import org.david.manejodesesiones.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImplemt implements Repository<Categoria> {

    //Creamos una variable donde vamos a guarda la conexisón
    private Connection conn;

    public CategoriaRepositoryJdbcImplemt(Connection conn) {
        this.conn = conn;
    }



    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * from categoria")) {
            while (rs.next()) {
                Categoria c = getCategoria(rs);
                categorias.add(c);
            }
        }

        return categorias;
    }


    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "select * from categoria where idcategoria=?")) {
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                categoria=getCategoria(rs);
                }
                }

        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {
        String sql;
        boolean actualizar = (categoria.getIdCategoria() != null && categoria.getIdCategoria() > 0);

        if (actualizar) {
            // Para actualizar se usan tres parámetros (nombre, descripción y el id)
            sql = "update categoria set nombre=?, descripcion=? where idcategoria=?";
        } else {
            // Para insertar, solo inviertes el nombre y la descripción
            sql = "insert into categoria(nombre, descripcion) VALUES (?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());

            if (actualizar) {
                stmt.setLong(3, categoria.getIdCategoria());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setCondicion(rs.getInt("condicion"));
        c.setIdCategoria(rs.getLong("idCategoria"));
        return c;
    }


}
