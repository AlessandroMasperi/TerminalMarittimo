package com.example.terminal_marittimo.classiDAO;

import java.sql.*;
import java.util.ArrayList;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Merce;

public class MerciDAO {

    public ArrayList<Merce> trovaTutteLeTipologie() 
    {
        ArrayList<Merce> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "SELECT * FROM merci";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) 
            {
                Merce m = new Merce(rs.getInt("id"), rs.getString("tipo"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
