-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 28.11.2019 klo 17:20
-- Palvelimen versio: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asiakastyo`
--

-- --------------------------------------------------------

--
-- Rakenne taululle `asiakas`
--

CREATE TABLE `asiakas` (
  `asiakasID` varchar(11) COLLATE utf8_swedish_ci NOT NULL,
  `etunimi` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `sukunimi` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  `sukupuoli` varchar(30) COLLATE utf8_swedish_ci NOT NULL,
  `asuinalue` char(5) COLLATE utf8_swedish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `asiakas`
--

INSERT INTO `asiakas` (`asiakasID`, `etunimi`, `sukunimi`, `sukupuoli`, `asuinalue`) VALUES
('022', 'Urho', 'Urhea', 'mies', '00980'),
('1', 'Aili', 'Allinen', 'nainen', '00750'),
('2', 'Enni', 'Elonen', 'nainen', '00640'),
('3', 'Matti', 'Mallinen', 'mies', '00790'),
('33-22', 'Malli', 'Mallikas', 'nainen', '00660'),
('34234', 'Helmi', 'Helminen', 'nainen', '00760'),
('4', 'Hannu', 'Heikkinen', 'mies', '00550'),
('5', 'Martta', 'Marttinen', 'nainen', '00120'),
('6', 'Helvi', 'Henttonen', 'nainen', '00230');

-- --------------------------------------------------------

--
-- Rakenne taululle `palvelutapahtuma`
--

CREATE TABLE `palvelutapahtuma` (
  `palvelutapahtumaID` int(11) NOT NULL,
  `asiakasID` varchar(11) COLLATE utf8_swedish_ci NOT NULL,
  `palvelunLaji` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  `ajankohta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `kesto` time NOT NULL,
  `kuvaus` varchar(500) COLLATE utf8_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `palvelutapahtuma`
--

INSERT INTO `palvelutapahtuma` (`palvelutapahtumaID`, `asiakasID`, `palvelunLaji`, `ajankohta`, `kesto`, `kuvaus`) VALUES
(1, '1', 'puhelu', '2019-03-03 11:00:00', '00:10:00', 'kotikäynnin sopiminen'),
(2, '1', 'kotikäynti', '2019-03-10 09:00:00', '02:10:00', 'palvelutarpeen arviointi'),
(3, '1', 'dokumentointi', '2019-03-10 13:00:00', '01:30:00', 'kotikäynnin kirjaaminen'),
(4, '1', 'puhelu', '2019-03-20 08:00:00', '00:10:00', 'kotikäynnin sopiminen'),
(5, '1', 'kotikäynti', '2019-03-25 11:00:00', '01:10:00', 'tutustumiskäynti Gesosta'),
(6, '1', 'dokumentointi', '2019-03-25 13:00:00', '00:30:00', 'kotikäynnin kirjaaminen'),
(7, '2', 'puhelu', '2019-03-20 07:00:00', '00:20:00', 'kyselin kuulumisia'),
(8, '3', 'saatto', '2019-02-10 09:00:00', '03:30:00', 'saatto pankkiin'),
(9, '4', 'toimistokäynti', '2019-02-03 08:00:00', '01:00:00', 'toimeentulotukihakemuksen täyttö'),
(10, '5', 'kotikäynti', '2019-02-15 11:00:00', '01:10:00', 'palvelutarpeen arviointi'),
(11, '5', 'kotikäynti', '2019-02-20 10:00:00', '02:10:00', 'Gesosta kotikäynti'),
(12, '5', 'kotikäynti', '2019-02-25 07:00:00', '03:30:00', 'Siivouksen järjestäminen'),
(13, '5', 'dokumentointi', '2019-02-15 13:00:00', '00:50:00', 'kotikäynnin kirjaaminen'),
(14, '5', 'dokumentointi', '2019-02-20 13:00:00', '01:00:00', 'kotikäynnin kirjaaminen'),
(15, '5', 'selvittely', '2019-02-21 11:00:00', '01:30:00', 'siivouksesta sopiminen'),
(16, '6', 'päätös', '2019-03-30 10:00:00', '00:10:00', 'ruokamaksusitoumus'),
(17, '33-22', 'dokumentointi', '2019-08-28 07:00:00', '01:00:00', ''),
(18, '022', 'kotikäynti', '2019-09-30 09:00:00', '01:30:00', 'kotikäynti');

-- --------------------------------------------------------

--
-- Rakenne taululle `tyontekija`
--

CREATE TABLE `tyontekija` (
  `tyontekijanro` varchar(10) COLLATE utf8_swedish_ci NOT NULL,
  `yksikko` varchar(100) COLLATE utf8_swedish_ci NOT NULL,
  `tyontekijaNimeke` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `etunimi` varchar(50) COLLATE utf8_swedish_ci NOT NULL,
  `sukunimi` varchar(50) COLLATE utf8_swedish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `tyontekija`
--

INSERT INTO `tyontekija` (`tyontekijanro`, `yksikko`, `tyontekijaNimeke`, `etunimi`, `sukunimi`) VALUES
('001', 'Gerontologinen sosiaalityö/Pohjoinen', 'Sosiaalityöntekijä', 'Sanna', 'Sallinen'),
('002', 'Gerontologinen sosiaalityö/Etelä', 'Sosiaaliohjaaja', 'Mari', 'Maijanen'),
('003', 'HelppiSeniori/Pohjoinen', 'Sosiaalityöntekijä', 'Jouko', 'Jalonen'),
('004', 'Gerontologinen sosiaalityö/Pohjoinen', 'Sosiaaliohjaaja', 'Siiri', 'Siitonen'),
('005', 'Gerontologinen sosiaalityö/Etelä', 'Sosiaalityöntekijä', 'Olli', 'Ohjaaja'),
('006', 'HelppiSeniori/Pohjoinen', 'Sosiaaliohjaaja', 'Toni', 'Toivola'),
('007', 'HelppiSeniori/Etelä', 'Sosiaalityöntekijä', 'Emmi', 'Elo'),
('008', 'HelppiSeniori/Etelä', 'Sosiaaliohjaaja', 'Jaana', 'Jalonen'),
('112', 'Gerontologinen sosiaalityö/Länsi', 'sosiaalityöntekijä', 'Tanja', 'Työntekijä');

-- --------------------------------------------------------

--
-- Rakenne taululle `tyoskentely`
--

CREATE TABLE `tyoskentely` (
  `tyoskentelyID` int(11) NOT NULL,
  `tyontekijanro` varchar(10) COLLATE utf8_swedish_ci NOT NULL,
  `palvelutapahtumaID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

--
-- Vedos taulusta `tyoskentely`
--

INSERT INTO `tyoskentely` (`tyoskentelyID`, `tyontekijanro`, `palvelutapahtumaID`) VALUES
(1, '003', 1),
(2, '003', 2),
(3, '003', 3),
(4, '001', 4),
(5, '001', 5),
(6, '001', 6),
(7, '004', 7),
(8, '004', 8),
(9, '002', 9),
(10, '007', 10),
(11, '005', 11),
(12, '002', 12),
(13, '007', 13),
(14, '005', 14),
(15, '002', 15),
(16, '008', 16),
(17, '112', 17),
(18, '001', 18);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `asiakas`
--
ALTER TABLE `asiakas`
  ADD PRIMARY KEY (`asiakasID`);

--
-- Indexes for table `palvelutapahtuma`
--
ALTER TABLE `palvelutapahtuma`
  ADD PRIMARY KEY (`palvelutapahtumaID`),
  ADD KEY `asiakasID` (`asiakasID`);

--
-- Indexes for table `tyontekija`
--
ALTER TABLE `tyontekija`
  ADD PRIMARY KEY (`tyontekijanro`);

--
-- Indexes for table `tyoskentely`
--
ALTER TABLE `tyoskentely`
  ADD PRIMARY KEY (`tyoskentelyID`),
  ADD KEY `tyontekijanro` (`tyontekijanro`),
  ADD KEY `palvelutapahtumaID` (`palvelutapahtumaID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `palvelutapahtuma`
--
ALTER TABLE `palvelutapahtuma`
  MODIFY `palvelutapahtumaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tyoskentely`
--
ALTER TABLE `tyoskentely`
  MODIFY `tyoskentelyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Rajoitteet vedostauluille
--

--
-- Rajoitteet taululle `palvelutapahtuma`
--
ALTER TABLE `palvelutapahtuma`
  ADD CONSTRAINT `palvelutapahtuma_ibfk_1` FOREIGN KEY (`asiakasID`) REFERENCES `asiakas` (`asiakasID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Rajoitteet taululle `tyoskentely`
--
ALTER TABLE `tyoskentely`
  ADD CONSTRAINT `tyoskentely_ibfk_1` FOREIGN KEY (`tyontekijanro`) REFERENCES `tyontekija` (`tyontekijanro`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `tyoskentely_ibfk_2` FOREIGN KEY (`palvelutapahtumaID`) REFERENCES `palvelutapahtuma` (`palvelutapahtumaID`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
