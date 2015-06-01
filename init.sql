-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 24, 2015 at 12:51 PM
-- Server version: 5.5.43-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Tim3`
--

-- --------------------------------------------------------

CREATE DATABASE `Tim3` DEFAULT CHARSET=utf8 COLLATE=utf8_slovenian_ci;
USE `Tim3`;

-- --------------------------------------------------------

CREATE USER 'EtfSI2014'@'localhost' IDENTIFIED BY '2014SIEtf';

GRANT ALL ON `Tim3`.* TO `EtfSI2014`;


-- --------------------------------------------------------

--
-- Table structure for table `Autobus`
--

CREATE TABLE IF NOT EXISTS `Autobus` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODEL` varchar(255) DEFAULT NULL,
  `KAPACITET` int(11) DEFAULT NULL,
  `REGISTRACIJA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `AutobuskaLinija`
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
) ENGINE=InnoDB  AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Karta`
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
) ENGINE=InnoDB  AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `KorisnickiRacun`
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
-- Dumping data for table `KorisnickiRacun`
--

INSERT INTO `KorisnickiRacun` (`ID`, `KORISNICKOIME`, `SIFRA`, `radnik`, `TIPKORISNICKOGRACUNA`, `ONLINE`) VALUES
(4, 'admin', 'admin123!', 3, 'administrator', 0);

-- --------------------------------------------------------

--
-- Table structure for table `MedjunarodnaKarta`
--

CREATE TABLE IF NOT EXISTS `MedjunarodnaKarta` (
  `ID` bigint(20) NOT NULL,
  `IME` varchar(255) DEFAULT NULL,
  `PREZIME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB ;

-- --------------------------------------------------------

--
-- Table structure for table `Nalog`
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
) ENGINE=InnoDB  AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `Radnik`
--

CREATE TABLE IF NOT EXISTS `Radnik` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IME` varchar(255) DEFAULT NULL,
  `PREZIME` varchar(255) DEFAULT NULL,
  `JMBG` varchar(255) DEFAULT NULL,
  `TIPRADNOGMJESTA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB   AUTO_INCREMENT=4 ;

--
-- Dumping data for table `Radnik`
--

INSERT INTO `Radnik` (`ID`, `IME`, `PREZIME`, `JMBG`, `TIPRADNOGMJESTA`) VALUES
(3, 'neko', 'neko', '0303992110034', 'Administrator');

-- --------------------------------------------------------

--
-- Table structure for table `Rezervacija`
--

CREATE TABLE IF NOT EXISTS `rezervacija` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LINIJA` bigint(20) DEFAULT NULL,
  `VRIJEMEREZERVACIJE_SATI` int(11) DEFAULT NULL,
  `VRIJEMEREZERVACIJE_MINUTE` int(11) DEFAULT NULL,
  `TIPKARTE` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `DATUMREZERVACIJE_DAN` int(11) DEFAULT NULL,
  `DATUMREZERVACIJE_MJESEC` int(11) DEFAULT NULL,
  `DATUMREZERVACIJE_GODINA` int(11) DEFAULT NULL,
  `CIJENA` double DEFAULT NULL,
  `IME` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  `PREZIME` varchar(255) COLLATE utf8_slovenian_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_evtdmljwiq1a82fvlakbqmcq6` (`LINIJA`)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `AutobuskaLinija`
--
ALTER TABLE `AutobuskaLinija`
  ADD CONSTRAINT `FK_49krsit0b1oj279n79rq2rhh6` FOREIGN KEY (`VOZAC`) REFERENCES `Radnik` (`ID`),
  ADD CONSTRAINT `FK_6s85fip3v6f0ssgfygehx4myp` FOREIGN KEY (`AUTOBUS`) REFERENCES `Autobus` (`ID`);

--
-- Constraints for table `Karta`
--
ALTER TABLE `Karta`
  ADD CONSTRAINT `FK_2o637y6nuuy4429flb6k9t16o` FOREIGN KEY (`linija`) REFERENCES `AutobuskaLinija` (`ID`);

--
-- Constraints for table `KorisnickiRacun`
--
ALTER TABLE `KorisnickiRacun`
  ADD CONSTRAINT `FK_7w97gmc3yjar1cxat94lblpur` FOREIGN KEY (`radnik`) REFERENCES `Radnik` (`ID`);

--
-- Constraints for table `MedjunarodnaKarta`
--
ALTER TABLE `MedjunarodnaKarta`
  ADD CONSTRAINT `FK_hkqpn8hrcjot38cf64hapjlk2` FOREIGN KEY (`ID`) REFERENCES `Karta` (`ID`);

--
-- Constraints for table `Nalog`
--
ALTER TABLE `Nalog`
  ADD CONSTRAINT `FK_a84m1ahi7o58sj09my6xjn6d1` FOREIGN KEY (`AUTOBUSKALINIJA`) REFERENCES `AutobuskaLinija` (`ID`);

--
-- Constraints for table `Rezervacija`
--
ALTER TABLE `Rezervacija`
  ADD CONSTRAINT `FK_evtdmljwiq1a82fvlakbqmcq6` FOREIGN KEY (`LINIJA`) REFERENCES `AutobuskaLinija` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
