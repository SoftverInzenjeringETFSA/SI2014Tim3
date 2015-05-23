-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 23, 2015 at 11:03 AM
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

CREATE DATABASE `tim3`;
USE `tim3`;

-- --------------------------------------------------------

CREATE USER 'EtfSI2014'@'localhost' IDENTIFIED BY '2014SIEtf';

GRANT ALL ON `Tim3`.* TO 'EtfSI2014';


--
-- Table structure for table `autobus`
--

CREATE TABLE IF NOT EXISTS `autobus` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MODEL` varchar(255) DEFAULT NULL,
  `KAPACITET` int(11) DEFAULT NULL,
  `REGISTRACIJA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `autobuskalinija`
--

CREATE TABLE IF NOT EXISTS `autobuskalinija` (
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
  KEY `FK_j38341cnju2t1qvypfrp8sm3c` (`AUTOBUS`),
  KEY `FK_lp207a8l8k8sf1ttvjnl63xuy` (`VOZAC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `karta`
--

CREATE TABLE IF NOT EXISTS `karta` (
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
  KEY `FK_nb4vtpx7e9tr2a9mivsjue6mg` (`linija`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `korisnickiracun`
--

CREATE TABLE IF NOT EXISTS `korisnickiracun` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `KORISNICKOIME` varchar(255) DEFAULT NULL,
  `SIFRA` varchar(255) DEFAULT NULL,
  `radnik` bigint(20) DEFAULT NULL,
  `TIPKORISNICKOGRACUNA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_qppepa8vt2jotww7d6fhv901f` (`radnik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `medjunarodnakarta`
--

CREATE TABLE IF NOT EXISTS `medjunarodnakarta` (
  `ID` bigint(20) NOT NULL,
  `IME` varchar(255) DEFAULT NULL,
  `PREZIME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nalog`
--

CREATE TABLE IF NOT EXISTS `nalog` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `AUTOBUSKALINIJA` bigint(20) DEFAULT NULL,
  `DATUMPOLASKA_DAN` int(11) DEFAULT NULL,
  `DATUMPOLASKA_MJESEC` int(11) DEFAULT NULL,
  `DATUMPOLASKA_GODINA` int(11) DEFAULT NULL,
  `VRIJEMEPOLASKA_SATI` int(11) DEFAULT NULL,
  `VRIJEMEPOLASKA_MINUTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_j8mkp94ni32pk177iw6t1jwfk` (`AUTOBUSKALINIJA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `radnik`
--

CREATE TABLE IF NOT EXISTS `radnik` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IME` varchar(255) DEFAULT NULL,
  `PREZIME` varchar(255) DEFAULT NULL,
  `JMBG` varchar(255) DEFAULT NULL,
  `TIPRADNOGMJESTA` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE IF NOT EXISTS `rezervacija` (
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
  KEY `FK_tr1si9pqlsit85x4n5gw6i2ng` (`LINIJA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `autobuskalinija`
--
ALTER TABLE `autobuskalinija`
  ADD CONSTRAINT `FK_lp207a8l8k8sf1ttvjnl63xuy` FOREIGN KEY (`VOZAC`) REFERENCES `radnik` (`ID`),
  ADD CONSTRAINT `FK_j38341cnju2t1qvypfrp8sm3c` FOREIGN KEY (`AUTOBUS`) REFERENCES `autobus` (`ID`);

--
-- Constraints for table `karta`
--
ALTER TABLE `karta`
  ADD CONSTRAINT `FK_nb4vtpx7e9tr2a9mivsjue6mg` FOREIGN KEY (`linija`) REFERENCES `autobuskalinija` (`ID`);

--
-- Constraints for table `korisnickiracun`
--
ALTER TABLE `korisnickiracun`
  ADD CONSTRAINT `FK_qppepa8vt2jotww7d6fhv901f` FOREIGN KEY (`radnik`) REFERENCES `radnik` (`ID`);

--
-- Constraints for table `medjunarodnakarta`
--
ALTER TABLE `medjunarodnakarta`
  ADD CONSTRAINT `FK_lpxiueoqxkv3yni922a3rxjif` FOREIGN KEY (`ID`) REFERENCES `karta` (`ID`);

--
-- Constraints for table `nalog`
--
ALTER TABLE `nalog`
  ADD CONSTRAINT `FK_j8mkp94ni32pk177iw6t1jwfk` FOREIGN KEY (`AUTOBUSKALINIJA`) REFERENCES `autobuskalinija` (`ID`);

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `FK_tr1si9pqlsit85x4n5gw6i2ng` FOREIGN KEY (`LINIJA`) REFERENCES `autobuskalinija` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
