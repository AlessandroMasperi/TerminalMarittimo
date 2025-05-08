package com.example.terminal_marittimo.classiDAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.TipologiaNave;

public class TipologiaNaveDAO {

    public ArrayList<TipologiaNave> trovaTutteLeTipologie() 
    {
        ArrayList<TipologiaNave> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "SELECT * FROM tipologia_navi";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) 
            {
                TipologiaNave t = new TipologiaNave(rs.getInt("id"), rs.getString("tipologia"));
                lista.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
