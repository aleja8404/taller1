

import java.sql.*;
import java.util.*;

public class EstudianteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/tallerjdbcm";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public EstudianteDAO() {
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS estudiantes (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nombre VARCHAR(50)," +
                "apellido VARCHAR(50)," +
                "correo VARCHAR(100) UNIQUE," +
                "edad INT," +
                "estado_civil VARCHAR(20)" +
                ")";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creando tabla: " + e.getMessage());
        }
    }

    public boolean insertar(Estudiante est) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getApellido());
            ps.setString(3, est.getCorreo());
            ps.setInt(4, est.getEdad());
            ps.setString(5, est.getEstadoCivil().name());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertando estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Estudiante est) {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getApellido());
            ps.setInt(3, est.getEdad());
            ps.setString(4, est.getEstadoCivil().name());
            ps.setString(5, est.getCorreo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error actualizando estudiante: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorCorreo(String correo) {
        String sql = "DELETE FROM estudiantes WHERE correo=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminando estudiante: " + e.getMessage());
            return false;
        }
    }

    public List<Estudiante> consultarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante est = new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        EstadoCivil.valueOf(rs.getString("estado_civil"))
                );
                lista.add(est);
            }
        } catch (SQLException e) {
            System.out.println("Error consultando estudiantes: " + e.getMessage());
        }
        return lista;
    }

    public Estudiante consultarPorCorreo(String correo) {
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estudiante(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getInt("edad"),
                            EstadoCivil.valueOf(rs.getString("estado_civil"))
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error consultando estudiante: " + e.getMessage());
        }
        return null;
    }
}
