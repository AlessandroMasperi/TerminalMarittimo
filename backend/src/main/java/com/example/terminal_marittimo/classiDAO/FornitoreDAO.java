package com.example.terminal_marittimo.classiDAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Fornitore;

public class FornitoreDAO {

    public void inserisciFornitore(String nome, String cognome, String indirizzo, String telefono,String email, String nomeAzienda, String password) throws SQLException {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "INSERT INTO fornitori (nome, cognome, indirizzo, telefono, email, nomeAzienda, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cognome);
            stmt.setString(3, indirizzo);
            stmt.setString(4, telefono);
            stmt.setString(5, email);
            stmt.setString(6, nomeAzienda);
            stmt.setString(7, password);
            stmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Fornitore> trovaTuttiFornitori() 
    {
        ArrayList<Fornitore> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "SELECT * FROM fornitori";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) 
            {
                Fornitore f = new Fornitore(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getString("indirizzo"), rs.getString("telefono"), rs.getString("email"), rs.getString("nomeAzienda"), rs.getString("password"));
                lista.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminaFornitore(int id) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "DELETE FROM fornitori WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
