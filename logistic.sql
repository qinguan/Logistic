-- MySQL dump 10.13  Distrib 5.1.37, for Win32 (ia32)
--
-- Host: localhost    Database: logistic
-- ------------------------------------------------------
-- Server version	5.1.37-community

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activityId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `activityName` varchar(45) NOT NULL,
  `activityPlace` varchar(45) NOT NULL,
  `activatyTime` varchar(45) NOT NULL,
  `activityContent` varchar(45) DEFAULT NULL,
  `activityPersonNum` int(10) unsigned NOT NULL,
  PRIMARY KEY (`activityId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (11,'history','building1','9:00~11:00','actient  china',150),(12,'lecture','building5','14:00~16:00','history',160),(13,'sing3','building2','8:00~10:00','about spring',200),(14,'dance','building3','19:00~20:00','dancing',150),(15,'speech','building4','19:30~21:00','English',180);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charge` (
  `groupId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `workerId` int(10) unsigned NOT NULL,
  `activityId` int(10) unsigned NOT NULL,
  `chargeTime` varchar(45) NOT NULL,
  PRIMARY KEY (`groupId`,`workerId`,`activityId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COMMENT='负责';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

LOCK TABLES `charge` WRITE;
/*!40000 ALTER TABLE `charge` DISABLE KEYS */;
INSERT INTO `charge` VALUES (1,31,12,'8:00~10:00'),(2,32,11,'8:00~10:00'),(3,33,13,'8:00~12:00'),(4,34,14,'10:00~11:00'),(5,35,15,'10:00~11:00');
/*!40000 ALTER TABLE `charge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `code`
--

DROP TABLE IF EXISTS `code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `code` (
  `name` varchar(20) NOT NULL,
  `passwd` varchar(20) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `code`
--

LOCK TABLES `code` WRITE;
/*!40000 ALTER TABLE `code` DISABLE KEYS */;
INSERT INTO `code` VALUES ('liyan','12345'),('qinguan','12345');
/*!40000 ALTER TABLE `code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit`
--

DROP TABLE IF EXISTS `deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deposit` (
  `materialId` int(10) unsigned NOT NULL,
  `stockId` int(10) unsigned NOT NULL,
  `depositNum` int(10) unsigned NOT NULL,
  PRIMARY KEY (`materialId`,`stockId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit`
--

LOCK TABLES `deposit` WRITE;
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
INSERT INTO `deposit` VALUES (401,115,320),(402,111,200),(403,112,190);
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `groupId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `groupName` varchar(45) NOT NULL,
  `groupDuty` varchar(45) DEFAULT NULL,
  `groupOffice` varchar(45) DEFAULT NULL,
  `workTime` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'publicTeam','public','building_1','13:00~17:00'),(2,'supportTeam','support','building_2','9:00~10:00'),(3,'goverTeam','gover','building_3','8:00~10:00'),(4,'supplyTeam','supply','building_4','10:00~12:00'),(5,'driveTeam','drive','building_5','9:00~16:00');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `materialId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `materialName` varchar(45) NOT NULL,
  `materialUsed` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`materialId`)
) ENGINE=InnoDB AUTO_INCREMENT=406 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (401,'bikecicle','traffic'),(402,'cup','drink'),(403,'shoes','ware'),(404,'book','read'),(405,'computer','program');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer` (
  `supplierId` int(10) unsigned NOT NULL,
  `materialId` int(10) unsigned NOT NULL,
  `offerNum` int(10) unsigned NOT NULL,
  PRIMARY KEY (`supplierId`,`materialId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (301,404,200),(302,401,180),(304,403,250),(305,405,400);
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `stockId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `stockPlace` varchar(45) NOT NULL,
  `stockTel` varchar(45) NOT NULL,
  `stockArea` varchar(45) NOT NULL,
  PRIMARY KEY (`stockId`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (111,'beiqu','82330735','100m*100m'),(112,'beiqu','82330733','200m*100m'),(113,'nanqu','82330923','50m*100m'),(114,'nanqu','82337281','40m*70m'),(115,'dongqu','82332321','90m*60m');
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `supplierId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `organization` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `contactTel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`supplierId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=306 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (301,'beihang','xueyuanlu37hao','82330735'),(302,'beihang','7#715','82338976'),(303,'beihang','&#710','82336712'),(304,'keda','7#713','82330987'),(305,'keda','7#714','82338722');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `used`
--

DROP TABLE IF EXISTS `used`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `used` (
  `workerId` int(10) unsigned NOT NULL,
  `materialId` int(10) unsigned NOT NULL,
  `materialUsedNum` int(10) unsigned NOT NULL,
  PRIMARY KEY (`workerId`,`materialId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `used`
--

LOCK TABLES `used` WRITE;
/*!40000 ALTER TABLE `used` DISABLE KEYS */;
INSERT INTO `used` VALUES (31,404,170),(33,402,180),(35,401,200);
/*!40000 ALTER TABLE `used` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `worker` (
  `workerId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `workerNme` varchar(45) NOT NULL,
  `workerTel` varchar(45) NOT NULL,
  `workerSex` varchar(45) NOT NULL,
  PRIMARY KEY (`workerId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (31,'guan','1111333','male'),(32,'li','1111444','female'),(33,'ye','1111555','male'),(34,'qin','1111666','female'),(35,'xu','1111777','male');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-12-13 10:39:39
