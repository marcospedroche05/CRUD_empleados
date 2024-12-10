package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOJuguete {
    static Connection conn;
    private ArrayList<String> listaJuguetes;

    public DAOJuguete() {
        conn = DB.getConn();
        listaJuguetes = new ArrayList<>();
        listaJuguetes.add("Palo");
        listaJuguetes.add("Papel");
        listaJuguetes.add("Hueso");
        listaJuguetes.add("Cuerda");
        listaJuguetes.add("Carbon");
    }

    public ArrayList<DTOJuguete> getAllJuguetes() {
        ArrayList<DTOJuguete> juguetes = new ArrayList<>();

        try {
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM juguetes");
            sentencia.executeQuery();
            ResultSet resultado = sentencia.getResultSet();
            while(resultado.next()) {
                int id = resultado.getInt(1);
                String nombre = resultado.getString(2);
                juguetes.add(new DTOJuguete(id, nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return juguetes;
    }

    public ArrayList<DTOJuguete> getAllJuguetesOrdenadosById() {
        ArrayList<DTOJuguete> juguetes = new ArrayList<>();
        try {
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM juguetes ORDER BY id DESC");
            sentencia.executeQuery();
            ResultSet resultado = sentencia.getResultSet();
            while(resultado.next()) {
                int id = resultado.getInt(1);
                String nombre = resultado.getString(2);
                juguetes.add(new DTOJuguete(id, nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return juguetes;
    }

    public ArrayList<DTOJuguete> getAllJuguetesOrdenadosByName() {
        ArrayList<DTOJuguete> juguetes = new ArrayList<>();
        try {
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM juguetes ORDER BY nombre ASC");
            sentencia.executeQuery();
            ResultSet resultado = sentencia.getResultSet();
            while(resultado.next()) {
                int id = resultado.getInt(1);
                String nombre = resultado.getString(2);
                juguetes.add(new DTOJuguete(id, nombre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return juguetes;
    }


    public DTOJuguete getJugueteById(int id) {
        DTOJuguete juguete = null;

        try {
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM juguetes WHERE id = ?");
            sentencia.setInt(1, id);
            sentencia.executeQuery();
            ResultSet resultado = sentencia.getResultSet();
            while(resultado.next()) {
                juguete = new DTOJuguete(resultado.getInt(1), resultado.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return juguete;
    }

    public int addJuguete(DTOJuguete juguete) throws SQLException {
        PreparedStatement sentencia = conn.prepareStatement("INSERT INTO juguetes (id, nombre) VALUES (?,?)");
        sentencia.setInt(1, juguete.getId());
        sentencia.setString(2, juguete.getNombre());
        return sentencia.executeUpdate();
    }

    public int deleteJuguete(int id) throws SQLException {
        PreparedStatement sentencia = conn.prepareStatement("DELETE FROM juguetes WHERE id = ?");
        sentencia.setInt(1, id);
        return sentencia.executeUpdate();
    }

    public int deleteAllJuguetes() throws SQLException {
        PreparedStatement sentencia = conn.prepareStatement("DELETE * FROM juguetes");
        return sentencia.executeUpdate();
    }

    public int updateJuguete(DTOJuguete juguete) throws SQLException {
        PreparedStatement sentencia = conn.prepareStatement("UPDATE juguetes SET nombre = ? WHERE id = ?");
        sentencia.setString(1, juguete.getNombre());
        sentencia.setInt(2, juguete.getId());
        return sentencia.executeUpdate();
    }

    public void meterJuguetesPrueba() throws SQLException {
        for (int i = 5; i < 10; i++) {
            int num = (int)Math.floor(Math.random()*5);
            String nombreElemento = listaJuguetes.get(num);
            DTOJuguete juguete = new DTOJuguete(i, nombreElemento);
            addJuguete(juguete);
        }
    }


}
