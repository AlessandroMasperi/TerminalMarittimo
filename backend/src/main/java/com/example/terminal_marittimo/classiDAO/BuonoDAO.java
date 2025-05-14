package com.example.terminal_marittimo.classiDAO;

import com.example.terminal_marittimo.database;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class BuonoDAO {

    public void inserisciBuono(int idPolizza, int idCliente, float peso) {
        String sql = "INSERT INTO buoni_consegna (dt_emissione_buono, id_polizza, id_cliente, peso, codice_conferma) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            Timestamp emissione = new Timestamp(System.currentTimeMillis());

            Random rand = new Random();
            int randomNum = rand.nextInt(9000) + 1000; // assicura 4 cifre
            String codiceConferma = "CONF" + randomNum;

            stmt.setTimestamp(1, emissione);
            stmt.setInt(2, idPolizza);
            stmt.setInt(3, idCliente);
            stmt.setDouble(4, peso);
            stmt.setString(5, codiceConferma);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Buono> trovaTuttiBuoni() {
        ArrayList<Buono> lista = new ArrayList<>();
        String sql = """
                SELECT
                    b.nbuono,
                    b.dt_emissione_buono,
                    b.peso,
                    b.codice_conferma,
                    c.id AS id_cliente,
                    c.nome AS nome_cliente,
                    c.cognome AS cognome_cliente,
                    c.indirizzo,
                    c.telefono,
                    c.email,
                    c.nomeAzienda,
                    c.password,
                    pc.id AS id_polizza,
                    pc.gg_franchigia,
                    pc.costo_gg,
                    pc.peso AS peso_polizza,
                    m.id AS id_merce,
                    m.tipo AS tipo_merce,
                    f.id AS id_fornitore,
                    f.nome AS nome_fornitore,
                    f.cognome AS cognome_fornitore,
                    f.indirizzo AS indirizzo_fornitore,
                    f.telefono AS telefono_fornitore,
                    f.email AS email_fornitore,
                    f.nomeAzienda AS azienda_fornitore,
                    f.password AS password_fornitore,
                    v.id AS id_viaggio,
                    v.dt_partenza,
                    v.dt_arrivo,
                    n.id AS id_nave,
                    n.nome AS nome_nave,
                    n.anno_produzione,
                    tn.id AS id_tipologia_nave,
                    tn.tipologia AS descrizione_tipologia_nave,
                    l.id AS id_linea,
                    l.nome AS nome_linea,
                    p1.id AS id_porto_partenza,
                    p1.porto AS porto_partenza,
                    p1.nazione AS nazione_partenza,
                    p2.id AS id_porto_destinazione,
                    p2.porto AS porto_destinazione,
                    p2.nazione AS nazione_destinazione
                FROM buoni_consegna b
                JOIN cliente c ON b.id_cliente = c.id
                JOIN polizze_carico pc ON b.id_polizza = pc.id
                JOIN fornitore f ON pc.id_fornitore = f.id
                JOIN merci m ON pc.id_merce = m.id
                JOIN viaggi v ON pc.id_viaggio = v.id
                JOIN navi n ON v.id_nave = n.id
                JOIN tipologia_navi tn ON n.tipologia = tn.id
                JOIN linea l ON v.id_linea = l.id
                JOIN porti p1 ON l.porto_partenza = p1.id
                JOIN porti p2 ON l.porto_destinazione = p2.id
                ORDER BY b.dt_emissione_buono ASC
                """;

        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nome_cliente"),
                        rs.getString("cognome_cliente"),
                        rs.getString("indirizzo"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("nomeAzienda"),
                        rs.getString("password"));

                Fornitore fornitore = new Fornitore(
                        rs.getInt("id_fornitore"),
                        rs.getString("nome_fornitore"),
                        rs.getString("cognome_fornitore"),
                        rs.getString("indirizzo_fornitore"),
                        rs.getString("telefono_fornitore"),
                        rs.getString("email_fornitore"),
                        rs.getString("azienda_fornitore"),
                        rs.getString("password_fornitore"));

                Merce merce = new Merce(
                        rs.getInt("id_merce"),
                        rs.getString("tipo_merce"));

                Porto partenza = new Porto(
                        rs.getInt("id_porto_partenza"),
                        rs.getString("porto_partenza"),
                        rs.getString("nazione_partenza"));

                Porto destinazione = new Porto(
                        rs.getInt("id_porto_destinazione"),
                        rs.getString("porto_destinazione"),
                        rs.getString("nazione_destinazione"));

                Linea linea = new Linea(
                        rs.getInt("id_linea"),
                        rs.getString("nome_linea"),
                        partenza,
                        destinazione);

                TipologiaNave tipologia = new TipologiaNave(
                        rs.getInt("id_tipologia_nave"),
                        rs.getString("descrizione_tipologia_nave"));

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

                Polizza polizza = new Polizza(
                        rs.getInt("id_polizza"),
                        viaggio,
                        fornitore,
                        cliente,
                        rs.getFloat("peso_polizza"),
                        merce,
                        rs.getInt("gg_franchigia"),
                        rs.getFloat("costo_gg"));

                Buono buono = new Buono(
                        rs.getInt("nbuono"),
                        rs.getString("dt_emissione_buono"),
                        polizza,
                        cliente,
                        rs.getFloat("peso"),
                        rs.getString("codice_conferma"));

                lista.add(buono);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void eliminaBuono(int nbuono) {
        String sql = "DELETE FROM buoni_consegna WHERE nbuono = ?";
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, nbuono);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
