package com.example.terminal_marittimo.classiDAO;

import com.example.terminal_marittimo.database;
import com.example.terminal_marittimo.classiDTO.Cliente;
import com.example.terminal_marittimo.classiDTO.Fornitore;
import com.example.terminal_marittimo.classiDTO.Linea;
import com.example.terminal_marittimo.classiDTO.Merce;
import com.example.terminal_marittimo.classiDTO.Nave;
import com.example.terminal_marittimo.classiDTO.Polizza;
import com.example.terminal_marittimo.classiDTO.Porto;
import com.example.terminal_marittimo.classiDTO.Richiesta_buono;
import com.example.terminal_marittimo.classiDTO.TipologiaNave;
import com.example.terminal_marittimo.classiDTO.Viaggio;

import java.sql.*;
import java.util.ArrayList;

public class Richiesta_buonoDAO 
{
    //controllare il peso (somma richieste fatte riferimento stessa polizza) e somma ritiri peso
    public void inserisciRichiesta(int idCliente, int idPolizza, float peso) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "INSERT INTO richiesta_buono (cliente, polizza, peso) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            stmt.setInt(2, idPolizza);
            stmt.setFloat(3, peso);
            stmt.executeUpdate();   
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Richiesta_buono> trovaTutteRichieste() {
        ArrayList<Richiesta_buono> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = """
                        SELECT
                            rb.id AS id_richiesta,
                            rb.peso AS peso_richiesta,
                            c.id AS id_cliente,
                            c.nome AS nome_cliente,
                            c.cognome AS cognome_cliente,
                            c.indirizzo AS indirizzo_cliente,
                            c.telefono AS telefono_cliente,
                            c.email AS email_cliente,
                            c.nomeAzienda AS azienda_cliente,
                            c.password AS password_cliente,
                            f.id AS id_fornitore,
                            f.nome AS nome_fornitore,
                            f.cognome AS cognome_fornitore,
                            f.indirizzo AS indirizzo_fornitore,
                            f.telefono AS telefono_fornitore,
                            f.email AS email_fornitore,
                            f.nomeAzienda AS azienda_fornitore,
                            f.password AS password_fornitore,
                            m.id AS id_merce,
                            m.tipo AS tipologia,
                            pc.id AS id_polizza,
                            pc.gg_franchigia,
                            pc.costo_gg,
                            pc.peso AS peso_polizza,
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
                        FROM
                            richiesta_buono rb
                        JOIN cliente c ON rb.cliente = c.id
                        JOIN polizze_carico pc ON rb.polizza = pc.id
                        JOIN fornitore f ON pc.id_fornitore = f.id
                        JOIN merci m ON pc.id_merce = m.id
                        JOIN viaggi v ON pc.id_viaggio = v.id
                        JOIN navi n ON v.id_nave = n.id
                        JOIN tipologia_navi tn ON n.tipologia = tn.id
                        JOIN linea l ON v.id_linea = l.id
                        JOIN porti p1 ON l.porto_partenza = p1.id
                        JOIN porti p2 ON l.porto_destinazione = p2.id
                    """;
    
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
    
            while (rs.next()) {
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
                    rs.getString("tipologia")
                );
    
                Porto portoPartenza = new Porto(
                    rs.getInt("id_porto_partenza"),
                    rs.getString("porto_partenza"),
                    rs.getString("nazione_partenza")
                );
    
                Porto portoDestinazione = new Porto(
                    rs.getInt("id_porto_destinazione"),
                    rs.getString("porto_destinazione"),
                    rs.getString("nazione_destinazione")
                );
    
                Linea linea = new Linea(
                    rs.getInt("id_linea"),
                    rs.getString("nome_linea"),
                    portoPartenza,
                    portoDestinazione
                );
    
                TipologiaNave tipologiaNave = new TipologiaNave(
                    rs.getInt("id_tipologia_nave"),
                    rs.getString("descrizione_tipologia_nave")
                );
    
                Nave nave = new Nave(
                    rs.getInt("id_nave"),
                    rs.getString("nome_nave"),
                    rs.getInt("anno_produzione"),
                    tipologiaNave
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
                    rs.getFloat("peso_polizza"),
                    merce,
                    rs.getInt("gg_franchigia"),
                    rs.getFloat("costo_gg")
                );
    
                Richiesta_buono richiesta = new Richiesta_buono(
                    rs.getInt("id_richiesta"),
                    cliente,
                    polizza,
                    rs.getFloat("peso_richiesta")
                );
    
                lista.add(richiesta);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    


    public void eliminaRichiesta(int id) 
    {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
            String sql = "DELETE FROM richiesta_buono WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void approvaRichiesta(int idRichiesta) {
        try (Connection conn = DriverManager.getConnection(database.URL, database.USER, database.PASSWORD)) {
    
            String selectSql = "SELECT id_cliente, id_polizza, peso FROM richiesta_buono WHERE id = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setInt(1, idRichiesta);
            ResultSet rs = selectStmt.executeQuery();
    
            if (rs.next()) {
                eliminaRichiesta(idRichiesta);
    
                BuonoDAO dao = new BuonoDAO();
                dao.inserisciBuono(rs.getInt("id_polizza"), rs.getInt("id_cliente"), rs.getFloat("peso"));
            } else 
            {
                System.out.println("Richiesta non trovata per approvazione.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
