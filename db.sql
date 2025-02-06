CREATE DATABASE  IF NOT EXISTS `letsplay_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `letsplay_db`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: letsplay_db
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerEmail` varchar(45) NOT NULL,
  `team` varchar(45) NOT NULL,
  `numPlayers` int NOT NULL,
  `captain` varchar(45) NOT NULL,
  `managerEmail` varchar(45) NOT NULL,
  `status` enum('Accepted','Pending','Rejected') NOT NULL DEFAULT 'Pending',
  `message` varchar(90) DEFAULT NULL,
  `tournament` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_registration_idx` (`customerEmail`),
  CONSTRAINT `user_registration` FOREIGN KEY (`customerEmail`) REFERENCES `user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (41,'mario@ros','inter',11,'zanetti','lionel@messi','Pending',NULL,'Tornei Roma'),(42,'mario@ros','fiorentina',13,'mario','luca@libero.it','Accepted','Richiesta accettata','Coppa Campioni');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tournament` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `footballFacility` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `participationFee` int NOT NULL,
  `numberTeams` int NOT NULL,
  `prize` varchar(45) NOT NULL,
  `requirements` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `type` enum('Calcio a 5','Calcio a 8','Calcio a 11') NOT NULL,
  `managerEmail` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tournament_manager_idx` (`managerEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament`
--

LOCK TABLES `tournament` WRITE;
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
INSERT INTO `tournament` VALUES (1,'Torneo Capitale','Olimpico','Viale dei Gladiatori, 2','2025-02-10','2025-04-17',15,8,'1000€','18 anni minimo','Roma','Calcio a 5','fabio@gmail.com'),(2,'Tornei Roma','Cisco','Via dei Gordiani, 193','2025-02-21','2025-03-21',20,16,'Viaggio europa','40 anni minimo','Roma','Calcio a 8','lionel@messi'),(3,'Coppa Campioni','Arena capitolina','Via Giuseppe Garibaldi, 13','2025-03-11','2025-06-11',30,32,'Viaggio america','30 anni minimo','Firenze','Calcio a 11','luca@libero.it'),(4,'Dribbling Cup','Imperial Pitch','Via Roma, 77','2025-04-02','2025-05-31',50,16,'Buoni sconto per shopping in negozi fitness','Nessun vincolo di partecipazione','Napoli','Calcio a 5','fabio@gmail.com'),(5,'Milan Challenge','San Siro','Piazzale Angelo Moratti','2025-06-01','2025-12-20',35,32,'5000€','18 anni minimo','Milano','Calcio a 8','lionel@messi'),(6,'Rome Challenge','Gladiatori','Viale Palmiro Togliatti, 1999','2025-05-05','2025-06-06',40,16,'Viaggio sud america','25 anni minimo','Roma','Calcio a 11','luca@libero.it');
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` enum('Customer','Manager') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Email_UNIQUE` (`email`),
  UNIQUE KEY `Username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'fabio','iacus','fab','fabio@gmail.com','pass','Manager'),(2,'enzo','ferrari','enzfer','charles@leclerc','rosso','Customer'),(3,'lionel','messi','lm10','lionel@messi','barcellona','Manager'),(4,'mario','rossi','mrossi','mario@ros','mpass','Customer'),(5,'luca','verdi','lucav','luca@libero.it','luke','Manager'),(6,'leonardo','bianchi','leobianchi','leo@gmail.com','white','Customer');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-06 19:28:12
