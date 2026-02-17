package com.alfredo.data;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

//TODO Refactorizar a DTO

public class Conector {

    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String usuario = "mi_usuario"; // El que creamos antes
    private String password = "mi_password";

    public void crearEvento(
            String nombre,
            String fotos,
            String descripcion,
            String vigencia,
            String valor,
            String lugar){

        String sql = "INSERT INTO eventos (nombre_evento, fotos_evento, descripcion_evento, vigencia_evento, valor_evento, lugar_evento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, usuario, password);
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, nombre);
            ps.setString(2, fotos);
            ps.setString(3, descripcion);
            ps.setString(4, vigencia);
            ps.setString(5, valor);
            ps.setString(6, lugar);
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error al cargar datos en la tabla: " + e.getMessage());
        }

    }

    public String[] leerEventos(String id){
        String[] evento = new String[6];
        String sql = "SELECT nombre_evento, fotos_evento, descripcion_evento, vigencia_evento, valor_evento, lugar_evento FROM eventos WHERE event_id = " + id;

        try (Connection con = DriverManager.getConnection(url, usuario, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                evento[0] = rs.getString("nombre_evento");
                evento[1] = rs.getString("fotos_evento");
                evento[2] = rs.getString("descripcion_evento");
                evento[3] = rs.getString("vigencia_evento");
                evento[4] = rs.getString("valor_evento");
                evento[5] = rs.getString("lugar_evento");
            }

            System.out.printf(evento[1]);

        }
        catch (SQLException e){
            System.err.println("Error al cargar datos: " + e.getMessage());
        }
        return evento;
    }


    public void cargarDatosTabla(DefaultTableModel modelo) {

        String sql = "SELECT event_id, nombre_evento, valor_evento, lugar_evento FROM eventos";

        modelo.setRowCount(0);

        // Extraer datos y llenar el modelo
        try (Connection con = DriverManager.getConnection(url, usuario, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Object[] fila = new Object[4]; // Número de columnas
                fila[0] = rs.getInt("event_id");
                fila[1] = rs.getString("nombre_evento");
                fila[2] = rs.getString("valor_evento");
                fila[3] = rs.getString("lugar_evento");
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar datos en la tabla: " + e.getMessage());
            ;
        }
    }

    public int buscarEvento(String busqueda, DefaultTableModel modelo){


        String sql = "SELECT event_id, nombre_evento, valor_evento, lugar_evento FROM eventos " +
                "WHERE nombre_evento = ? OR  lugar_evento = ?";


        modelo.setRowCount(0);

        Object[] fila = new Object[4]; // Número de columnas

        try (Connection con = DriverManager.getConnection(url, usuario, password);
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, busqueda);
            ps.setString(2, busqueda);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                fila[0] = rs.getInt("event_id");
                fila[1] = rs.getString("nombre_evento");
                fila[2] = rs.getString("valor_evento");
                fila[3] = rs.getString("lugar_evento");
                modelo.addRow(fila);

            }

        }
        catch (SQLException e){
            System.err.println("Error al cargar datos en la tabla: " + e.getMessage());
        }

        if (fila[0] == null) {
            return 0;
        } else {
            return 1;
        }

    }

    public void modificarEvento(
            String id,
            String nombre,
            String fotos,
            String descripcion,
            String vigencia,
            String valor,
            String lugar
    ) {
        String sql = "UPDATE eventos SET nombre_evento = ?, fotos_evento = ?, descripcion_evento = ?," +
                " vigencia_evento = ?, valor_evento = ?, lugar_evento = ? WHERE event_id = ?";
        try (Connection con = DriverManager.getConnection(url, usuario, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, fotos);
            ps.setString(3, descripcion);
            ps.setString(4, vigencia);
            ps.setString(5, valor);
            ps.setString(6, lugar);
            ps.setInt(7, Integer.parseInt(id));
            ps.executeUpdate();

        }catch (SQLException e) {
            System.err.println("Error al actualizar datos en la tabla: " + e.getMessage());
        }
    }


    public void borrarEvento(String id) {
        String sql = "DELETE FROM eventos WHERE event_id = ?";
        try (Connection con = DriverManager.getConnection(url, usuario, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Error al cargar datos en la tabla: " + e.getMessage());
        }

    }
}
