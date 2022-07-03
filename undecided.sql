-- MySQL dump 10.19  Distrib 10.3.34-MariaDB, for debian-linux-gnu (aarch64)
--
-- Host: localhost    Database: undecided_demo
-- ------------------------------------------------------
-- Server version	10.3.34-MariaDB-0+deb10u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Authentication`
--

DROP TABLE IF EXISTS `Authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Authentication` (
  `session` varchar(255) NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`session`),
  KEY `FK6eql7w1otb19lycn9bfx5m5h0` (`userid`),
  CONSTRAINT `FK6eql7w1otb19lycn9bfx5m5h0` FOREIGN KEY (`userid`) REFERENCES `User` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `commentid` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `ratingID` varchar(255) DEFAULT NULL,
  `userID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`commentid`),
  KEY `FKsv7m7t10g3sg88kjw76m2p8wj` (`ratingID`),
  KEY `FKep7bh7ce5rqwe6vq1bwtn8o98` (`userID`),
  CONSTRAINT `FKep7bh7ce5rqwe6vq1bwtn8o98` FOREIGN KEY (`userID`) REFERENCES `User` (`userid`),
  CONSTRAINT `FKsv7m7t10g3sg88kjw76m2p8wj` FOREIGN KEY (`ratingID`) REFERENCES `Rating` (`ratingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Following`
--

DROP TABLE IF EXISTS `Following`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Following` (
  `followingID` varchar(255) NOT NULL,
  `userID` varchar(255) NOT NULL,
  `followDate` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`followingID`,`userID`),
  KEY `FKq2b4t3evoxe7fflp8yff37h2a` (`userID`),
  CONSTRAINT `FK1yra878mgr28dlv72dfdnh7qu` FOREIGN KEY (`followingID`) REFERENCES `User` (`userid`),
  CONSTRAINT `FKq2b4t3evoxe7fflp8yff37h2a` FOREIGN KEY (`userID`) REFERENCES `User` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `locationid` varchar(255) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `houseNumber` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`locationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `productid` varchar(255) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `labelList` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `officialImage` varchar(255) DEFAULT NULL,
  `verified` bit(1) NOT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Product_Type`
--

DROP TABLE IF EXISTS `Product_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_Type` (
  `Product_productid` varchar(255) NOT NULL,
  `types_label` varchar(255) NOT NULL,
  UNIQUE KEY `UK_m6wxkm9kdw5sijl2egyluhi5e` (`types_label`),
  KEY `FK4dpbced24sfbmnu4nsn9cuu9d` (`Product_productid`),
  CONSTRAINT `FK4dpbced24sfbmnu4nsn9cuu9d` FOREIGN KEY (`Product_productid`) REFERENCES `Product` (`productid`),
  CONSTRAINT `FKsmlqm8mopqoo8euh3xicgj1ie` FOREIGN KEY (`types_label`) REFERENCES `Type` (`label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rating`
--

DROP TABLE IF EXISTS `Rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rating` (
  `ratingid` varchar(255) NOT NULL,
  `commentNum` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `imageNum` int(11) DEFAULT NULL,
  `labels` varchar(255) DEFAULT NULL,
  `stars` double DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `voteNum` int(11) DEFAULT NULL,
  `locationid` varchar(255) DEFAULT NULL,
  `productid` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ratingid`),
  KEY `FKas1lc01ed86us1uabrndth82j` (`locationid`),
  KEY `FK8nd2js65o3j4mw3hilgg8d8mg` (`productid`),
  KEY `FKoq4j9hree1oxxyuvcpysthi5d` (`userid`),
  CONSTRAINT `FK8nd2js65o3j4mw3hilgg8d8mg` FOREIGN KEY (`productid`) REFERENCES `Product` (`productid`),
  CONSTRAINT `FKas1lc01ed86us1uabrndth82j` FOREIGN KEY (`locationid`) REFERENCES `Location` (`locationid`),
  CONSTRAINT `FKoq4j9hree1oxxyuvcpysthi5d` FOREIGN KEY (`userid`) REFERENCES `User` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rating_Type`
--

DROP TABLE IF EXISTS `Rating_Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rating_Type` (
  `Rating_ratingid` varchar(255) NOT NULL,
  `types_label` varchar(255) NOT NULL,
  UNIQUE KEY `UK_giwj06axcrcqc20ma5lv9dokd` (`types_label`),
  KEY `FKghv8xt6yfyp1utrj6b3nrr8rv` (`Rating_ratingid`),
  CONSTRAINT `FK3m5rsoeov0en9enxym1fgixri` FOREIGN KEY (`types_label`) REFERENCES `Type` (`label`),
  CONSTRAINT `FKghv8xt6yfyp1utrj6b3nrr8rv` FOREIGN KEY (`Rating_ratingid`) REFERENCES `Rating` (`ratingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Rating_User`
--

DROP TABLE IF EXISTS `Rating_User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rating_User` (
  `Rating_ratingid` varchar(255) NOT NULL,
  `friends_userid` varchar(255) NOT NULL,
  KEY `FKnb30mkxyjlt29ptew0vuyvq2h` (`friends_userid`),
  KEY `FKcgifio0st6qtp2w564osr0ln` (`Rating_ratingid`),
  CONSTRAINT `FKcgifio0st6qtp2w564osr0ln` FOREIGN KEY (`Rating_ratingid`) REFERENCES `Rating` (`ratingid`),
  CONSTRAINT `FKnb30mkxyjlt29ptew0vuyvq2h` FOREIGN KEY (`friends_userid`) REFERENCES `User` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Type`
--

DROP TABLE IF EXISTS `Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Type` (
  `label` varchar(255) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`label`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `userid` varchar(255) NOT NULL,
  `bannerImage` int(11) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `followerNum` int(11) DEFAULT NULL,
  `followingNum` int(11) DEFAULT NULL,
  `isDarkTheme` bit(1) NOT NULL,
  `language` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profileImage` varchar(255) DEFAULT NULL,
  `ratingsNum` int(11) DEFAULT NULL,
  `registerDate` datetime DEFAULT current_timestamp(),
  `username` varchar(255) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL,
  `verified` bit(1) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Vote`
--

DROP TABLE IF EXISTS `Vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vote` (
  `ratingID` varchar(255) NOT NULL,
  `userID` varchar(255) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`ratingID`,`userID`),
  KEY `FKhgqvfug7klic53ahfo73n13to` (`userID`),
  CONSTRAINT `FKe551ukdlxxe7y02mf5cmdjl9g` FOREIGN KEY (`ratingID`) REFERENCES `Rating` (`ratingid`),
  CONSTRAINT `FKhgqvfug7klic53ahfo73n13to` FOREIGN KEY (`userID`) REFERENCES `User` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-03 19:11:12
