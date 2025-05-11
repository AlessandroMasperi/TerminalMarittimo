package com.example.terminal_marittimo.classiDAO;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Linea;
import com.example.terminal_marittimo.classiDTO.Nave;
import com.example.terminal_marittimo.classiDTO.Porto;
import com.example.terminal_marittimo.classiDTO.TipologiaNave;
import com.example.terminal_marittimo.classiDTO.Viaggio;

import java.sql.*;
import java.util.ArrayList;

public class ViaggioDAO {

    public void inserisciViaggio(int idNave, int idLinea, String dtPartenza, String dtArrivo) {
        String sql = "INSERT INTO viaggi (id_nave, id_linea, dt_partenza, dt_arrivo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            Timestamp partenza = Timestamp.valueOf(dtPartenza + " 00:00:00");
            Timestamp arrivo = Timestamp.valueOf(dtArrivo + " 00:00:00");

            stmt.setInt(1, idNave);
            stmt.setInt(2, idLinea);
            stmt.setTimestamp(3, partenza);
            stmt.setTimestamp(4, arrivo);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Viaggio> trovaTuttiViaggi() {
        ArrayList<Viaggio> lista = new ArrayList<>();
        String sql = "SELECT v.id AS id_viaggio, " +
                "n.id AS id_nave, n.nome AS nome_nave, n.anno_produzione, " +
                "t.id AS id_tipologia, " +
                "t.tipologia AS tipologia, " +
                "l.id AS id_linea, l.nome AS nome_linea, " +
                "pp.id AS id_porto_partenza, pp.porto AS nome_porto_partenza, pp.nazione AS nazionalita_porto_partenza, "
                +
                "pd.id AS id_porto_destinazione, pd.porto AS nome_porto_destinazione, pd.nazione AS nazionalita_porto_destinazione, "
                +
                "v.dt_partenza, v.dt_arrivo " +
                "FROM viaggi v " +
                "JOIN navi n ON v.id_nave = n.id " +
                "JOIN tipologia_navi t ON n.tipologia = t.id " +
                "JOIN linea l ON v.id_linea = l.id " +
                "JOIN porti pp ON l.porto_partenza = pp.id " +
                "JOIN porti pd ON l.porto_destinazione = pd.id " + 
                "ORDER BY v.dt_arrivo ASC";

        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Porto partenza = new Porto(
                        rs.getInt("id_porto_partenza"),
                        rs.getString("nome_porto_partenza"),
                        rs.getString("nazionalita_porto_partenza"));

                Porto destinazione = new Porto(
                        rs.getInt("id_porto_destinazione"),
                        rs.getString("nome_porto_destinazione"),
                        rs.getString("nazionalita_porto_destinazione"));

                Linea linea = new Linea(
                        rs.getInt("id_linea"),
                        rs.getString("nome_linea"),
                        partenza,
                        destinazione);

                TipologiaNave tipologia = new TipologiaNave(
                        rs.getInt("id_tipologia"),
                        rs.getString("tipologia"));

                Nave nave = new Nave(
                        rs.getInt("id_nave"),
                        rs.getString("nome_nave"),
                        rs.getInt("anno_produzione"),
                        tipologia);

                String dtPartenza = rs.getString("dt_partenza");
                String dtArrivo = rs.getString("dt_arrivo");

                Viaggio v = new Viaggio(rs.getInt("id_viaggio"), nave, linea, dtPartenza, dtArrivo);
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminaViaggio(int id) {
        String sql = "DELETE FROM viaggi WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
