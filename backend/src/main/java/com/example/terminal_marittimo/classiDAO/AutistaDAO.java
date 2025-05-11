package com.example.terminal_marittimo.classiDAO;
import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Autista;

import java.sql.*;
import java.util.ArrayList;

public class AutistaDAO 
{
    public void inserisciAutista(String nome, String cognome, String patente, String password) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "INSERT INTO autisti (nome, cognome, patente, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cognome);
            stmt.setString(3, patente);
            stmt.setString(4, password);
            stmt.executeUpdate();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Autista> trovaTuttiAutisti() 
    {
        ArrayList<Autista> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "SELECT * FROM autisti";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) 
            {
                Autista a = new Autista(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getString("patente"), rs.getString("password"));
                lista.add(a);
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminaAutista(int id) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "DELETE FROM autisti WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public int verificaCredenziali(String username, String password) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "SELECT id FROM autisti WHERE patente = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}

