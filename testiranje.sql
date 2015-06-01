-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2015 at 05:03 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tim3`
--

CREATE DATABASE `Tim3` DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
USE `Tim3`;

-- --------------------------------------------------------

CREATE USER 'EtfSI2014'@'localhost' IDENTIFIED BY '2014SIEtf';

GRANT ALL ON `Tim3`.* TO `EtfSI2014`;

-- --------------------------------------------------------

--
-- Table structure for table `autobus`
--

CREATE TABLE IF NOT EXISTS `Autobus` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODEL` varchar(255) DEFAULT NULL,
  `KAPACITET` int(11) DEFAULT NULL,
  `REGISTRACIJA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB   AUTO_INCREMENT=9 ;

--
-- Dumping data for table `autobus`
--

INSERT INTO `Autobus` (`ID`, `MODEL`, `KAPACITET`, `REGISTRACIJA`) VALUES
(1, 'Setra SG 221 U', 40, 'A23-T-456'),
(2, 'Setra SG 221 U', 40, 'T21-K-987'),
(3, 'MAN Lion´s Coach', 40, 'A98-M-986'),
(4, 'MAN Lion´s Coach', 50, 'K90-K-097'),
(5, 'MAN Lion´s Coach', 60, 'A21-M-098'),
(6, 'MAN Lion´s Coach', 60, 'A34-K-098'),
(7, 'Setra SG 221 UL', 50, 'A67-A-456'),
(8, 'Setra SG 221 UL', 35, 'A78-O-678');

-- --------------------------------------------------------

--
-- Table structure for table `autobuskalinija`
--

CREATE TABLE IF NOT EXISTS `AutobuskaLinija` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POLAZISTE` varchar(255) DEFAULT NULL,
  `ODREDISTE` varchar(255) DEFAULT NULL,
  `AUTOBUS` bigint(20) DEFAULT NULL,
  `VOZAC` bigint(20) DEFAULT NULL,
  `DATUMPOLASKA_DAN` int(11) DEFAULT NULL,
  `DATUMPOLASKA_MJESEC` int(11) DEFAULT NULL,
  `DATUMPOLASKA_GODINA` int(11) DEFAULT NULL,
  `VRIJEMEPOLASKA_SATI` int(11) DEFAULT NULL,
  `VRIJEMEPOLASKA_MINUTE` int(11) DEFAULT NULL,
  `PERON` int(11) DEFAULT NULL,
  `DISTANCA` double DEFAULT NULL,
  `TRAJANJE` double DEFAULT NULL,
  `BROJLINIJE` int(11) DEFAULT NULL,
  `CIJENAJEDNOSMJERNA` double DEFAULT NULL,
  `CIJENADVOSMJERNA` double DEFAULT NULL,
  `MEDJUNARODNA` bit(1) DEFAULT NULL,
  `ZAUZETO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_6s85fip3v6f0ssgfygehx4myp` (`AUTOBUS`),
  KEY `FK_49krsit0b1oj279n79rq2rhh6` (`VOZAC`)
) ENGINE=InnoDB   AUTO_INCREMENT=7 ;

--
-- Dumping data for table `autobuskalinija`
--

INSERT INTO `AutobuskaLinija` (`ID`, `POLAZISTE`, `ODREDISTE`, `AUTOBUS`, `VOZAC`, `DATUMPOLASKA_DAN`, `DATUMPOLASKA_MJESEC`, `DATUMPOLASKA_GODINA`, `VRIJEMEPOLASKA_SATI`, `VRIJEMEPOLASKA_MINUTE`, `PERON`, `DISTANCA`, `TRAJANJE`, `BROJLINIJE`, `CIJENAJEDNOSMJERNA`, `CIJENADVOSMJERNA`, `MEDJUNARODNA`, `ZAUZETO`) VALUES
(1, 'Sarajevo', 'Tuzla', 1, 7, 24, 5, 2015, 17, 0, 4, 128, 3, 3, 20, 30, b'0', 10),
(2, 'Sarajevo', 'Mostar', 2, 8, 14, 5, 2015, 8, 0, 1, 100, 2, 2, 15, 25, b'0', 0),
(3, 'Sarajevo', 'Biha?', 3, 9, 14, 5, 2015, 7, 0, 2, 505, 6, 1, 45, 60, b'0', 0),
(4, 'Sarajevo', 'Travnik', 4, 10, 15, 5, 2015, 9, 0, 5, 70, 2, 5, 15, 20, b'0', 0),
(5, 'Sarajevo', 'Neum', 5, 11, 30, 5, 2015, 6, 0, 2, 150, 4, 8, 40, 70, b'0', 0),
(6, 'Sarajevo', 'Herceg Novi', 1, 12, 31, 5, 2015, 10, 0, 2, 400, 4, 6, 70, 100, b'0', 0);

-- --------------------------------------------------------

--
-- Table structure for table `karta`
--

CREATE TABLE IF NOT EXISTS `Karta` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `linija` bigint(20) DEFAULT NULL,
  `VRIJEMEKupovine_SATI` int(11) DEFAULT NULL,
  `VRIJEMEkupovine_MINUTE` int(11) DEFAULT NULL,
  `TIPKARTE` varchar(255) DEFAULT NULL,
  `DATUMkupovine_DAN` int(11) DEFAULT NULL,
  `DATUMkupovine_MJESEC` int(11) DEFAULT NULL,
  `DATUMkupovine_GODINA` int(11) DEFAULT NULL,
  `CIJENA` double DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_2o637y6nuuy4429flb6k9t16o` (`linija`)
) ENGINE=InnoDB   AUTO_INCREMENT=6 ;

--
-- Dumping data for table `karta`
--

INSERT INTO `Karta` (`ID`, `linija`, `VRIJEMEKupovine_SATI`, `VRIJEMEkupovine_MINUTE`, `TIPKARTE`, `DATUMkupovine_DAN`, `DATUMkupovine_MJESEC`, `DATUMkupovine_GODINA`, `CIJENA`) VALUES
(1, 1, 4, 56, 'jednosmjerna', 24, 5, 2015, 20),
(2, 1, 4, 56, 'jednosmjerna', 24, 5, 2015, 20),
(3, 1, 4, 56, 'jednosmjerna', 24, 5, 2015, 20),
(4, 1, 4, 56, 'dvosmjerna', 24, 5, 2015, 30),
(5, 1, 4, 56, 'dvosmjerna', 24, 5, 2015, 30);

-- --------------------------------------------------------

--
-- Table structure for table `korisnickiracun`
--

CREATE TABLE IF NOT EXISTS `korisnickiracun` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `KORISNICKOIME` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `SIFRA` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `radnik` bigint(20) DEFAULT NULL,
  `TIPKORISNICKOGRACUNA` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `ONLINE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_7w97gmc3yjar1cxat94lblpur` (`radnik`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

--
-- Dumping data for table `korisnickiracun`
--

INSERT INTO `KorisnickiRacun` (`ID`, `KORISNICKOIME`, `SIFRA`, `radnik`, `TIPKORISNICKOGRACUNA`, `ONLINE`) VALUES
(4, 'admin', 'admin123!', 3, 'administrator', 0),
(5, 'Fahrudin', 'fahrudin10!', 4, 'menadzer', 0),
(6, 'Aida', 'Aida1000!', 6, 'salterskiRadnik', 0);

-- --------------------------------------------------------

--
-- Table structure for table `medjunarodnakarta`
--

CREATE TABLE IF NOT EXISTS `MedjunarodnaKarta` (
  `ID` bigint(20) NOT NULL,
  `IME` varchar(255) DEFAULT NULL,
  `PREZIME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB ;

-- --------------------------------------------------------

--
-- Table structure for table `nalog`
--

CREATE TABLE IF NOT EXISTS `Nalog` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUTOBUSKALINIJA` bigint(20) DEFAULT NULL,
  `DATUMPOLASKA_DAN` int(11) DEFAULT NULL,
  `DATUMPOLASKA_MJESEC` int(11) DEFAULT NULL,
  `DATUMPOLASKA_GODINA` int(11) DEFAULT NULL,
  `VRIJEMEPOLASKA_SATI` int(11) DEFAULT NULL,
  `VRIJEMEPOLASKA_MINUTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_a84m1ahi7o58sj09my6xjn6d1` (`AUTOBUSKALINIJA`)
) ENGINE=InnoDB   AUTO_INCREMENT=6 ;

--
-- Dumping data for table `nalog`
--

INSERT INTO `Nalog` (`ID`, `AUTOBUSKALINIJA`, `DATUMPOLASKA_DAN`, `DATUMPOLASKA_MJESEC`, `DATUMPOLASKA_GODINA`, `VRIJEMEPOLASKA_SATI`, `VRIJEMEPOLASKA_MINUTE`) VALUES
(1, 1, 20, 4, 2015, 12, 0),
(2, 2, 28, 4, 2015, 18, 0),
(3, 3, 31, 4, 2015, 18, 0),
(4, 5, 29, 4, 2015, 19, 0),
(5, 6, 30, 4, 2015, 20, 0);

-- --------------------------------------------------------

--
-- Table structure for table `radnik`
--

CREATE TABLE IF NOT EXISTS `Radnik` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IME` varchar(255) DEFAULT NULL,
  `PREZIME` varchar(255) DEFAULT NULL,
  `JMBG` varchar(255) DEFAULT NULL,
  `TIPRADNOGMJESTA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB   AUTO_INCREMENT=13 ;

--
-- Dumping data for table `radnik`
--

INSERT INTO `Radnik` (`ID`, `IME`, `PREZIME`, `JMBG`, `TIPRADNOGMJESTA`) VALUES
(3, 'neko', 'neko', '0303992110034', 'Administrator'),
(4, 'Fahrudin', 'Brbutovi?', '0303992110034', 'Menadzer'),
(5, 'Huso', 'Efendi?', '0202992110034', 'SalterskiRadnik'),
(6, 'Aida', 'Curi?', '0103991110034', 'SalterskiRadnik'),
(7, 'Osman', 'Huseinovi?', '0409888993322', 'Vozac'),
(8, 'Meho', 'Hasanagi?', '1111002332288', 'Vozac'),
(9, 'Asmir', 'Avduki?', '0903987889966', 'Vozac'),
(10, 'Tarik', 'Radmanovi?', '2304991883344', 'Vozac'),
(11, 'Marko', 'Kraljevi?', '3001985231232', 'Vozac'),
(12, 'Mihajlo', 'Mihojevi?', '2305972334455', 'Vozac');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE IF NOT EXISTS `Rezervacija` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LINIJA` bigint(20) DEFAULT NULL,
  `VRIJEMEPOLASKA_SATI` int(11) DEFAULT NULL,
  `VRIJEMEPOLASKA_MINUTE` int(11) DEFAULT NULL,
  `TIPKARTE` varchar(255) DEFAULT NULL,
  `DATUMPOLASKA_DAN` int(11) DEFAULT NULL,
  `DATUMPOLASKA_MJESEC` int(11) DEFAULT NULL,
  `DATUMPOLASKA_GODINA` int(11) DEFAULT NULL,
  `CIJENA` double DEFAULT NULL,
  `IME` varchar(255) DEFAULT NULL,
  `PREZIME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_evtdmljwiq1a82fvlakbqmcq6` (`LINIJA`)
) ENGINE=InnoDB   AUTO_INCREMENT=6 ;

--
-- Dumping data for table `rezervacija`
--

INSERT INTO `Rezervacija` (`ID`, `LINIJA`, `VRIJEMEPOLASKA_SATI`, `VRIJEMEPOLASKA_MINUTE`, `TIPKARTE`, `DATUMPOLASKA_DAN`, `DATUMPOLASKA_MJESEC`, `DATUMPOLASKA_GODINA`, `CIJENA`, `IME`, `PREZIME`) VALUES
(1, 1, 17, 0, 'dvosmjerna', 24, 5, 2015, 30, 'Haso', 'Husic'),
(2, 1, 17, 0, 'dvosmjerna', 24, 5, 2015, 30, 'Haso', 'Husic'),
(3, 1, 17, 0, 'dvosmjerna', 24, 5, 2015, 30, 'Haso', 'Husic'),
(4, 1, 17, 0, 'dvosmjerna', 24, 5, 2015, 30, 'Meho', 'Mehi?'),
(5, 1, 17, 0, 'dvosmjerna', 24, 5, 2015, 30, 'Meho', 'Mehi?');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `autobuskalinija`
--
ALTER TABLE `AutobuskaLinija`
  ADD CONSTRAINT `FK_49krsit0b1oj279n79rq2rhh6` FOREIGN KEY (`VOZAC`) REFERENCES `radnik` (`ID`),
  ADD CONSTRAINT `FK_6s85fip3v6f0ssgfygehx4myp` FOREIGN KEY (`AUTOBUS`) REFERENCES `autobus` (`ID`);

--
-- Constraints for table `karta`
--
ALTER TABLE `Karta`
  ADD CONSTRAINT `FK_2o637y6nuuy4429flb6k9t16o` FOREIGN KEY (`linija`) REFERENCES `autobuskalinija` (`ID`);

--
-- Constraints for table `korisnickiracun`
--
ALTER TABLE `KorisnickiRacun`
  ADD CONSTRAINT `FK_7w97gmc3yjar1cxat94lblpur` FOREIGN KEY (`radnik`) REFERENCES `radnik` (`ID`);

--
-- Constraints for table `medjunarodnakarta`
--
ALTER TABLE `MedjunarodnaKarta`
  ADD CONSTRAINT `FK_hkqpn8hrcjot38cf64hapjlk2` FOREIGN KEY (`ID`) REFERENCES `karta` (`ID`);

--
-- Constraints for table `nalog`
--
ALTER TABLE `Nalog`
  ADD CONSTRAINT `FK_a84m1ahi7o58sj09my6xjn6d1` FOREIGN KEY (`AUTOBUSKALINIJA`) REFERENCES `autobuskalinija` (`ID`);

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `Rezervacija`
  ADD CONSTRAINT `FK_evtdmljwiq1a82fvlakbqmcq6` FOREIGN KEY (`LINIJA`) REFERENCES `autobuskalinija` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
