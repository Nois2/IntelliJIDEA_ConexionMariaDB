package sv.edu.udb.persona;

import java.sql.*;

public class VerPersona {
    public VerPersona() {
        try {
            // Se utiliza try-with-resources para asegurar que la conexión se cierre correctamente.
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost/personabdd", "root", "")) {

                // Operaciones con la conexión...
                // Objeto de clase Statement: permite ejecutar sentencias SQL sin parámetros
                try (Statement s = conexion.createStatement();
                     ResultSet rs = s.executeQuery("SELECT * FROM persona")) {

                    //  Recorre las filas de ResultSet, mostrando por pantalla los resultados
                    while (rs.next()) {
                        // Muestra los resultados de cada fila
                        System.out.println("ID: " + rs.getInt(1) +
                                "\nNombre: " + rs.getString(2) +
                                "\nEdad: " + rs.getString(3) +
                                "\nTelefono: " + rs.getString(4));
                        System.out.println("**** .....");

                    }
                } // Se cierra la conexión con la base de datos.
            } catch (SQLException e) {
                // Error SQL: Login/password o sentencia son erróneos
                System.out.println("ERROR: Fallo en SQL: " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            // Error si no puedo leer el driver de MySQL
            System.out.println("ERROR: No encuentro el driver de la BD: " + e.getMessage());
        }
    }

    // Método principal, instancia a objeto de la clase VerPersona
    public static void main(String[] args) {
        new VerPersona();
    }
}
