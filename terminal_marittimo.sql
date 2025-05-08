-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 08, 2025 alle 18:47
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `terminal_marittimo`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin1', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `autisti`
--

CREATE TABLE `autisti` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `patente` varchar(10) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `autisti`
--

INSERT INTO `autisti` (`id`, `nome`, `cognome`, `patente`, `password`) VALUES
(1, 'Antonio', 'Pietro', 'B123456789', '5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Roberta', 'Sanna', 'C987654321', '5f4dcc3b5aa765d61d8327deb882cf99'),
(3, 'Francesco', 'Lupi', 'D345678901', '5f4dcc3b5aa765d61d8327deb882cf99'),
(4, 'Luca', 'Moretti', 'B234567890', '5f4dcc3b5aa765d61d8327deb882cf99'),
(5, 'Chiara', 'Gatti', 'C567890123', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `buoni_consegna`
--

CREATE TABLE `buoni_consegna` (
  `nbuono` int(11) NOT NULL,
  `dt_emissione_buono` date NOT NULL DEFAULT current_timestamp(),
  `id_polizza` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `peso` decimal(10,2) NOT NULL,
  `codice_conferma` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `buoni_consegna`
--

INSERT INTO `buoni_consegna` (`nbuono`, `dt_emissione_buono`, `id_polizza`, `id_cliente`, `peso`, `codice_conferma`) VALUES
(16, '2025-02-01', 107, 3, 1000.00, 'CONF1234'),
(17, '2025-01-15', 109, 5, 1500.00, 'CONF1235'),
(18, '2025-03-01', 120, 2, 1200.00, 'CONF1236'),
(19, '2025-02-20', 113, 4, 1800.00, 'CONF1237'),
(20, '2025-01-25', 110, 1, 900.00, 'CONF1238'),
(21, '2025-02-10', 116, 3, 1600.00, 'CONF1239'),
(22, '2025-03-05', 106, 5, 2000.00, 'CONF1240'),
(23, '2025-01-30', 114, 4, 1300.00, 'CONF1241'),
(24, '2025-02-12', 118, 2, 1100.00, 'CONF1242'),
(25, '2025-03-02', 115, 1, 1700.00, 'CONF1243'),
(26, '2025-02-22', 119, 5, 1400.00, 'CONF1244'),
(27, '2025-03-10', 112, 3, 1900.00, 'CONF1245'),
(28, '2025-01-18', 108, 2, 1100.00, 'CONF1246'),
(29, '2025-02-28', 111, 4, 1500.00, 'CONF1247'),
(30, '2025-03-08', 117, 1, 1200.00, 'CONF1248');

-- --------------------------------------------------------

--
-- Struttura della tabella `camion`
--

CREATE TABLE `camion` (
  `targa` varchar(8) NOT NULL,
  `marca` varchar(32) NOT NULL,
  `modello` varchar(32) NOT NULL,
  `colore` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `camion`
--

INSERT INTO `camion` (`targa`, `marca`, `modello`, `colore`) VALUES
('AB123CD', 'Scania', 'R450', 'Blu'),
('GH789KL', 'Iveco', 'Stralis X-Way', 'Giallo'),
('LM456OP', 'Mercedes', 'Actros 1843', 'Bianco'),
('QW234ER', 'MAN', 'TGX 18.440', 'Nero'),
('XY987ZT', 'Volvo', 'FH16', 'Rosso');

-- --------------------------------------------------------

--
-- Struttura della tabella `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `indirizzo` varchar(128) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `nomeAzienda` varchar(64) DEFAULT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `cognome`, `indirizzo`, `telefono`, `email`, `nomeAzienda`, `password`) VALUES
(1, 'Giovanni', 'Rossi', 'Via Roma 10, Milano', '3391234567', 'giovanni.rossi@example.com', 'TechSolutions', '5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Maria', 'Bianchi', 'Via delle Rose 22, Roma', '3352345678', 'maria.bianchi@example.com', 'GreenEnergy', '5f4dcc3b5aa765d61d8327deb882cf99'),
(3, 'Luca', 'Verdi', 'Corso Italia 15, Torino', '3383456789', 'luca.verdi@example.com', 'AutoCar', '5f4dcc3b5aa765d61d8327deb882cf99'),
(4, 'Francesca', 'Lombardi', 'Viale Europa 5, Napoli', '3344567890', 'francesca.lombardi@example.com', 'InnovativeSystems', '5f4dcc3b5aa765d61d8327deb882cf99'),
(5, 'Alessandro', 'Galli', 'Piazza San Marco 7, Venezia', '3375678901', 'alessandro.galli@example.com', 'DigitalConsulting', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `consegne`
--

CREATE TABLE `consegne` (
  `id_consegna` int(11) NOT NULL,
  `nbuono` int(11) DEFAULT NULL,
  `dt_consegna` date NOT NULL DEFAULT current_timestamp(),
  `peso` decimal(10,2) NOT NULL,
  `id_autista` int(11) DEFAULT NULL,
  `targa` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `consegne`
--

INSERT INTO `consegne` (`id_consegna`, `nbuono`, `dt_consegna`, `peso`, `id_autista`, `targa`) VALUES
(51, 20, '2025-03-01', 1000.00, 3, 'AB123CD'),
(52, 23, '2025-03-03', 1500.00, 2, 'GH789KL'),
(53, 30, '2025-03-05', 1200.00, 5, 'LM456OP'),
(54, 19, '2025-03-07', 1800.00, 4, 'QW234ER'),
(55, 25, '2025-03-09', 900.00, 1, 'XY987ZT'),
(56, 18, '2025-03-11', 1600.00, 3, 'AB123CD'),
(57, 22, '2025-03-13', 2000.00, 2, 'GH789KL'),
(58, 21, '2025-03-15', 1300.00, 4, 'LM456OP'),
(59, 24, '2025-03-17', 1100.00, 5, 'QW234ER'),
(60, 17, '2025-03-19', 1700.00, 3, 'XY987ZT');

-- --------------------------------------------------------

--
-- Struttura della tabella `fornitore`
--

CREATE TABLE `fornitore` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `indirizzo` varchar(128) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `nomeAzienda` varchar(64) DEFAULT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `fornitore`
--

INSERT INTO `fornitore` (`id`, `nome`, `cognome`, `indirizzo`, `telefono`, `email`, `nomeAzienda`, `password`) VALUES
(1, 'Marco', 'Luci', 'Via delle Acacie 12, Firenze', '3319876543', 'marco.luci@example.com', 'FurniTech', '5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Elena', 'Ferrari', 'Viale Marconi 33, Bologna', '3398765432', 'elena.ferrari@example.com', 'HomeStyle', '5f4dcc3b5aa765d61d8327deb882cf99'),
(3, 'Paolo', 'Gallo', 'Piazza della Liberta 9, Milano', '3387654321', 'paolo.gallo@example.com', 'BuildPro', '5f4dcc3b5aa765d61d8327deb882cf99'),
(4, 'Giulia', 'Mancini', 'Corso Buenos Aires 8, Torino', '3401234567', 'giulia.mancini@example.com', 'ElettroPlus', '5f4dcc3b5aa765d61d8327deb882cf99'),
(5, 'Stefano', 'Romano', 'Via Garibaldi 21, Genova', '3365432109', 'stefano.romano@example.com', 'FerroviaTech', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `linea`
--

CREATE TABLE `linea` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `porto_partenza` int(11) DEFAULT NULL,
  `porto_destinazione` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `linea`
--

INSERT INTO `linea` (`id`, `nome`, `porto_partenza`, `porto_destinazione`) VALUES
(11, 'Linea A', 1, 1),
(12, 'Linea B', 2, 1),
(13, 'Linea C', 3, 1),
(14, 'Linea D', 4, 1),
(16, 'Linea test', 10, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `merci`
--

CREATE TABLE `merci` (
  `id` int(11) NOT NULL,
  `tipo` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `merci`
--

INSERT INTO `merci` (`id`, `tipo`) VALUES
(1, 'Cibo'),
(2, 'Elettronica'),
(3, 'Abbigliamento'),
(4, 'Materiale edile'),
(5, 'Vino'),
(6, 'Fiori'),
(7, 'Farmaci'),
(8, 'Attrezzature sportive'),
(9, 'Giochi'),
(10, 'Mobili');

-- --------------------------------------------------------

--
-- Struttura della tabella `navi`
--

CREATE TABLE `navi` (
  `id` int(11) NOT NULL,
  `nome` varchar(64) NOT NULL,
  `anno_produzione` year(4) NOT NULL,
  `tipologia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `navi`
--

INSERT INTO `navi` (`id`, `nome`, `anno_produzione`, `tipologia`) VALUES
(1, 'Nave Mercantile 1', '2010', 1),
(2, 'Petroliera Oceanica', '2005', 2),
(3, 'Container Express', '2018', 3),
(4, 'Nave Rinfuse 2020', '2020', 4),
(5, 'Ro-Ro Swift', '2012', 5),
(6, 'Sea Explorer', '2016', 7);

-- --------------------------------------------------------

--
-- Struttura della tabella `operatori`
--

CREATE TABLE `operatori` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `operatori`
--

INSERT INTO `operatori` (`id`, `username`, `password`) VALUES
(1, 'operatore1', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `polizze_carico`
--

CREATE TABLE `polizze_carico` (
  `id` int(11) NOT NULL,
  `id_viaggio` int(11) DEFAULT NULL,
  `id_fornitore` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `peso` decimal(10,2) NOT NULL,
  `id_merce` int(11) DEFAULT NULL,
  `gg_franchigia` int(11) NOT NULL,
  `costo_gg` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `polizze_carico`
--

INSERT INTO `polizze_carico` (`id`, `id_viaggio`, `id_fornitore`, `id_cliente`, `peso`, `id_merce`, `gg_franchigia`, `costo_gg`) VALUES
(106, 5, 5, 2, 1000.00, 3, 5, 50.00),
(107, 2, 3, 1, 2000.00, 7, 10, 60.00),
(108, 8, 2, 3, 1500.00, 2, 7, 45.00),
(109, 3, 1, 4, 1200.00, 4, 5, 50.00),
(110, 9, 4, 5, 900.00, 6, 8, 55.00),
(111, 1, 4, 2, 1800.00, 8, 12, 65.00),
(112, 6, 3, 1, 1300.00, 5, 6, 48.00),
(113, 10, 5, 3, 1100.00, 10, 4, 42.00),
(114, 4, 5, 1, 1500.00, 9, 10, 60.00),
(115, 7, 1, 4, 1700.00, 3, 9, 55.00),
(116, 2, 5, 5, 1600.00, 7, 6, 50.00),
(117, 3, 1, 3, 1200.00, 2, 8, 53.00),
(118, 6, 3, 4, 1100.00, 4, 7, 52.00),
(119, 8, 3, 1, 1400.00, 8, 5, 60.00),
(120, 1, 4, 2, 1500.00, 5, 12, 65.00);

-- --------------------------------------------------------

--
-- Struttura della tabella `porti`
--

CREATE TABLE `porti` (
  `id` int(11) NOT NULL,
  `porto` varchar(64) NOT NULL,
  `nazione` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `porti`
--

INSERT INTO `porti` (`id`, `porto`, `nazione`) VALUES
(1, 'Porto di Genova', 'Italia'),
(2, 'Porto di Rotterdam', 'Paesi Bassi'),
(3, 'Porto di Barcellona', 'Spagna'),
(4, 'Porto di Amburgo', 'Germania'),
(5, 'Porto di Marsiglia', 'Francia'),
(6, 'Porto di New York', 'Stati Uniti'),
(7, 'Porto di Singapore', 'Singapore'),
(8, 'Porto di Shanghai', 'Cina'),
(9, 'Porto di Sydney', 'Australia'),
(10, 'Porto di Dubai', 'Emirati Arabi Uniti');

-- --------------------------------------------------------

--
-- Struttura della tabella `tipologia_navi`
--

CREATE TABLE `tipologia_navi` (
  `id` int(11) NOT NULL,
  `tipologia` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `tipologia_navi`
--

INSERT INTO `tipologia_navi` (`id`, `tipologia`) VALUES
(1, 'Cargo'),
(3, 'Container'),
(7, 'Nave da crociera'),
(9, 'Nave da ricerca'),
(8, 'Nave mercantile'),
(10, 'Nave ospedale'),
(6, 'Peschereccio'),
(2, 'Petroliera'),
(4, 'Rinfuse'),
(5, 'Ro-Ro');

-- --------------------------------------------------------

--
-- Struttura della tabella `viaggi`
--

CREATE TABLE `viaggi` (
  `id` int(11) NOT NULL,
  `id_nave` int(11) DEFAULT NULL,
  `id_linea` int(11) DEFAULT NULL,
  `dt_partenza` date NOT NULL,
  `dt_arrivo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `viaggi`
--

INSERT INTO `viaggi` (`id`, `id_nave`, `id_linea`, `dt_partenza`, `dt_arrivo`) VALUES
(1, 1, 11, '2025-04-01', '2025-04-05'),
(2, 2, 11, '2025-03-15', '2025-03-20'),
(3, 3, 12, '2025-04-10', '2025-04-15'),
(4, 1, 13, '2025-03-25', '2025-03-30'),
(5, 5, 14, '2025-04-05', '2025-04-10'),
(6, 6, 12, '2025-03-18', '2025-03-22'),
(7, 4, 12, '2025-04-02', '2025-04-06'),
(8, 3, 13, '2025-05-10', '2025-05-15'),
(9, 2, 11, '2025-05-12', '2025-05-17'),
(10, 6, 14, '2025-05-14', '2025-05-19');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indici per le tabelle `autisti`
--
ALTER TABLE `autisti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `patente` (`patente`);

--
-- Indici per le tabelle `buoni_consegna`
--
ALTER TABLE `buoni_consegna`
  ADD PRIMARY KEY (`nbuono`),
  ADD KEY `id_polizza` (`id_polizza`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indici per le tabelle `camion`
--
ALTER TABLE `camion`
  ADD PRIMARY KEY (`targa`);

--
-- Indici per le tabelle `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indici per le tabelle `consegne`
--
ALTER TABLE `consegne`
  ADD PRIMARY KEY (`id_consegna`),
  ADD KEY `nbuono` (`nbuono`),
  ADD KEY `id_autista` (`id_autista`),
  ADD KEY `targa` (`targa`);

--
-- Indici per le tabelle `fornitore`
--
ALTER TABLE `fornitore`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indici per le tabelle `linea`
--
ALTER TABLE `linea`
  ADD PRIMARY KEY (`id`),
  ADD KEY `porto_partenza` (`porto_partenza`),
  ADD KEY `porto_destinazione` (`porto_destinazione`);

--
-- Indici per le tabelle `merci`
--
ALTER TABLE `merci`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `navi`
--
ALTER TABLE `navi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tipologia` (`tipologia`);

--
-- Indici per le tabelle `operatori`
--
ALTER TABLE `operatori`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indici per le tabelle `polizze_carico`
--
ALTER TABLE `polizze_carico`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_viaggio` (`id_viaggio`),
  ADD KEY `id_fornitore` (`id_fornitore`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_merce` (`id_merce`);

--
-- Indici per le tabelle `porti`
--
ALTER TABLE `porti`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `tipologia_navi`
--
ALTER TABLE `tipologia_navi`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `tipologia` (`tipologia`);

--
-- Indici per le tabelle `viaggi`
--
ALTER TABLE `viaggi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nave` (`id_nave`),
  ADD KEY `id_linea` (`id_linea`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `autisti`
--
ALTER TABLE `autisti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `buoni_consegna`
--
ALTER TABLE `buoni_consegna`
  MODIFY `nbuono` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT per la tabella `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `consegne`
--
ALTER TABLE `consegne`
  MODIFY `id_consegna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT per la tabella `fornitore`
--
ALTER TABLE `fornitore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `linea`
--
ALTER TABLE `linea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT per la tabella `merci`
--
ALTER TABLE `merci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `navi`
--
ALTER TABLE `navi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `operatori`
--
ALTER TABLE `operatori`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `polizze_carico`
--
ALTER TABLE `polizze_carico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT per la tabella `porti`
--
ALTER TABLE `porti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `tipologia_navi`
--
ALTER TABLE `tipologia_navi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `viaggi`
--
ALTER TABLE `viaggi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `buoni_consegna`
--
ALTER TABLE `buoni_consegna`
  ADD CONSTRAINT `buoni_consegna_ibfk_1` FOREIGN KEY (`id_polizza`) REFERENCES `polizze_carico` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `buoni_consegna_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `consegne`
--
ALTER TABLE `consegne`
  ADD CONSTRAINT `consegne_ibfk_1` FOREIGN KEY (`nbuono`) REFERENCES `buoni_consegna` (`nbuono`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `consegne_ibfk_2` FOREIGN KEY (`id_autista`) REFERENCES `autisti` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `consegne_ibfk_3` FOREIGN KEY (`targa`) REFERENCES `camion` (`targa`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `linea`
--
ALTER TABLE `linea`
  ADD CONSTRAINT `linea_ibfk_1` FOREIGN KEY (`porto_partenza`) REFERENCES `porti` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `linea_ibfk_2` FOREIGN KEY (`porto_destinazione`) REFERENCES `porti` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `navi`
--
ALTER TABLE `navi`
  ADD CONSTRAINT `navi_ibfk_1` FOREIGN KEY (`tipologia`) REFERENCES `tipologia_navi` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `polizze_carico`
--
ALTER TABLE `polizze_carico`
  ADD CONSTRAINT `polizze_carico_ibfk_1` FOREIGN KEY (`id_viaggio`) REFERENCES `viaggi` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `polizze_carico_ibfk_2` FOREIGN KEY (`id_fornitore`) REFERENCES `fornitore` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `polizze_carico_ibfk_3` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `polizze_carico_ibfk_4` FOREIGN KEY (`id_merce`) REFERENCES `merci` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `viaggi`
--
ALTER TABLE `viaggi`
  ADD CONSTRAINT `viaggi_ibfk_1` FOREIGN KEY (`id_nave`) REFERENCES `navi` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `viaggi_ibfk_2` FOREIGN KEY (`id_linea`) REFERENCES `linea` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
