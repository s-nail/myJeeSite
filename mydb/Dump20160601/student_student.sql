CREATE DATABASE  IF NOT EXISTS `student` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `student`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: student
-- ------------------------------------------------------
-- Server version	5.6.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` varchar(64) NOT NULL,
  `name` varchar(45) CHARACTER SET latin1 NOT NULL,
  `password` varchar(45) CHARACTER SET latin1 NOT NULL DEFAULT '0',
  `num` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `class` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `age` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `math` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `english` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `chinese` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('1','jia','11','1101','1','22','15',NULL,NULL),('10','f','6','6','1','6',NULL,NULL,NULL),('11','g','7','7','1','7',NULL,NULL,NULL),('12','h','8','8','1','8',NULL,NULL,NULL),('13','i','9','9','1','9',NULL,NULL,NULL),('14','j','10','10','1','10',NULL,NULL,NULL),('17','jim','0','','7','','100',NULL,NULL),('2','tim','1','1111','1','20',NULL,NULL,NULL),('3','fuck','1','11','1','12','15',NULL,NULL),('4','zyf','123','123','1','111','0',NULL,NULL),('5','1','1','1','1','1',NULL,NULL,NULL),('6','c','3','3','1','3',NULL,NULL,NULL),('7','b','2','2','1','2',NULL,NULL,NULL),('8','e','4','4','1','4',NULL,NULL,NULL),('9','d','5','5','1','5',NULL,NULL,NULL),('ea10d47de266410d8494b009ac8d3f3d','TEE','0','12','7','22','100',NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-01 17:08:45
