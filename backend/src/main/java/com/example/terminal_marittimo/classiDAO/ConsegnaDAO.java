package com.example.terminal_marittimo.classiDAO;

import com.example.terminal_marittimo.classiDTO.*;
import com.example.terminal_marittimo.database;

import java.sql.*;
import java.util.ArrayList;

public class ConsegnaDAO {

    public void inserisciConsegna(int idBuono, int idAutista, String targa, float peso) {
        String sql = "INSERT INTO consegne (nbuono, id_autista, targa, peso) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idBuono);
            stmt.setInt(2, idAutista);
            stmt.setString(3, targa);
            stmt.setFloat(4, peso);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Consegna> trovaTutteConsegne() {
        ArrayList<Consegna> lista = new ArrayList<>();
    
        String sql = "SELECT c.id_consegna AS id_consegna, c.dt_consegna AS data_consegna, c.peso AS peso_consegna, " +
            "a.id AS id_autista, a.nome AS nome_autista, a.cognome AS cognome_autista, a.patente, a.password, " +
            "cam.targa, cam.marca, cam.modello, cam.colore, " +
            "b.nbuono AS id_buono, b.dt_emissione_buono AS data_buono, b.peso AS peso_buono, b.codice_conferma, " +
            "cli.id AS id_cliente, cli.nome AS nome_cliente, cli.cognome AS cognome_cliente, " +
            "cli.indirizzo AS indirizzo_cliente, cli.telefono AS telefono_cliente, cli.email AS email_cliente, " +
            "cli.nomeAzienda AS azienda_cliente, cli.password AS password_cliente, " +
            "p.id AS id_polizza, p.gg_franchigia, p.costo_gg, " +
            "m.id AS id_merce, m.tipo, " +
            "f.id AS id_fornitore, f.nome AS nome_fornitore, f.cognome AS cognome_fornitore, " +
            "f.indirizzo AS indirizzo_fornitore, f.telefono AS telefono_fornitore, f.email AS email_fornitore, " +
            "f.nomeAzienda AS azienda_fornitore, f.password AS password_fornitore, " +
            "v.id AS id_viaggio, v.dt_partenza, v.dt_arrivo, " +
            "n.id AS id_nave, n.nome AS nome_nave, n.anno_produzione, " +
            "t.id AS id_tipologia, t.tipologia AS nome_tipologia, " +
            "l.id AS id_linea, l.nome AS nome_linea, " +
            "pp.id AS id_porto_partenza, pp.porto AS nome_porto_partenza, pp.nazione AS nazione_partenza, " +
            "pd.id AS id_porto_arrivo, pd.porto AS nome_porto_arrivo, pd.nazione AS nazione_arrivo " +
            "FROM consegne c " +
            "JOIN autisti a ON c.id_autista = a.id " +
            "JOIN camion cam ON c.targa = cam.targa " +
            "JOIN buoni_consegna b ON c.nbuono = b.nbuono " +
            "JOIN cliente cli ON b.id_cliente = cli.id " +
            "JOIN polizze_carico p ON b.id_polizza = p.id " +
            "JOIN merci m ON p.id_merce = m.id " +
            "JOIN fornitore f ON p.id_fornitore = f.id " +
            "JOIN viaggi v ON p.id_viaggio = v.id " +
            "JOIN navi n ON v.id_nave = n.id " +
            "JOIN tipologia_navi t ON n.tipologia = t.id " +
            "JOIN linea l ON v.id_linea = l.id " +
            "JOIN porti pp ON l.porto_partenza = pp.id " +
            "JOIN porti pd ON l.porto_destinazione = pd.id";
    
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                Autista autista = new Autista(
                    rs.getInt("id_autista"),
                    rs.getString("nome_autista"),
                    rs.getString("cognome_autista"),
                    rs.getString("patente"),
                    rs.getString("password")
                );
    
                Camion camion = new Camion(
                    rs.getString("targa"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getString("colore")
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
                    rs.getString("tipo")
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
    
                // Porto partenza e destinazione
                Porto portoPartenza = new Porto(
                    rs.getInt("id_porto_partenza"),
                    rs.getString("nome_porto_partenza"),
                    rs.getString("nazione_partenza")
                );
    
                Porto portoArrivo = new Porto(
                    rs.getInt("id_porto_arrivo"),
                    rs.getString("nome_porto_arrivo"),
                    rs.getString("nazione_arrivo")
                );
    
                Linea linea = new Linea(
                    rs.getInt("id_linea"),
                    rs.getString("nome_linea"),
                    portoPartenza,
                    portoArrivo
                );
    
                TipologiaNave tipologia = new TipologiaNave(
                    rs.getInt("id_tipologia"),
                    rs.getString("nome_tipologia")
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
    
                Polizza polizza = new Polizza(
                    rs.getInt("id_polizza"),
                    viaggio,
                    fornitore,
                    cliente,
                    rs.getFloat("peso_buono"),
                    merce,
                    rs.getInt("gg_franchigia"),
                    rs.getFloat("costo_gg")
                );
    
                Buono buono = new Buono(
                    rs.getInt("id_buono"),
                    rs.getString("data_buono"),
                    polizza,
                    cliente,
                    rs.getFloat("peso_buono"),
                    rs.getString("codice_conferma")
                );
    
                Consegna consegna = new Consegna(
                    rs.getInt("id_consegna"),
                    buono,
                    rs.getString("data_consegna"),
                    rs.getFloat("peso_consegna"),
                    autista,
                    camion
                );
    
                lista.add(consegna);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lista;
    }

    public void eliminaConsegna(int id) {
        String sql = "DELETE FROM consegne WHERE id_consegna = ?";

        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
