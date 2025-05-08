package com.example.terminal_marittimo.classiDAO;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Linea;
import com.example.terminal_marittimo.classiDTO.Porto;

import java.sql.*;
import java.util.ArrayList;

public class LineaDAO {

    public void inserisciLinea(String nome, int idPartenza, int idDestinazione) {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "INSERT INTO linea (nome, porto_partenza, porto_destinazione) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, idPartenza);
            stmt.setInt(3, idDestinazione);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Linea> trovaTutteLinee() {
        ArrayList<Linea> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "SELECT l.id, l.nome, p1.id AS id_partenza, p1.porto AS porto_partenza, p1.nazione AS nazione_partenza, p2.id AS id_destinazione, p2.porto AS porto_destinazione, p2.nazione AS nazione_destinazione FROM linea l JOIN porti p1 ON l.porto_partenza = p1.id JOIN porti p2 ON l.porto_destinazione = p2.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Porto partenza = new Porto(rs.getInt("id_partenza"), rs.getString("porto_partenza"),
                        rs.getString("nazione_partenza"));
                Porto destinazione = new Porto(rs.getInt("id_destinazione"), rs.getString("porto_destinazione"),
                        rs.getString("nazione_destinazione"));
                Linea linea = new Linea(rs.getInt("id"), rs.getString("nome"), partenza, destinazione);
                lista.add(linea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminaLinea(int id) {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "DELETE FROM linea WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
