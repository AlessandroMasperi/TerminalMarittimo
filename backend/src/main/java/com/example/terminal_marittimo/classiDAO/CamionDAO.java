package com.example.terminal_marittimo.classiDAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Camion;

public class CamionDAO {

    public void inserisciCamion(String targa, String marca, String modello, String colore) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "INSERT INTO camion (targa, marca, modello, colore) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, targa);
            stmt.setString(2, marca);
            stmt.setString(3, modello);
            stmt.setString(4, colore);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Camion> trovaTuttiICamion() 
    {
        ArrayList<Camion> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "SELECT targa, marca, modello, colore FROM camion";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Camion camion = new Camion(
                    rs.getString("targa"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getString("colore")
                );
                lista.add(camion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminaCamion(String targa) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "DELETE FROM camion WHERE targa = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, targa);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
