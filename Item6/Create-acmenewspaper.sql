﻿start transaction;

create database `acmenewspaper`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete
	on `acmenewspaper`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter,
        create temporary tables, lock tables, create view, create routine,
        alter routine, execute, trigger, show view
    on `acmenewspaper`.* to 'acme-manager'@'%';

use `acmenewspaper`;

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: acmenewspaper
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor_addressess`
--

DROP TABLE IF EXISTS `actor_addressess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_addressess` (
  `Actor_id` int(11) NOT NULL,
  `addressess` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_addressess`
--

LOCK TABLES `actor_addressess` WRITE;
/*!40000 ALTER TABLE `actor_addressess` DISABLE KEYS */;
INSERT INTO `actor_addressess` VALUES (1005,'Avda. Reina Mercedes no.5 2C');
/*!40000 ALTER TABLE `actor_addressess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_emailss`
--

DROP TABLE IF EXISTS `actor_emailss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_emailss` (
  `Actor_id` int(11) NOT NULL,
  `emailss` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_emailss`
--

LOCK TABLES `actor_emailss` WRITE;
/*!40000 ALTER TABLE `actor_emailss` DISABLE KEYS */;
INSERT INTO `actor_emailss` VALUES (1005,'pacheco@correo.com');
/*!40000 ALTER TABLE `actor_emailss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_phoness`
--

DROP TABLE IF EXISTS `actor_phoness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_phoness` (
  `Actor_id` int(11) NOT NULL,
  `phoness` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_phoness`
--

LOCK TABLES `actor_phoness` WRITE;
/*!40000 ALTER TABLE `actor_phoness` DISABLE KEYS */;
INSERT INTO `actor_phoness` VALUES (1005,'+34617179563');
/*!40000 ALTER TABLE `actor_phoness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gfgqmtp2f9i5wsojt33xm0uth` (`userAccount_id`),
  KEY `AdminUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_gfgqmtp2f9i5wsojt33xm0uth` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1005,0,'John','Tabares Pacheco',994);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `finalMode` bit(1) DEFAULT NULL,
  `inappropriate` bit(1) DEFAULT NULL,
  `publicationMoment` date DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `newspaper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_41w77a5vfwp4dgryvj07s5tqd` (`newspaper_id`,`publicationMoment`,`inappropriate`,`summary`,`body`,`title`),
  CONSTRAINT `FK_pftm848lf5hu8sd0vghfs605l` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_picturess`
--

DROP TABLE IF EXISTS `article_picturess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_picturess` (
  `Article_id` int(11) NOT NULL,
  `picturess` varchar(255) DEFAULT NULL,
  KEY `FK_dojvlpqxgicr7ljwgjptg2ovn` (`Article_id`),
  CONSTRAINT `FK_dojvlpqxgicr7ljwgjptg2ovn` FOREIGN KEY (`Article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_picturess`
--

LOCK TABLES `article_picturess` WRITE;
/*!40000 ALTER TABLE `article_picturess` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_picturess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creationMoment` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `inappropriate` bit(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_htwa0xf4olgk89qnhe8mt578j` (`user_id`,`inappropriate`,`creationMoment`,`description`,`title`),
  CONSTRAINT `FK_t10lk4j2g8uw7k7et58ytrp70` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  KEY `CustomerUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup`
--

DROP TABLE IF EXISTS `followup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `publicationMoment` date DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_freq297hr07gk5gy5a4auue0q` (`publicationMoment`),
  KEY `FK_aer0q20rslre6418yqlfwmeek` (`article_id`),
  CONSTRAINT `FK_aer0q20rslre6418yqlfwmeek` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup`
--

LOCK TABLES `followup` WRITE;
/*!40000 ALTER TABLE `followup` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followup_picturess`
--

DROP TABLE IF EXISTS `followup_picturess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `followup_picturess` (
  `FollowUp_id` int(11) NOT NULL,
  `picturess` varchar(255) DEFAULT NULL,
  KEY `FK_gay3v6q6el0rv9nwudcfsm3m7` (`FollowUp_id`),
  CONSTRAINT `FK_gay3v6q6el0rv9nwudcfsm3m7` FOREIGN KEY (`FollowUp_id`) REFERENCES `followup` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followup_picturess`
--

LOCK TABLES `followup_picturess` WRITE;
/*!40000 ALTER TABLE `followup_picturess` DISABLE KEYS */;
/*!40000 ALTER TABLE `followup_picturess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newspaper`
--

DROP TABLE IF EXISTS `newspaper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `newspaper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `inappropriate` bit(1) DEFAULT NULL,
  `isPrivate` bit(1) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `publicationDate` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_9d4jmr7swxswfoel8e6qoyi47` (`user_id`,`title`,`description`,`inappropriate`,`publicationDate`,`isPrivate`),
  CONSTRAINT `FK_1vjxhvxnpuoan0f5uyrs4veeg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newspaper`
--

LOCK TABLES `newspaper` WRITE;
/*!40000 ALTER TABLE `newspaper` DISABLE KEYS */;
/*!40000 ALTER TABLE `newspaper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `newspaper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_nc1vf3np9y553nvhx2n6mxd1m` (`newspaper_id`,`customer_id`),
  KEY `FK_2i5w4btb7x3r6d2jsd213aqct` (`customer_id`),
  CONSTRAINT `FK_b3d3q413vlktogdjnnus3ep9e` FOREIGN KEY (`newspaper_id`) REFERENCES `newspaper` (`id`),
  CONSTRAINT `FK_2i5w4btb7x3r6d2jsd213aqct` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfig`
--

DROP TABLE IF EXISTS `systemconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfig` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfig`
--

LOCK TABLES `systemconfig` WRITE;
/*!40000 ALTER TABLE `systemconfig` DISABLE KEYS */;
INSERT INTO `systemconfig` VALUES (1029,0);
/*!40000 ALTER TABLE `systemconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfig_taboowordss`
--

DROP TABLE IF EXISTS `systemconfig_taboowordss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfig_taboowordss` (
  `SystemConfig_id` int(11) NOT NULL,
  `tabooWordss` varchar(255) DEFAULT NULL,
  KEY `FK_h9owbp8lhtwicbvkjnex7qpu1` (`SystemConfig_id`),
  CONSTRAINT `FK_h9owbp8lhtwicbvkjnex7qpu1` FOREIGN KEY (`SystemConfig_id`) REFERENCES `systemconfig` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfig_taboowordss`
--

LOCK TABLES `systemconfig_taboowordss` WRITE;
/*!40000 ALTER TABLE `systemconfig_taboowordss` DISABLE KEYS */;
INSERT INTO `systemconfig_taboowordss` VALUES (1029,'sex'),(1029,'sexo'),(1029,'viagra'),(1029,'cialis');
/*!40000 ALTER TABLE `systemconfig_taboowordss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surnames` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  KEY `UserUK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_user` (
  `follows_id` int(11) NOT NULL,
  `followedBy_id` int(11) NOT NULL,
  KEY `FK_qlqckc2up307vwrb3kqju18fl` (`followedBy_id`),
  KEY `FK_p9wcdbf2yc1blf0qgv5tm4ji6` (`follows_id`),
  CONSTRAINT `FK_p9wcdbf2yc1blf0qgv5tm4ji6` FOREIGN KEY (`follows_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_qlqckc2up307vwrb3kqju18fl` FOREIGN KEY (`followedBy_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user`
--

LOCK TABLES `user_user` WRITE;
/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (994,0,'21232f297a57a5a743894a0e4a801fc3','admin');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (994,'ADMIN');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-12 21:29:58
