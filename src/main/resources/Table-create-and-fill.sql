-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: locations
-- ------------------------------------------------------
-- Server version	5.5.23

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
-- Table structure for table `location_list`
--

DROP TABLE IF EXISTS `location_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_list`
--

LOCK TABLES `location_list` WRITE;
/*!40000 ALTER TABLE `location_list` DISABLE KEYS */;
INSERT INTO `location_list` VALUES (15,-13.751,81.61,12),(16,-14.7,81.6,6),(17,-14.7,81.6,7),(18,-45.1235,1.136,8),(19,-45.1235,1.136,9),(20,-45.1235,1.136,10),(21,22.45562721871866,13.225387587832326,11),(22,3.0501924392417203,7.644781347586067,12),(46,6.50968287314383,17.531390393177144,36),(47,11.730727153804876,19.20771611673615,37),(48,10.196460243934595,5.0216240669560825,38),(49,7.871764995364847,10.021744569449055,39),(51,5.888669779930149,9.671829128459883,41),(52,18.428335089549275,17.022937772700764,42),(54,0.9324735024686142,5.365601435047106,44),(57,18.075279353648106,23.175367088383105,47),(63,19.133348367189384,22.530235312061087,53),(64,23.314442099125333,6.167431036293571,54),(65,14.285801501387663,8.411649715037509,55),(80,14.7,26.3,31),(82,-3.62,0.15,5),(83,1.24,-44.234,2),(84,1.24,0.1,122),(85,0,0,0),(86,1,2,3),(87,0,0,0),(88,-12,25,324),(95,12,13,41),(99,0,0,0),(138,0,0,0),(156,13,14,15),(157,12,14,161);
/*!40000 ALTER TABLE `location_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_types`
--

DROP TABLE IF EXISTS `location_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_types`
--

LOCK TABLES `location_types` WRITE;
/*!40000 ALTER TABLE `location_types` DISABLE KEYS */;
INSERT INTO `location_types` VALUES (3,'Парк',150),(4,'Парк',150),(5,'Парк',150),(6,'Парк',150),(7,'Цирк',50),(8,'Банк',500),(9,'Банк',500),(10,'Банк',500),(11,'Банк',500),(12,'Банк',735),(36,'Банк',613),(37,'Банк',261),(38,'Банк',927),(39,'Банк',412),(41,'Банк',874),(42,'Банк',661),(44,'Банк',348),(47,'Банк',722),(53,'Банк',411),(54,'Банк',383),(55,'Банк',326),(58,'Дверь',250);
/*!40000 ALTER TABLE `location_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operator`
--

DROP TABLE IF EXISTS `operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operator` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Region` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operator`
--

LOCK TABLES `operator` WRITE;
/*!40000 ALTER TABLE `operator` DISABLE KEYS */;
INSERT INTO `operator` VALUES (1,'Beeline','Moscow'),(2,'Megafon','Moscow'),(3,'MTS','Moscow'),(4,'Tele2','Moscow');
/*!40000 ALTER TABLE `operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `location_id_idx` (`location_id`),
  CONSTRAINT `location_id` FOREIGN KEY (`location_id`) REFERENCES `location_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (3,15,4.5,'Good'),(4,16,2.5,'Very bad'),(5,16,4.5,'Good'),(6,17,0,'WTF is dat'),(7,17,4.5,'Good'),(8,17,10,'Excellent'),(9,18,9,'I like it'),(10,18,7,'Wasn\'t expecting like that'),(11,18,1,'Garbage'),(12,19,9,'I like it'),(13,19,7,'Wasn\'t expecting like that'),(14,19,1,'Garbage'),(15,20,5,'I like it'),(16,20,8,'Wasn\'t expecting like that'),(17,20,0,'Garbage'),(18,21,1,'I like it'),(19,21,9,'Wasn\'t expecting like that'),(20,21,2,'Garbage'),(21,22,6,'I like it'),(22,22,6,'Wasn\'t expecting like that'),(23,22,3,'Garbage'),(93,46,0,'1I like it'),(94,46,9,'Wasn\'t expecting like that'),(95,46,6,'Garbage'),(96,47,6,'1I like it'),(97,47,5,'Wasn\'t expecting like that'),(98,47,4,'Garbage'),(99,48,4,'1I like it'),(100,48,4,'Wasn\'t expecting like that'),(101,48,0,'Garbage'),(102,49,2,'1I like it'),(103,49,7,'Wasn\'t expecting like that'),(104,49,9,'Garbage'),(108,51,8,'1I like it'),(109,51,1,'Wasn\'t expecting like that'),(110,51,0,'Garbage'),(111,52,2,'1I like it'),(112,52,2,'Wasn\'t expecting like that'),(113,52,7,'Garbage'),(117,54,8,'1I like it'),(118,54,7,'Wasn\'t expecting like that'),(119,54,5,'Garbage'),(126,57,4,'1I like it'),(127,57,1,'Wasn\'t expecting like that'),(128,57,2,'Garbage'),(144,63,2,'1I like it'),(145,63,0,'Wasn\'t expecting like that'),(146,63,4,'Garbage'),(147,64,0,'1I like it'),(148,64,1,'Wasn\'t expecting like that'),(149,64,1,'Garbage'),(150,65,8,'1I like it'),(151,65,1,'Wasn\'t expecting like that'),(152,65,6,'Garbage');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_review`
--

DROP TABLE IF EXISTS `user_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_review` (
  `user_id` int(11) NOT NULL,
  `review_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`review_id`),
  KEY `review_id` (`review_id`),
  CONSTRAINT `review_id` FOREIGN KEY (`review_id`) REFERENCES `reviews` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_review`
--

LOCK TABLES `user_review` WRITE;
/*!40000 ALTER TABLE `user_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-18 20:37:21
