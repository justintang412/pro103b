-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: archive
-- ------------------------------------------------------
-- Server version	5.5.5-10.7.3-MariaDB-1:10.7.3+maria~focal

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
-- Table structure for table `log_captcha`
--

DROP TABLE IF EXISTS `log_captcha`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_captcha` (
  `t` varchar(100) NOT NULL,
  `captcha` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`t`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_captcha`
--

LOCK TABLES `log_captcha` WRITE;
/*!40000 ALTER TABLE `log_captcha` DISABLE KEYS */;
INSERT INTO `log_captcha` VALUES ('1649576078954','w5dr8'),('1649576092447','dbefp'),('1649576167660','a2d24'),('1649576174387','88kba'),('1649577032034','chbww'),('1649577040469','p43hg'),('1649577048724','w37dg'),('1649590798858','hehwx'),('1649590875836','8php8'),('1649590892161','wwaeg'),('1649590997607','rykpw'),('1649591450323','5d33x'),('1649591480471','yeb7m'),('1649591943458','wfkwy'),('1649591952471','rpdr4'),('1649591953517','pmkan'),('1649591957742','k3gw2'),('1649591966624','nkg2w'),('1649592037556','cxrb2'),('1649592078018','re3wp'),('1649592201123','fmfg4'),('1649592244289','y7bfg'),('1649592590897','6apmp'),('1649592609960','kww6y'),('1649592664673','fkd7b'),('1649592679396','hb82m'),('1649593239107','4bg35'),('1649593342369','348er'),('1649593388885','p7ed6'),('1649593963714','5kycp'),('1649593975648','6xk2x'),('1649594025932','e3y8p'),('1649594026216','an3rr'),('1649594227321','ge868'),('1649594249950','y7445'),('1649594265908','wxmey'),('1649594560723','24hrm'),('1649594587468','8ahrb'),('1649594721731','wb6e3'),('1649594943995','harc6'),('1649594964525','exbhp'),('1649631719231','823nc'),('1649631734776','2hb4e'),('1649631771998','6yepw'),('1649631864938','4rae3'),('1649631880708','r2e7d'),('1649631889200','x233y'),('1649632110470','kc36r'),('1649635991887','w72wy'),('1649636153502','3ny3k'),('1649636164378','dhgxe'),('1649636243523','mgncm'),('1649636352381','pph42'),('1650299545147','5w5f2'),('1651591871166','hcawr');
/*!40000 ALTER TABLE `log_captcha` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_right_role`
--

DROP TABLE IF EXISTS `m_right_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `m_right_role` (
  `role_id` varchar(100) NOT NULL,
  `right_id` varchar(100) NOT NULL,
  PRIMARY KEY (`role_id`,`right_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_right_role`
--

LOCK TABLES `m_right_role` WRITE;
/*!40000 ALTER TABLE `m_right_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `m_right_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_archive_group`
--

DROP TABLE IF EXISTS `t_archive_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_archive_group` (
  `archive_no` varchar(100) NOT NULL,
  `archive_name` varchar(100) DEFAULT NULL,
  `department_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`archive_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='全宗';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_archive_group`
--

LOCK TABLES `t_archive_group` WRITE;
/*!40000 ALTER TABLE `t_archive_group` DISABLE KEYS */;
INSERT INTO `t_archive_group` VALUES ('全宗-001','阜阳档案管理局','1');
/*!40000 ALTER TABLE `t_archive_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_codes`
--

DROP TABLE IF EXISTS `t_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_codes` (
  `code_id` varchar(100) NOT NULL,
  `code_name` varchar(100) DEFAULT NULL,
  `code_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_codes`
--

LOCK TABLES `t_codes` WRITE;
/*!40000 ALTER TABLE `t_codes` DISABLE KEYS */;
INSERT INTO `t_codes` VALUES ('secret_level_1','秘密','secret_level'),('secret_level_2','机密','secret_level'),('secret_level_3','绝密','secret_level'),('urgent_level_1','一般','urgent_level'),('urgent_level_2','紧急','urgent_level');
/*!40000 ALTER TABLE `t_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_department`
--

DROP TABLE IF EXISTS `t_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_department` (
  `department_id` varchar(100) NOT NULL,
  `department_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_department`
--

LOCK TABLES `t_department` WRITE;
/*!40000 ALTER TABLE `t_department` DISABLE KEYS */;
INSERT INTO `t_department` VALUES ('1','阜阳档案管理局');
/*!40000 ALTER TABLE `t_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_file`
--

DROP TABLE IF EXISTS `t_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_file` (
  `file_no` varchar(100) NOT NULL COMMENT '室编档号',
  `department_file_no` varchar(100) DEFAULT NULL COMMENT '部门档号',
  `global_file_no` varchar(100) DEFAULT NULL COMMENT '馆编档号',
  `file_title` varchar(100) DEFAULT NULL COMMENT '题名',
  `alternative_title` varchar(100) DEFAULT NULL COMMENT '并列题名',
  `series_no` varchar(100) DEFAULT NULL COMMENT '类目号',
  `file_year` varchar(100) DEFAULT NULL COMMENT '年度',
  `refer_global_file_no` varchar(100) DEFAULT NULL COMMENT '参见号',
  `valid_years` int(11) DEFAULT NULL COMMENT '保管期限，年，0=永久',
  `pages` int(11) DEFAULT NULL COMMENT '页数',
  `security_classification` varchar(100) DEFAULT NULL COMMENT '密级',
  `department_id` varchar(100) DEFAULT NULL COMMENT '所属部门',
  `archive_value` varchar(100) DEFAULT NULL COMMENT '档案价值',
  `store_location` varchar(100) DEFAULT NULL COMMENT '存储位置',
  `archived_by` varchar(100) DEFAULT NULL COMMENT '归档人',
  `date_archived` varchar(100) DEFAULT NULL COMMENT '归档日期',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`file_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卷';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_file`
--

LOCK TABLES `t_file` WRITE;
/*!40000 ALTER TABLE `t_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_item`
--

DROP TABLE IF EXISTS `t_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_item` (
  `item_id` varchar(100) NOT NULL COMMENT 'UUID',
  `file_no` varchar(100) DEFAULT NULL COMMENT '卷号',
  `item_no` varchar(100) DEFAULT NULL COMMENT '件号',
  `item_date` varchar(100) DEFAULT NULL COMMENT '件日期',
  `item_size` varchar(100) DEFAULT NULL COMMENT '电子文件大小',
  `item_full_name` varchar(100) DEFAULT NULL COMMENT '电子全文名称',
  `item_format` varchar(100) DEFAULT NULL COMMENT '电子文件格式',
  `item_version` varchar(100) DEFAULT NULL COMMENT '版本号',
  `e_item_no` varchar(100) DEFAULT NULL COMMENT '室编电子文档号',
  `department_e_item_no` varchar(100) DEFAULT NULL COMMENT '部门电子文档号',
  `global_e_item_no` varchar(100) DEFAULT NULL COMMENT '馆编电子文档号',
  `key_words` varchar(100) DEFAULT NULL COMMENT '关键词',
  `pages` varchar(100) DEFAULT NULL COMMENT '页数',
  `pysical_file_id` varchar(100) DEFAULT NULL COMMENT '文件UUID',
  `receiver` varchar(100) DEFAULT NULL COMMENT '接收人',
  `receive_date` varchar(100) DEFAULT NULL COMMENT '接收日期',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_item`
--

LOCK TABLES `t_item` WRITE;
/*!40000 ALTER TABLE `t_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_rights`
--

DROP TABLE IF EXISTS `t_rights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_rights` (
  `right_id` varchar(100) NOT NULL,
  `right_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`right_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_rights`
--

LOCK TABLES `t_rights` WRITE;
/*!40000 ALTER TABLE `t_rights` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_rights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `role_id` varchar(100) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES ('admin','admin'),('operator','operator'),('sysadmin','sysadmin'),('user','user');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_series`
--

DROP TABLE IF EXISTS `t_series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_series` (
  `series_no` varchar(100) NOT NULL COMMENT '目录号',
  `series_name` varchar(100) DEFAULT NULL COMMENT '类目名称',
  `archive_no` varchar(100) DEFAULT NULL COMMENT '全宗号',
  `file_type` varchar(100) DEFAULT NULL COMMENT 'item, file, project,不立卷，案卷，项目',
  `department_id` varchar(100) DEFAULT NULL COMMENT '如果不是按照部门，此字段是NULL',
  `sort_no` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`series_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='目录（全宗下面的树形结构）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_series`
--

LOCK TABLES `t_series` WRITE;
/*!40000 ALTER TABLE `t_series` DISABLE KEYS */;
INSERT INTO `t_series` VALUES ('1','会计档案',NULL,NULL,NULL,NULL),('2','会计凭证',NULL,NULL,NULL,NULL),('3','会计账簿',NULL,NULL,NULL,NULL),('4','财务报告',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `is_admin` varchar(100) DEFAULT NULL,
  `role_id` varchar(100) DEFAULT NULL,
  `is_active` varchar(100) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `expire` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('admin','admin','true','sysadmin','true','admin:cad58e68-a4aa-47d1-bce2-1c0f06eb1c24',1651635083393);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'archive'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06  0:12:03
