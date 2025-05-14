package com.example.terminal_marittimo.classiDAO;

import com.example.terminal_marittimo.classiDTO.Autista;
import com.example.terminal_marittimo.classiDTO.Buono;
import com.example.terminal_marittimo.classiDTO.Cliente;
import com.example.terminal_marittimo.classiDTO.Fornitore;
import com.example.terminal_marittimo.classiDTO.Linea;
import com.example.terminal_marittimo.classiDTO.Merce;
import com.example.terminal_marittimo.classiDTO.Nave;
import com.example.terminal_marittimo.classiDTO.Polizza;
import com.example.terminal_marittimo.classiDTO.Porto;
import com.example.terminal_marittimo.classiDTO.TipologiaNave;
import com.example.terminal_marittimo.classiDTO.Viaggio;
import com.example.terminal_marittimo.classiDTO.Assegna_Consegna;
import com.example.terminal_marittimo.database;

import java.sql.*;
import java.util.ArrayList;

public class Assegna_consegnaDAO {

    public void inserisciAssegnazione(int idBuono, int idCliente, int idAutista) {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "INSERT INTO assegna_consegna (buono, cliente, autista) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBuono);
            stmt.setInt(2, idCliente);
            stmt.setInt(3, idAutista);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Assegna_Consegna> trovaTutteAssegnazioni() {
        ArrayList<Assegna_Consegna> lista = new ArrayList<>();
    
        String sql = """
            SELECT ac.id AS id_assegnazione,
    
                   c.id AS id_cliente, c.nome AS nome_cliente, c.cognome AS cognome_cliente,
                   c.indirizzo AS indirizzo_cliente, c.telefono AS telefono_cliente, c.email AS email_cliente,
                   c.nomeazienda AS azienda_cliente, c.password AS password_cliente,
    
                   a.id AS id_autista, a.nome AS nome_autista, a.cognome AS cognome_autista,
                   a.patente, a.password AS password_autista,
    
                   b.nbuono AS id_buono, b.dt_emissione_buono AS data_buono, b.codice_conferma,
    
                   p.id AS id_polizza, p.peso AS peso_polizza, p.gg_franchigia, p.costo_gg,
    
                   f.id AS id_fornitore, f.nome AS nome_fornitore, f.cognome AS cognome_fornitore,
                   f.indirizzo AS indirizzo_fornitore, f.telefono AS telefono_fornitore,
                   f.email AS email_fornitore, f.nomeazienda AS azienda_fornitore,
                   f.password AS password_fornitore,
    
                   m.id AS id_merce, m.tipo AS nome_merce,
    
                   v.id AS id_viaggio, v.dt_partenza, v.dt_arrivo,
    
                   n.id AS id_nave, n.nome AS nome_nave, n.anno_produzione,
    
                   t.id AS id_tipologia, t.tipologia,
    
                   l.id AS id_linea, l.nome AS nome_linea,
    
                   pp.id AS id_porto_partenza, pp.porto AS nome_porto_partenza, pp.nazione AS nazionalita_porto_partenza,
                   pd.id AS id_porto_destinazione, pd.porto AS nome_porto_destinazione, pd.nazione AS nazionalita_porto_destinazione
    
            FROM assegna_consegna ac
            JOIN cliente c ON ac.cliente = c.id
            JOIN autisti a ON ac.autista = a.id
            JOIN buoni_consegna b ON ac.buono = b.nbuono
            JOIN polizze_carico p ON b.id_polizza = p.id
            JOIN fornitore f ON p.id_fornitore = f.id
            JOIN merci m ON p.id_merce = m.id
            JOIN viaggi v ON p.id_viaggio = v.id
            JOIN navi n ON v.id_nave = n.id
            JOIN tipologia_navi t ON n.tipologia = t.id
            JOIN linea l ON v.id_linea = l.id
            JOIN porti pp ON l.porto_partenza = pp.id
            JOIN porti pd ON l.porto_destinazione = pd.id
        """;
    
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                Porto portoPartenza = new Porto(
                    rs.getInt("id_porto_partenza"),
                    rs.getString("nome_porto_partenza"),
                    rs.getString("nazionalita_porto_partenza")
                );
    
                Porto portoDestinazione = new Porto(
                    rs.getInt("id_porto_destinazione"),
                    rs.getString("nome_porto_destinazione"),
                    rs.getString("nazionalita_porto_destinazione")
                );
    
                Linea linea = new Linea(
                    rs.getInt("id_linea"),
                    rs.getString("nome_linea"),
                    portoPartenza,
                    portoDestinazione
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
    
                Autista autista = new Autista(
                    rs.getInt("id_autista"),
                    rs.getString("nome_autista"),
                    rs.getString("cognome_autista"),
                    rs.getString("patente"),
                    rs.getString("password_autista")
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
    
                Merce merce = new Merce(
                    rs.getInt("id_merce"),
                    rs.getString("nome_merce")
                );
    
                Polizza polizza = new Polizza(
                    rs.getInt("id_polizza"),
                    viaggio,
                    fornitore,
                    cliente,
                    rs.getFloat("peso_polizza"),
                    merce,
                    rs.getInt("gg_franchigia"),
                    rs.getFloat("costo_gg")
                );
    
                Buono buono = new Buono(
                    rs.getInt("id_buono"),
                    rs.getString("data_buono"),
                    polizza,
                    cliente,
                    rs.getFloat("peso_polizza"),
                    rs.getString("codice_conferma")
                );
    
                Assegna_Consegna assegnazione = new Assegna_Consegna(
                    rs.getInt("id_assegnazione"),
                    buono,
                    autista,
                    cliente
                );
    
                lista.add(assegnazione);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return lista;
    }
    
    

    public void eliminaAssegnazione(int id) {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "DELETE FROM assegna_consegna WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
