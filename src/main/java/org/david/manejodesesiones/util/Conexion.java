package org.david.manejodesesiones.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.david.manejodesesiones.util.Conexion.getConnection;

public class Conexion {
    //inicializo 3 variables globales
    //url de la bd
    private static String url="jdbc:mysql://localhost:3306/compra_venta?serverTimezone=UTC";
    //nombre del usuario de la bd
    private static String username="root";
    //contrase;a de la bd
    private static String password="";
    //implementamos un metodo para realizar la conexion
    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(url, username, password);
    //generar un metodo main que compruebe si la conexion esta correcta
    }
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos: " + url);
            } else {
                System.out.println("La conexión es nula.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
