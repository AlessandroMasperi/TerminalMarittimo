package com.example.terminal_marittimo.classiDAO;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.*;

import java.sql.*;
import java.util.ArrayList;

public class PolizzaDAO {

    public void inserisciPolizza(int idViaggio, int idFornitore, int idCliente, int idMerce, float peso,
            int ggFranchigia, float costoGg) {
        String sql = "INSERT INTO polizze_carico (id_viaggio, id_fornitore, id_cliente, id_merce, peso, gg_franchigia, costo_gg) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idViaggio);
            stmt.setInt(2, idFornitore);
            stmt.setInt(3, idCliente);
            stmt.setInt(4, idMerce);
            stmt.setFloat(5, peso);
            stmt.setInt(6, ggFranchigia);
            stmt.setFloat(7, costoGg);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Polizza> trovaTuttePolizze() {
        ArrayList<Polizza> lista = new ArrayList<>();

        String sql = "SELECT " +
                "p.id AS id_polizza, p.peso, p.gg_franchigia, p.costo_gg, " +
                "v.id AS id_viaggio, v.dt_partenza, v.dt_arrivo, " +
                "n.id AS id_nave, n.nome AS nome_nave, n.anno_produzione, " +
                "t.id AS id_tipologia, t.tipologia, " +
                "l.id AS id_linea, l.nome AS nome_linea, " +
                "pp.id AS id_porto_partenza, pp.porto AS nome_porto_partenza, pp.nazione AS nazionalita_porto_partenza, "
                +
                "pd.id AS id_porto_destinazione, pd.porto AS nome_porto_destinazione, pd.nazione AS nazionalita_porto_destinazione, "
                +

                "f.id AS id_fornitore, f.nome AS nome_fornitore, f.cognome AS cognome_fornitore, f.indirizzo AS indirizzo_fornitore, "
                +
                "f.telefono AS telefono_fornitore, f.email AS email_fornitore, f.nomeAzienda AS azienda_fornitore, f.password AS password_fornitore, "
                +

                "c.id AS id_cliente, c.nome AS nome_cliente, c.cognome AS cognome_cliente, c.indirizzo AS indirizzo_cliente, "
                +
                "c.telefono AS telefono_cliente, c.email AS email_cliente, c.nomeAzienda AS azienda_cliente, c.password AS password_cliente, "
                +

                "m.id AS id_merce, m.tipo AS nome_merce " +

                "FROM polizze_carico p " +
                "JOIN viaggi v ON p.id_viaggio = v.id " +
                "JOIN navi n ON v.id_nave = n.id " +
                "JOIN tipologia_navi t ON n.tipologia = t.id " +
                "JOIN linea l ON v.id_linea = l.id " +
                "JOIN porti pp ON l.porto_partenza = pp.id " +
                "JOIN porti pd ON l.porto_destinazione = pd.id " +
                "JOIN fornitore f ON p.id_fornitore = f.id " +
                "JOIN cliente c ON p.id_cliente = c.id " +
                "JOIN merci m ON p.id_merce = m.id";

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

                Viaggio viaggio = new Viaggio(
                        rs.getInt("id_viaggio"),
                        nave,
                        linea,
                        rs.getString("dt_partenza"),
                        rs.getString("dt_arrivo"));

                Fornitore fornitore = new Fornitore(
                        rs.getInt("id_fornitore"),
                        rs.getString("nome_fornitore"),
                        rs.getString("cognome_fornitore"),
                        rs.getString("indirizzo_fornitore"),
                        rs.getString("telefono_fornitore"),
                        rs.getString("email_fornitore"),
                        rs.getString("azienda_fornitore"),
                        rs.getString("password_fornitore"));

                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getString("cognome_cliente"),
                        rs.getString("indirizzo_cliente"),
                        rs.getString("telefono_cliente"),
                        rs.getString("email_cliente"),
                        rs.getString("azienda_cliente"),
                        rs.getString("password_cliente"));

                Merce merce = new Merce(
                        rs.getInt("id_merce"),
                        rs.getString("nome_merce"));

                Polizza polizza = new Polizza(
                        rs.getInt("id_polizza"),
                        viaggio,
                        fornitore,
                        cliente,
                        rs.getFloat("peso"),
                        merce,
                        rs.getInt("gg_franchigia"),
                        rs.getFloat("costo_gg"));

                lista.add(polizza);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<Polizza> trovaTuttePolizzeDatoIDCliente(int id_cliente_parametro) {
        ArrayList<Polizza> lista = new ArrayList<>();
    
        String sql = "SELECT " +
                "p.id AS id_polizza, p.peso, p.gg_franchigia, p.costo_gg, " +
                "v.id AS id_viaggio, v.dt_partenza, v.dt_arrivo, " +
                "n.id AS id_nave, n.nome AS nome_nave, n.anno_produzione, " +
                "t.id AS id_tipologia, t.tipologia, " +
                "l.id AS id_linea, l.nome AS nome_linea, " +
                "pp.id AS id_porto_partenza, pp.porto AS nome_porto_partenza, pp.nazione AS nazionalita_porto_partenza, " +
                "pd.id AS id_porto_destinazione, pd.porto AS nome_porto_destinazione, pd.nazione AS nazionalita_porto_destinazione, " +
                "f.id AS id_fornitore, f.nome AS nome_fornitore, f.cognome AS cognome_fornitore, f.indirizzo AS indirizzo_fornitore, " +
                "f.telefono AS telefono_fornitore, f.email AS email_fornitore, f.nomeAzienda AS azienda_fornitore, f.password AS password_fornitore, " +
                "c.id AS id_cliente, c.nome AS nome_cliente, c.cognome AS cognome_cliente, c.indirizzo AS indirizzo_cliente, " +
                "c.telefono AS telefono_cliente, c.email AS email_cliente, c.nomeAzienda AS azienda_cliente, c.password AS password_cliente, " +
                "m.id AS id_merce, m.tipo AS nome_merce " +
                "FROM polizze_carico p " +
                "JOIN viaggi v ON p.id_viaggio = v.id " +
                "JOIN navi n ON v.id_nave = n.id " +
                "JOIN tipologia_navi t ON n.tipologia = t.id " +
                "JOIN linea l ON v.id_linea = l.id " +
                "JOIN porti pp ON l.porto_partenza = pp.id " +
                "JOIN porti pd ON l.porto_destinazione = pd.id " +
                "JOIN fornitore f ON p.id_fornitore = f.id " +
                "JOIN cliente c ON p.id_cliente = c.id " +
                "JOIN merci m ON p.id_merce = m.id " +
                "WHERE c.id = ?";
    
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, id_cliente_parametro);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Porto partenza = new Porto(
                            rs.getInt("id_porto_partenza"),
                            rs.getString("nome_porto_partenza"),
                            rs.getString("nazionalita_porto_partenza")
                    );
    
                    Porto destinazione = new Porto(
                            rs.getInt("id_porto_destinazione"),
                            rs.getString("nome_porto_destinazione"),
                            rs.getString("nazionalita_porto_destinazione")
                    );
    
                    Linea linea = new Linea(
                            rs.getInt("id_linea"),
                            rs.getString("nome_linea"),
                            partenza,
                            destinazione
                    );
    
                    TipologiaNave tipologia = new TipologiaNave(
                            rs.getInt("id_tipologia"),
                            rs.getString("tipologia")
                    );
    
                    Nave nave = new Nave(
                            rs.getInt("id_nave"),
                            rs.getString("nome_nave"),
                            rs.getInt("anno_produzione"),
                            tipologia
                    );
    
                    Viaggio viaggio = new Viaggio(
                            rs.getInt("id_viaggio"),
                            nave,
                            linea,
                            rs.getString("dt_partenza"),
                            rs.getString("dt_arrivo")
                    );
    
                    Fornitore fornitore = new Fornitore(
                            rs.getInt("id_fornitore"),
                            rs.getString("nome_fornitore"),
                            rs.getString("cognome_fornitore"),
                            rs.getString("indirizzo_fornitore"),
                            rs.getString("telefono_fornitore"),
                            rs.getString("email_fornitore"),
                            rs.getString("azienda_fornitore"),
                            rs.getString("password_fornitore")
                    );
    
                    Cliente cliente = new Cliente(
                            rs.getInt("id_cliente"),
                            rs.getString("nome_cliente"),
                            rs.getString("cognome_cliente"),
                            rs.getString("indirizzo_cliente"),
                            rs.getString("telefono_cliente"),
                            rs.getString("email_cliente"),
                            rs.getString("azienda_cliente"),
                            rs.getString("password_cliente")
                    );
    
                    Merce merce = new Merce(
                            rs.getInt("id_merce"),
                            rs.getString("nome_merce")
                    );
    
                    Polizza polizza = new Polizza(
                            rs.getInt("id_polizza"),
                            viaggio,
                            fornitore,
                            cliente,
                            rs.getFloat("peso"),
                            merce,
                            rs.getInt("gg_franchigia"),
                            rs.getFloat("costo_gg")
                    );
    
                    lista.add(polizza);
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lista;
    }
    

    public void eliminaPolizza(int id) {
        String sql = "DELETE FROM polizze_carico WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
