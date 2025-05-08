package com.example.terminal_marittimo.classiDAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Nave;
import com.example.terminal_marittimo.classiDTO.TipologiaNave;

public class NaveDAO {

    public void inserisciNave(String nome, int annoProduzione, int idTipologia) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "INSERT INTO navi (nome, anno_produzione, tipologia) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, annoProduzione);
            stmt.setInt(3, idTipologia);
            stmt.executeUpdate();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Nave> trovaTutteLeNavi() {
        ArrayList<Nave> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "SELECT n.id, n.nome, n.anno_produzione, t.id AS id_tipologia, t.tipologia FROM navi n JOIN tipologia_navi t ON n.tipologia = t.id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) 
            {
                TipologiaNave tipologia = new TipologiaNave(rs.getInt("id_tipologia"), rs.getString("tipologia"));
                Nave nave = new Nave(rs.getInt("id"), rs.getString("nome"), rs.getInt("anno_produzione"), tipologia);
                lista.add(nave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminaNave(int id) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) 
        {
            String sql = "DELETE FROM navi WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
