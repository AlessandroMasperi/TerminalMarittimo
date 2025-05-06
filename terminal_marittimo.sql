-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 06, 2025 alle 18:50
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
-- Struttura della tabella `autisti`
--

CREATE TABLE `autisti` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `patente` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `autisti`
--

INSERT INTO `autisti` (`id`, `nome`, `cognome`, `patente`, `password`) VALUES
(1, 'Marco', 'Rossi', 'BZ384932GR', '5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Luca', 'Verdi', 'BS342911FF', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `buoni_consegna`
--

CREATE TABLE `buoni_consegna` (
  `nbuono` int(11) NOT NULL,
  `dt_emissione_buono` date NOT NULL,
  `id_polizza` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `peso` decimal(10,2) NOT NULL,
  `dt_ritiro` date NOT NULL,
  `codice_conferma` varchar(64) NOT NULL,
  `isRitirato` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `buoni_consegna`
--

INSERT INTO `buoni_consegna` (`nbuono`, `dt_emissione_buono`, `id_polizza`, `id_cliente`, `peso`, `dt_ritiro`, `codice_conferma`, `isRitirato`) VALUES
(1, '2025-01-14', 1, 2, 1200.50, '2025-01-16', 'BC123456', 1),
(2, '2025-01-16', 2, 3, 980.00, '2025-01-18', 'BC654321', 1),
(3, '2025-02-02', 3, 1, 600.00, '2025-02-04', 'BC987654', 1),
(4, '2025-03-12', 4, 4, 1300.20, '2025-03-14', 'BC456123', 1),
(5, '2025-04-06', 5, 2, 450.50, '2025-04-08', 'BC321987', 1),
(6, '2025-04-11', 6, 6, 720.30, '2025-04-13', 'BC789654', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `camion`
--

CREATE TABLE `camion` (
  `targa` varchar(8) NOT NULL,
  `marca` varchar(64) NOT NULL,
  `modello` varchar(64) NOT NULL,
  `colore` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `camion`
--

INSERT INTO `camion` (`targa`, `marca`, `modello`, `colore`) VALUES
('AB123CD', 'IVECO', 'Eurocargo', 'Bianco'),
('EF456GH', 'Mercedes', 'Actros', 'Blu'),
('GG012HH', 'Scania', 'R500', 'Blu'),
('II345JJ', 'Volvo', 'FH16', 'Rosso'),
('KK678LL', 'Mercedes', 'Actros', 'Bianco'),
('LL890MM', 'MAN', 'TGX', 'Nero');

-- --------------------------------------------------------

--
-- Struttura della tabella `cliente`
--

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `indirizzo` varchar(128) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `email` varchar(64) NOT NULL,
  `nomeAzienda` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `cognome`, `indirizzo`, `telefono`, `email`, `nomeAzienda`, `password`) VALUES
(1, 'Giulia', 'Bianchi', 'Via Roma 12, Milano', '3471234567', 'giulia.bianchi@email.it', 'LogiTrans S.r.l.', '5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Stefano', 'Neri', 'Via Torino 88, Torino', '3497654321', 'stefano.neri@email.it', 'Trasporti Italia S.p.A.', '5f4dcc3b5aa765d61d8327deb882cf99'),
(3, 'Chiara', 'Russo', 'Via Milano 45, Roma', '3401122334', 'chiara.russo@email.it', 'Distribuzioni Roma S.p.A.', '5f4dcc3b5aa765d61d8327deb882cf99'),
(4, 'Francesco', 'Leoni', 'Corso Garibaldi 77, Bologna', '3399988776', 'francesco.leoni@email.it', 'LogiOne Logistics', '5f4dcc3b5aa765d61d8327deb882cf99'),
(5, 'Anna', 'Greco', 'Via Manzoni 10, Bari', '3386655443', 'anna.greco@email.it', 'Bari Cargo S.r.l.', '5f4dcc3b5aa765d61d8327deb882cf99'),
(6, 'Davide', 'Sanna', 'Piazza Dante 15, Cagliari', '3372211334', 'davide.sanna@email.it', 'Sardegna Trasporti', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `consegne`
--

CREATE TABLE `consegne` (
  `id_consegna` int(11) NOT NULL,
  `nbuono` int(11) NOT NULL,
  `dt_consegna` date NOT NULL DEFAULT current_timestamp(),
  `peso` decimal(10,2) NOT NULL,
  `id_autista` int(11) NOT NULL,
  `targa` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `consegne`
--

INSERT INTO `consegne` (`id_consegna`, `nbuono`, `dt_consegna`, `peso`, `id_autista`, `targa`) VALUES
(7, 3, '2025-02-05', 600.00, 2, 'EF456GH'),
(8, 6, '2025-04-14', 720.30, 1, 'AB123CD'),
(9, 4, '2025-03-15', 1300.20, 2, 'KK678LL'),
(10, 1, '2025-01-17', 1200.50, 1, 'LL890MM'),
(11, 2, '2025-01-19', 980.00, 2, 'II345JJ'),
(12, 5, '2025-04-09', 450.50, 2, 'GG012HH');

-- --------------------------------------------------------

--
-- Struttura della tabella `fornitore`
--

CREATE TABLE `fornitore` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `indirizzo` varchar(128) NOT NULL,
  `telefono` varchar(16) NOT NULL,
  `email` varchar(64) NOT NULL,
  `nomeAzienda` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `fornitore`
--

INSERT INTO `fornitore` (`id`, `nome`, `cognome`, `indirizzo`, `telefono`, `email`, `nomeAzienda`, `password`) VALUES
(1, 'Alessandro', 'Gallo', 'Via Napoli 10, Napoli', '3409988776', 'a.gallo@forniture.it', 'Forniture Gallo S.r.l.', '5f4dcc3b5aa765d61d8327deb882cf99'),
(2, 'Marta', 'Conti', 'Via Firenze 23, Firenze', '3381122334', 'm.conti@logistica.it', 'Conti Logistic Group', '5f4dcc3b5aa765d61d8327deb882cf99'),
(3, 'Elena', 'Marini', 'Via Veneto 20, Verona', '3358822441', 'elena.marini@fornituremarini.it', 'Forniture Marini', '5f4dcc3b5aa765d61d8327deb882cf99'),
(4, 'Giorgio', 'Ferrari', 'Via Liguria 12, Milano', '3347766552', 'giorgio.ferrari@logferrari.it', 'Ferrari Logistic', '5f4dcc3b5aa765d61d8327deb882cf99'),
(5, 'Laura', 'Ricci', 'Via Mazzini 3, Pisa', '3365544332', 'laura.ricci@riccigroup.it', 'Ricci Group S.p.A.', '5f4dcc3b5aa765d61d8327deb882cf99'),
(6, 'Matteo', 'Bianchi', 'Via Trieste 18, Trieste', '3394433221', 'matteo.bianchi@mblogistics.it', 'MB Logistics', '5f4dcc3b5aa765d61d8327deb882cf99');

-- --------------------------------------------------------

--
-- Struttura della tabella `linea`
--

CREATE TABLE `linea` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `porto_partenza` int(11) DEFAULT NULL,
  `porto_destinazione` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `linea`
--

INSERT INTO `linea` (`id`, `nome`, `porto_partenza`, `porto_destinazione`) VALUES
(1, 'Linea Italia-Spagna A', 1, 5),
(2, 'Linea Sud-Spagna', 2, 5),
(3, 'Linea Sicilia-Barcellona', 3, 5),
(4, 'Linea Sardegna-Francia', 4, 6);

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
(1, 'Alimenti deperibili'),
(2, 'Materiale elettronico'),
(3, 'Prodotti chimici'),
(4, 'Abbigliamento'),
(5, 'Macchinari industriali'),
(6, 'Mobili e arredamento');

-- --------------------------------------------------------

--
-- Struttura della tabella `navi`
--

CREATE TABLE `navi` (
  `id` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `anno_produzione` int(11) NOT NULL,
  `tipologia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `navi`
--

INSERT INTO `navi` (`id`, `nome`, `anno_produzione`, `tipologia`) VALUES
(1, 'Poseidone', 2010, 1),
(2, 'Mercurio', 2015, 2),
(3, 'Titanic II', 2020, 3),
(4, 'Oceano Blu', 2005, 4),
(5, 'Stella del Mare', 2018, 5),
(6, 'CargoMax', 2011, 2),
(7, 'Atlantis', 2008, 6),
(8, 'Freccia del Sud', 2019, 1),
(9, 'Nordic Wind', 2014, 5),
(10, 'Orizzonte', 2009, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `polizza_carico`
--

CREATE TABLE `polizza_carico` (
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
-- Dump dei dati per la tabella `polizza_carico`
--

INSERT INTO `polizza_carico` (`id`, `id_viaggio`, `id_fornitore`, `id_cliente`, `peso`, `id_merce`, `gg_franchigia`, `costo_gg`) VALUES
(1, 1, 1, 2, 1200.50, 1, 3, 15.50),
(2, 1, 2, 3, 980.00, 4, 2, 20.00),
(3, 2, 3, 1, 600.00, 2, 5, 12.00),
(4, 2, 4, 4, 1300.20, 5, 3, 18.75),
(5, 3, 5, 2, 450.50, 3, 1, 11.00),
(6, 3, 6, 6, 720.30, 6, 4, 21.00),
(7, 4, 1, 5, 1500.00, 1, 2, 19.50),
(8, 5, 2, 6, 850.00, 2, 3, 13.00),
(9, 5, 3, 1, 910.70, 4, 2, 17.25),
(10, 6, 4, 2, 1200.00, 5, 1, 10.00),
(11, 7, 5, 3, 780.20, 6, 4, 23.00),
(12, 8, 6, 4, 640.00, 1, 2, 14.00),
(13, 9, 1, 5, 1110.00, 3, 5, 16.50),
(14, 9, 2, 6, 950.50, 4, 3, 22.00),
(15, 10, 3, 1, 875.00, 2, 2, 12.75);

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
(1, 'Genova', 'Italia'),
(2, 'Napoli', 'Italia'),
(3, 'Palermo', 'Italia'),
(4, 'Cagliari', 'Italia'),
(5, 'Barcellona', 'Spagna'),
(6, 'Marsiglia', 'Francia'),
(7, 'Valencia', 'Spagna'),
(8, 'Amburgo', 'Germania'),
(9, 'Rotterdam', 'Paesi Bassi'),
(10, 'Pireo', 'Grecia');

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
(1, 'Cargo refrigerato'),
(2, 'Portacontainer'),
(3, 'Traghetto merci'),
(4, 'Petroliera'),
(5, 'Ro-Ro (Roll-on/Roll-off)'),
(6, 'Multipurpose vessel');

-- --------------------------------------------------------

--
-- Struttura della tabella `viaggi`
--

CREATE TABLE `viaggi` (
  `id` int(11) NOT NULL,
  `id_nave` int(11) DEFAULT NULL,
  `linea` int(11) DEFAULT NULL,
  `dt_partenza` date NOT NULL,
  `dt_arrivo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `viaggi`
--

INSERT INTO `viaggi` (`id`, `id_nave`, `linea`, `dt_partenza`, `dt_arrivo`) VALUES
(1, 5, 1, '2025-01-10', '2025-01-12'),
(2, 9, 2, '2025-01-15', '2025-01-18'),
(3, 1, 3, '2025-02-01', '2025-02-03'),
(4, 8, 1, '2025-03-10', '2025-03-12'),
(5, 6, 2, '2025-04-05', '2025-04-08'),
(6, 2, 3, '2025-04-10', '2025-04-12'),
(7, 3, 1, '2025-05-01', '2025-05-06'),
(8, 7, 2, '2025-05-03', '2025-05-07'),
(9, 10, 3, '2025-05-05', '2025-05-10'),
(10, 4, 1, '2025-05-06', '2025-05-12'),
(11, 9, 4, '2025-01-05', '2025-01-08'),
(12, 1, 4, '2025-03-20', '2025-03-23'),
(13, 8, 4, '2025-04-01', '2025-04-04'),
(14, 6, 4, '2025-05-04', '2025-05-09');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `autisti`
--
ALTER TABLE `autisti`
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`id`);

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
-- Indici per le tabelle `polizza_carico`
--
ALTER TABLE `polizza_carico`
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
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `viaggi`
--
ALTER TABLE `viaggi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_nave` (`id_nave`),
  ADD KEY `linea` (`linea`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `autisti`
--
ALTER TABLE `autisti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `buoni_consegna`
--
ALTER TABLE `buoni_consegna`
  MODIFY `nbuono` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `consegne`
--
ALTER TABLE `consegne`
  MODIFY `id_consegna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT per la tabella `fornitore`
--
ALTER TABLE `fornitore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `linea`
--
ALTER TABLE `linea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `merci`
--
ALTER TABLE `merci`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `navi`
--
ALTER TABLE `navi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `polizza_carico`
--
ALTER TABLE `polizza_carico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT per la tabella `porti`
--
ALTER TABLE `porti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT per la tabella `tipologia_navi`
--
ALTER TABLE `tipologia_navi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `viaggi`
--
ALTER TABLE `viaggi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `buoni_consegna`
--
ALTER TABLE `buoni_consegna`
  ADD CONSTRAINT `buoni_consegna_ibfk_1` FOREIGN KEY (`id_polizza`) REFERENCES `polizza_carico` (`id`),
  ADD CONSTRAINT `buoni_consegna_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`);

--
-- Limiti per la tabella `consegne`
--
ALTER TABLE `consegne`
  ADD CONSTRAINT `consegne_ibfk_1` FOREIGN KEY (`nbuono`) REFERENCES `buoni_consegna` (`nbuono`),
  ADD CONSTRAINT `consegne_ibfk_2` FOREIGN KEY (`id_autista`) REFERENCES `autisti` (`id`),
  ADD CONSTRAINT `consegne_ibfk_3` FOREIGN KEY (`targa`) REFERENCES `camion` (`targa`);

--
-- Limiti per la tabella `linea`
--
ALTER TABLE `linea`
  ADD CONSTRAINT `linea_ibfk_1` FOREIGN KEY (`porto_partenza`) REFERENCES `porti` (`id`),
  ADD CONSTRAINT `linea_ibfk_2` FOREIGN KEY (`porto_destinazione`) REFERENCES `porti` (`id`);

--
-- Limiti per la tabella `navi`
--
ALTER TABLE `navi`
  ADD CONSTRAINT `navi_ibfk_1` FOREIGN KEY (`tipologia`) REFERENCES `tipologia_navi` (`id`);

--
-- Limiti per la tabella `polizza_carico`
--
ALTER TABLE `polizza_carico`
  ADD CONSTRAINT `polizza_carico_ibfk_1` FOREIGN KEY (`id_viaggio`) REFERENCES `viaggi` (`id`),
  ADD CONSTRAINT `polizza_carico_ibfk_2` FOREIGN KEY (`id_fornitore`) REFERENCES `fornitore` (`id`),
  ADD CONSTRAINT `polizza_carico_ibfk_3` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `polizza_carico_ibfk_4` FOREIGN KEY (`id_merce`) REFERENCES `merci` (`id`);

--
-- Limiti per la tabella `viaggi`
--
ALTER TABLE `viaggi`
  ADD CONSTRAINT `viaggi_ibfk_1` FOREIGN KEY (`id_nave`) REFERENCES `navi` (`id`),
  ADD CONSTRAINT `viaggi_ibfk_2` FOREIGN KEY (`linea`) REFERENCES `linea` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
