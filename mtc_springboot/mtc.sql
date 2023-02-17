-- MySQL dump 10.13  Distrib 8.0.30, for Linux (x86_64)
--
-- Host: localhost    Database: mtc
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_all_user`
--

DROP TABLE IF EXISTS `t_all_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_all_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_email` varchar(32) NOT NULL,
  `user_portrait` varchar(100) DEFAULT NULL,
  `user_sex` char(1) DEFAULT NULL,
  `user_password` varchar(20) DEFAULT '123456',
  `user_bloodtype` char(2) DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_height` int DEFAULT NULL,
  `user_type` char(2) DEFAULT '00',
  `user_permission` varchar(20) DEFAULT 'user',
  `user_keyword` varchar(100) DEFAULT '糖糖用户',
  `user_state` int DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_email` (`user_email`),
  KEY `t_all_user_email_index` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_all_user`
--

LOCK TABLES `t_all_user` WRITE;
/*!40000 ALTER TABLE `t_all_user` DISABLE KEYS */;
INSERT INTO `t_all_user` VALUES (1,'jckeep','2407018371@qq.comm','http://114.132.226.110/file/bear2.jpg','男','123456Abc.','A型','2013-10-29',100,'00','user','糖糖用户',1),(5,'JCKeep','2407018371@qq.com','http://114.132.226.110/file/bear2.jpg','男','123456Abc.','A型','2013-10-29',170,'00','user','金牌糖友,Taylor,999,糖糖用户',1),(7,'猪猪侠','1875754352@qq.com','http://114.132.226.110/file/bear2.jpg','女','lwt123456+++','O型','1975-09-01',180,'00','user','金牌糖友,Taylor,YYDS,999,糖糖用户',1),(13,'Tiramisu','3109689250@qq.com','http://114.132.226.110/file/bear2.jpg',NULL,'3109689250@qq.com',NULL,NULL,NULL,'00','user','',1),(36,'hrn','1318293185@qq.com','http://114.132.226.110/file/bear2.jpg','女','1318293185@qq.com','A型','2002-06-28',162,'10','user','Taylor,hhh,加油抗糖,糖糖用户',1),(37,'MK','1278129485@qq.com','http://114.132.226.110/file/bear2.jpg',NULL,'fqy123$',NULL,'2023-02-11',NULL,'01','user','糖糖用户',1),(39,'糖糖','769721625@qq.com','https://img1.baidu.com/it/u=425754087,984711911&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',NULL,'769721625@qq.com',NULL,'2023-02-12',NULL,'01','user','金牌糖友,YYDS,糖糖用户',1);
/*!40000 ALTER TABLE `t_all_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_background_administrator`
--

DROP TABLE IF EXISTS `t_background_administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_background_administrator` (
  `admin_id` bigint NOT NULL AUTO_INCREMENT,
  `admin_password` varchar(20) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_background_administrator`
--

LOCK TABLES `t_background_administrator` WRITE;
/*!40000 ALTER TABLE `t_background_administrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_background_administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_chat`
--

DROP TABLE IF EXISTS `t_community_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_chat` (
  `chat_id` bigint NOT NULL AUTO_INCREMENT,
  `relation_id` bigint NOT NULL,
  `chat_time` datetime NOT NULL,
  `chat_content` text NOT NULL,
  `chat_from` char(2) NOT NULL,
  PRIMARY KEY (`chat_id`),
  KEY `FK_fk_relation_chat` (`relation_id`),
  CONSTRAINT `FK_fk_relation_chat` FOREIGN KEY (`relation_id`) REFERENCES `t_community_relation` (`relation_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_chat`
--

LOCK TABLES `t_community_chat` WRITE;
/*!40000 ALTER TABLE `t_community_chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_community_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_doctorverify`
--

DROP TABLE IF EXISTS `t_community_doctorverify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_doctorverify` (
  `doctor_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `doctor_idnumber` char(18) NOT NULL,
  `doctor_hospital` varchar(20) NOT NULL,
  `doctor_qualification` varchar(100) NOT NULL,
  `verify_state` varchar(10) NOT NULL,
  `admin_id` bigint NOT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `FK_fk_admin_doctorverigy` (`admin_id`),
  KEY `FK_fk_user_doctorverify` (`user_id`),
  CONSTRAINT `FK_fk_user_doctorverify` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_doctorverify`
--

LOCK TABLES `t_community_doctorverify` WRITE;
/*!40000 ALTER TABLE `t_community_doctorverify` DISABLE KEYS */;
INSERT INTO `t_community_doctorverify` VALUES (13,36,'152620123412231234','u费用TV几乎不可见那就哦','http://114.132.226.110/file/{$]F~0[GO8ITJL{9UG9GTBR.jpg','00',1),(14,37,'100000','哈哈','http://114.132.226.110/file/火车.jpeg','00',1),(17,39,'152621200112207635','湖南大学校医院','http://114.132.226.110/file/6fcd328ab07229899990819bb06f85d3.jpg','00',1);
/*!40000 ALTER TABLE `t_community_doctorverify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_drug`
--

DROP TABLE IF EXISTS `t_community_drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_drug` (
  `drug_id` bigint NOT NULL AUTO_INCREMENT,
  `drug_name` varchar(30) NOT NULL,
  `drug_image` varchar(100) DEFAULT NULL,
  `drug_detail` text,
  PRIMARY KEY (`drug_id`),
  KEY `t_coummunity_drug_name_index` (`drug_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_drug`
--

LOCK TABLES `t_community_drug` WRITE;
/*!40000 ALTER TABLE `t_community_drug` DISABLE KEYS */;
INSERT INTO `t_community_drug` VALUES (1,'阿莫西林胶囊','https://tse3-mm.cn.bing.net/th/id/OIP-C.FL7GatLhEKuPr4XIW0IAMAHaHa?w=196&h=197&c=7&r=0&o=5&pid=1.7','阿莫西林适用于敏感菌(不产β内酰胺酶菌株)所致的的感染。'),(2,'999感冒灵','https://img0.baidu.com/it/u=3017500513,3335226283&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500','一日两次，早晚饭前服用'),(3,'布洛芬胶囊','https://img0.baidu.com/it/u=115615123,3939442106&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500','用于缓解轻至中度疼痛，也用于普通感冒或流行性感冒引起的发热。'),(12,'连花清瘟胶囊','https://img.yaopinnet.com/img500/1331886826022010.jpg','清瘟解毒，宣肺泄热。用于治疗流行性感冒属热毒袭肺证。'),(14,'         ','看看.jpg','     ');
/*!40000 ALTER TABLE `t_community_drug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_food`
--

DROP TABLE IF EXISTS `t_community_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_food` (
  `food_id` bigint NOT NULL AUTO_INCREMENT,
  `food_name` varchar(100) DEFAULT NULL,
  `food_image` varchar(100) DEFAULT NULL,
  `food_suger` float DEFAULT NULL,
  `food_protein` float DEFAULT NULL,
  `food_fat` float DEFAULT NULL,
  `food_energy` float DEFAULT NULL,
  `food_introduction` text,
  `food_weight` float DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  KEY `t_coummunity_food_name_index` (`food_name`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_food`
--

LOCK TABLES `t_community_food` WRITE;
/*!40000 ALTER TABLE `t_community_food` DISABLE KEYS */;
INSERT INTO `t_community_food` VALUES (1,'清炒土豆丝','https://tse4-mm.cn.bing.net/th/id/OIP-C.XrRTcF3PjFjKu4YM1k8iHAHaFj',17,3,3,102,'原料：马铃薯、大葱、食盐、味精、花生油。介绍：清炒土豆丝是以土豆作为主料，大葱作为辅料制作而成的家常菜。具有咸鲜，酸辣的口味。',NULL),(2,'烤鸭','https://pic1.zhimg.com/v2-cdd635837f448b37f4aecb61ea6c8154_r.jpg',3,11,36,379,'原料：鸭肉、酱油、黄酒、八角、花椒、大葱、姜、食盐、香叶。介绍：烤鸭色泽略黄，柔软淡香，夹卷其他荤素食物食用，为宴席常用菜点，更是家常风味美食。',NULL),(3,'烧腊煲仔饭','https://cp1.douguo.com/upload/caiku/8/2/5/yuan_826a4dccaf5fa8ed23b67ce9cffff3c5.jpg',17,8,10,187,'原料：香肠、上海青、米饭、生抽、食盐。介绍：煲仔饭是广东地区特色传统名点。它做法既简单又便捷，具有补脾、和胃、清肺的功效。',1),(4,'糖醋里脊','https://tse4-mm.cn.bing.net/th/id/OIP-C.mqsEjQKv7y-d1QAdiNlLFwHaE8?pid=ImgDet&rs=1',6,12,9,156,'原料：猪里脊、青椒、大葱、大蒜、食盐、酱油、鸡蛋、番茄酱、白砂糖、醋。介绍：糖醋里脊是中国经典传统名菜之一，该菜品以猪里脊肉为主材，酸甜可口，让人食欲大开。',NULL),(5,'青椒炒香菇','https://st-cn.meishij.net/rs/121/248/2562121/n2562121_162918067085156.jpg',5,1,3,45,'原料：青椒、香菇、食盐。介绍：青椒炒香菇是一道简单美味的家常菜品，它的味道清新鲜甜，口感柔嫩爽口，深受中国食客的喜爱。',NULL),(6,'番茄炒蛋','https://pic.quanjing.com/w6/wg/QJ9126801532.jpg',3,4,5,66,'原料：番茄、鸡蛋、食盐。介绍：番茄炒蛋是中国人吃的最多的家常菜之一。酸甜可口，物美价廉，任何人都无法拒绝番茄炒蛋的美味诱惑。',NULL),(7,'茄子豆角','https://pic3.zhimg.com/v2-1bbc01b33467f32ecb63b8507b1c4284_1440w.jpg?source=172ae18b',6,2,3,52,'原料：茄子、豆角、大蒜、食盐、生抽。介绍：茄子豆角是用茄子、豆角制作的一道家常菜。茄子营养丰富，含有蛋白质、脂肪、维生素以及钙、磷、铁等多种营养成分。',NULL),(8,'白斩鸡','https://st-cn.meishij.net/rs/169/156/2789169/n2789169_149301317942743.jpg',1,9,5,90,'原料：鸡肉、姜、大葱、大蒜、食盐、白砂糖、醋、酱油。介绍：白斩鸡形状美观，皮黄肉白，肥嫩鲜美，滋味异常鲜美，食之别有风味。',NULL),(9,'清炒藕片','https://cp1.douguo.com/upload/caiku/8/f/a/yuan_8fc5e181ed78c7ffbc841f517375368a.jpg',11,2,3,69,'原料：莲藕、食盐、酱油、味精。介绍：清炒藕片是一道家常菜，做法简单易学，是家常菜的上乘之选。做成后颜色清亮且口味适中，有利于咳喘病症的缓解。',NULL),(10,'小鸡炖蘑菇','https://cp1.douguo.com/upload/caiku/c/7/c/yuan_c7fb834ffc9c7f6126af8ea6074044ec.jpg',10,7,4,102,'原料：鸡肉、香菇、粉条、味精、料酒、大葱、姜、白砂糖、食盐、老抽。介绍：小鸡炖蘑菇是一道东北名菜。爽滑的鸡肉和醇香的蘑菇碰撞出极佳的味觉体验。',NULL),(11,'猪肉炖粉条','https://st-cn.meishij.net/rs/235/229/13994985/n13994985_158417046848625.jpg',7,7,12,158,'原料：猪肉、大白菜、粉丝、姜、大蒜、生抽、酱油、料酒、八角、胡椒粉、食盐、鸡精。介绍：猪肉炖粉条是以五花肉、粉条、酸菜或白菜为主要食材制作而成。',NULL),(12,'白灼虾','https://pic2.zhimg.com/v2-35ce6bca7e56e64dd37c7f91ac31536c_r.jpg?source=1940ef5c',4,17,3,107,'原料：基围虾、生抽、食盐、姜、小葱。介绍：白灼虾是一道传统名菜。主要食材是虾，主要烹饪工艺是白灼，“白灼”二字指的是将原汁原味的鲜虾直接放进清水里煮食。',NULL),(13,'松鼠桂鱼','https://img3.doubanio.com/lpic/s9122165.jpg',33,9,3,196,'介绍：松鼠桂鱼为苏帮菜中色香味兼具的代表之作 [1-2]  ，用鲤鱼出骨，加调味稍腌后，拖上蛋黄糊，入热油锅嫩炸成熟后，浇上熬热的糖醋卤汁，形状似鼠，外脆里嫩，酸甜可口。',NULL),(14,'炒鸡蛋','https://cp1.douguo.com/upload/caiku/1/f/2/yuan_1f672fe54d1ca84161a414e6198974b2.jpg',2,10,11,149,'原料：鸡蛋、食盐、小葱 介绍：炒鸡蛋是一道简单美味的家常菜，味道清淡但不单薄，在日常餐桌上经常见到。',NULL),(102,'烧腊煲仔饭_be360a38-e8fe-45c3-8841-d1801e5e037a',NULL,17,8,10,187,NULL,100),(103,'烧腊煲仔饭_446d491b-1d4b-428d-a846-3aa587b6d8a3',NULL,17,8,10,187,NULL,100),(106,'糖醋里脊_440f155c-ad62-45db-bc75-5cc2a41c3201',NULL,6,12,9,156,NULL,100),(108,'土司_55b8fc05-0411-4b50-852b-1805bc9f8829',NULL,10,10,7,60,NULL,200),(109,'清炒土豆丝_2bc729ae-97e7-40b8-a59a-2be5b025648f',NULL,17,9,3,102,NULL,100),(110,'烧腊煲仔饭_23a6f903-775a-4001-8603-d91d833f6860',NULL,17,8,10,187,NULL,100),(111,'123_ebf5e1e5-35d4-49e7-86ea-1066043208d5',NULL,246,236,246,246,NULL,100),(112,'14_51fcb6e2-fb15-44ce-b4f9-0e54ead91b62',NULL,45,33,33,345,NULL,156),(113,'34_6f07ea4c-a295-4ce9-a86b-488961f03bd2',NULL,343,344,343,343,NULL,3434),(114,'清炒土豆丝_14332a69-7906-4ee6-a3c0-af14616f251f',NULL,17,3,3,102,NULL,100),(115,'番茄炒蛋_5abd4a7f-10d3-469c-9a9d-e5e44ae96b9b',NULL,3,4,5,66,NULL,100),(116,'aaa_9ff7dc17-73f3-4078-93c5-14f95548e849',NULL,11,11,11,11,'11',11),(117,'as_9e9deae9-8a5a-467c-b602-364afd591e27',NULL,11,11,11,11,'11',1111),(118,'as_a989fa6b-48c6-4384-89ea-798d75a732b2',NULL,11,11,111,1111,'11',1111),(119,'清炒土豆丝_b6860d3b-9c12-4ace-ae86-77939b6f2a08',NULL,17,3,3,102,NULL,100),(120,'烧腊煲仔饭_3476238d-2ec1-46a8-b869-5cb3571b840a',NULL,17,8,10,1000,NULL,100),(121,'aaa_91b7a459-436c-4978-96cc-21705aacaee4',NULL,11,11,11,11,'11',11),(122,'烧腊煲仔饭_c518bcc8-b226-43a9-b79d-03d7f6cf0a06',NULL,17,8,10,187,NULL,100),(123,'烧腊煲仔饭_198a4752-a179-4734-90b8-f868b59c63ff',NULL,17,8,10,187,NULL,100),(124,'糖醋里脊_353c6f05-7704-4f6f-9faa-29dbbb0fc9e8',NULL,6,12,9,156,NULL,100),(125,'白斩鸡_fe0e69f7-ce8e-441f-b561-0508880b36c7',NULL,1,9,5,90,NULL,100),(126,'糖醋里脊_17895bfe-998d-4aa3-9e45-f496ddd75e2c',NULL,6,12,9,156,NULL,100),(127,'茄子豆角_f37c4cbb-f49d-4043-8bfd-ec5ddfa74a30',NULL,6,2,3,52,NULL,100),(128,'白斩鸡_5b90c080-41b4-4b51-b10f-a5ae8010a3cc',NULL,1,9,5,9090,NULL,100),(129,'青椒炒香菇_67b9254e-6dac-446f-a691-ec87c5ff05bd',NULL,5,1,3,45,NULL,100),(130,'糖醋里脊_50b5f3cf-ca86-45bf-a5b1-a6277059d81a',NULL,6,12,9,156,NULL,100),(131,'白斩鸡_9dcab7ab-27e1-4e68-b3d2-6eccc8f9616c',NULL,1,9,5,90,NULL,100),(132,'清炒藕片_06619b59-70e9-4b0f-a885-5113499e660d',NULL,11,2,3,69,NULL,100),(133,'糖醋里脊_523800ec-e4c2-40c4-931b-e822e44596f2',NULL,6,12,9,156,NULL,100),(134,'白斩鸡_2cd7275c-b9df-49ae-a715-08dc973e0bf8',NULL,1,9,5,90,NULL,100),(135,'番茄炒蛋_70377994-4079-454c-8b6d-272e9aaef15d',NULL,3,4,5,66,NULL,100),(136,'茄子豆角_eaa68d7b-bcab-4849-8a9a-3fc046e5afb0',NULL,6,2,3,52,NULL,100),(137,'清炒土豆丝_121f1417-065d-49b0-a7bc-ec348fef3e93',NULL,17,3,3,102,NULL,100),(138,'烤鸭_d10de1ca-9cd7-46ff-9e84-7adcab3d64a2',NULL,3,11,36,379,NULL,100),(139,'烤鸭_bb8e1139-fe85-497f-8fcf-8139e5252fa6',NULL,3,11,36,379,NULL,100),(140,' bbb_b031a84e-c1a3-48b5-a32a-f2f58d8b37a6',NULL,3,11,36,379,NULL,100);
/*!40000 ALTER TABLE `t_community_food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_interaction`
--

DROP TABLE IF EXISTS `t_community_interaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_interaction` (
  `interaction_id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `is_like` tinyint(1) NOT NULL,
  `is_favorite` tinyint(1) NOT NULL,
  PRIMARY KEY (`interaction_id`),
  KEY `FK_fk_post_interaction` (`post_id`),
  KEY `FK_fk_user_interaction` (`user_id`),
  CONSTRAINT `FK_fk_post_interaction` FOREIGN KEY (`post_id`) REFERENCES `t_community_post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_fk_user_interaction` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_interaction`
--

LOCK TABLES `t_community_interaction` WRITE;
/*!40000 ALTER TABLE `t_community_interaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_community_interaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_newfood`
--

DROP TABLE IF EXISTS `t_community_newfood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_newfood` (
  `food_id` bigint NOT NULL AUTO_INCREMENT,
  `food_name` varchar(100) DEFAULT NULL,
  `food_image` varchar(100) DEFAULT NULL,
  `food_suger` float DEFAULT NULL,
  `food_protein` float DEFAULT NULL,
  `food_fat` float DEFAULT NULL,
  `food_energy` float DEFAULT NULL,
  `food_introduction` text,
  `food_weight` float DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  KEY `t_coummunity_food_name_index` (`food_name`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_newfood`
--

LOCK TABLES `t_community_newfood` WRITE;
/*!40000 ALTER TABLE `t_community_newfood` DISABLE KEYS */;
INSERT INTO `t_community_newfood` VALUES (1,'清炒土豆丝','https://tse4-mm.cn.bing.net/th/id/OIP-C.XrRTcF3PjFjKu4YM1k8iHAHaFj',17,3,3,102,'原料：马铃薯、大葱、食盐、味精、花生油。介绍：清炒土豆丝是以土豆作为主料，大葱作为辅料制作而成的家常菜。具有咸鲜，酸辣的口味。',NULL),(2,'烤鸭','https://pic1.zhimg.com/v2-cdd635837f448b37f4aecb61ea6c8154_r.jpg',3,11,36,379,'原料：鸭肉、酱油、黄酒、八角、花椒、大葱、姜、食盐、香叶。介绍：烤鸭色泽略黄，柔软淡香，夹卷其他荤素食物食用，为宴席常用菜点，更是家常风味美食。',NULL),(3,'烧腊煲仔饭','https://cp1.douguo.com/upload/caiku/8/2/5/yuan_826a4dccaf5fa8ed23b67ce9cffff3c5.jpg',17,8,10,187,'原料：香肠、上海青、米饭、生抽、食盐。介绍：煲仔饭是广东地区特色传统名点。它做法既简单又便捷，具有补脾、和胃、清肺的功效。',1),(4,'糖醋里脊','https://tse4-mm.cn.bing.net/th/id/OIP-C.mqsEjQKv7y-d1QAdiNlLFwHaE8?pid=ImgDet&rs=1',6,12,9,156,'原料：猪里脊、青椒、大葱、大蒜、食盐、酱油、鸡蛋、番茄酱、白砂糖、醋。介绍：糖醋里脊是中国经典传统名菜之一，该菜品以猪里脊肉为主材，酸甜可口，让人食欲大开。',NULL),(5,'青椒炒香菇','https://st-cn.meishij.net/rs/121/248/2562121/n2562121_162918067085156.jpg',5,1,3,45,'原料：青椒、香菇、食盐。介绍：青椒炒香菇是一道简单美味的家常菜品，它的味道清新鲜甜，口感柔嫩爽口，深受中国食客的喜爱。',NULL),(6,'番茄炒蛋','https://pic.quanjing.com/w6/wg/QJ9126801532.jpg',3,4,5,66,'原料：番茄、鸡蛋、食盐。介绍：番茄炒蛋是中国人吃的最多的家常菜之一。酸甜可口，物美价廉，任何人都无法拒绝番茄炒蛋的美味诱惑。',NULL),(7,'茄子豆角','https://pic3.zhimg.com/v2-1bbc01b33467f32ecb63b8507b1c4284_1440w.jpg?source=172ae18b',6,2,3,52,'原料：茄子、豆角、大蒜、食盐、生抽。介绍：茄子豆角是用茄子、豆角制作的一道家常菜。茄子营养丰富，含有蛋白质、脂肪、维生素以及钙、磷、铁等多种营养成分。',NULL),(8,'白斩鸡','https://st-cn.meishij.net/rs/169/156/2789169/n2789169_149301317942743.jpg',1,9,5,90,'原料：鸡肉、姜、大葱、大蒜、食盐、白砂糖、醋、酱油。介绍：白斩鸡形状美观，皮黄肉白，肥嫩鲜美，滋味异常鲜美，食之别有风味。',NULL),(9,'清炒藕片','https://cp1.douguo.com/upload/caiku/8/f/a/yuan_8fc5e181ed78c7ffbc841f517375368a.jpg',11,2,3,69,'原料：莲藕、食盐、酱油、味精。介绍：清炒藕片是一道家常菜，做法简单易学，是家常菜的上乘之选。做成后颜色清亮且口味适中，有利于咳喘病症的缓解。',NULL),(10,'小鸡炖蘑菇','https://cp1.douguo.com/upload/caiku/c/7/c/yuan_c7fb834ffc9c7f6126af8ea6074044ec.jpg',10,7,4,102,'原料：鸡肉、香菇、粉条、味精、料酒、大葱、姜、白砂糖、食盐、老抽。介绍：小鸡炖蘑菇是一道东北名菜。爽滑的鸡肉和醇香的蘑菇碰撞出极佳的味觉体验。',NULL),(11,'猪肉炖粉条','https://st-cn.meishij.net/rs/235/229/13994985/n13994985_158417046848625.jpg',7,7,12,158,'原料：猪肉、大白菜、粉丝、姜、大蒜、生抽、酱油、料酒、八角、胡椒粉、食盐、鸡精。介绍：猪肉炖粉条是以五花肉、粉条、酸菜或白菜为主要食材制作而成。',NULL),(12,'白灼虾','https://pic2.zhimg.com/v2-35ce6bca7e56e64dd37c7f91ac31536c_r.jpg?source=1940ef5c',4,17,3,107,'原料：基围虾、生抽、食盐、姜、小葱。介绍：白灼虾是一道传统名菜。主要食材是虾，主要烹饪工艺是白灼，“白灼”二字指的是将原汁原味的鲜虾直接放进清水里煮食。',NULL),(13,'松鼠桂鱼','https://img3.doubanio.com/lpic/s9122165.jpg',33,9,3,196,'介绍：松鼠桂鱼为苏帮菜中色香味兼具的代表之作 [1-2]  ，用鲤鱼出骨，加调味稍腌后，拖上蛋黄糊，入热油锅嫩炸成熟后，浇上熬热的糖醋卤汁，形状似鼠，外脆里嫩，酸甜可口。',NULL),(14,'炒鸡蛋','https://cp1.douguo.com/upload/caiku/1/f/2/yuan_1f672fe54d1ca84161a414e6198974b2.jpg',2,10,11,149,'原料：鸡蛋、食盐、小葱 介绍：炒鸡蛋是一道简单美味的家常菜，味道清淡但不单薄，在日常餐桌上经常见到。',NULL);
/*!40000 ALTER TABLE `t_community_newfood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_post`
--

DROP TABLE IF EXISTS `t_community_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `post_time` time NOT NULL,
  `post_title` varchar(20) NOT NULL,
  `post_content` text NOT NULL,
  `post_image` blob,
  `like_number` bigint DEFAULT '0',
  `favorite_number` bigint DEFAULT '0',
  PRIMARY KEY (`post_id`),
  KEY `FK_fk_user_post` (`user_id`),
  CONSTRAINT `FK_fk_user_post` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_post`
--

LOCK TABLES `t_community_post` WRITE;
/*!40000 ALTER TABLE `t_community_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_community_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_community_relation`
--

DROP TABLE IF EXISTS `t_community_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_community_relation` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `doctor_id` bigint NOT NULL,
  `relation_state` int NOT NULL DEFAULT '2',
  PRIMARY KEY (`relation_id`),
  KEY `FK_fk_doctor_relation` (`doctor_id`),
  KEY `FK_fk_user_relation` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_community_relation`
--

LOCK TABLES `t_community_relation` WRITE;
/*!40000 ALTER TABLE `t_community_relation` DISABLE KEYS */;
INSERT INTO `t_community_relation` VALUES (49,5,5,2),(54,5,5,2),(55,35,11,0),(65,1,9,0),(66,5,12,0),(67,7,12,0),(68,7,14,0);
/*!40000 ALTER TABLE `t_community_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_diet_record`
--

DROP TABLE IF EXISTS `t_diet_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_diet_record` (
  `diet_id` bigint NOT NULL AUTO_INCREMENT,
  `food_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `diet_date` date NOT NULL,
  `diet_type` char(2) NOT NULL,
  PRIMARY KEY (`diet_id`),
  KEY `t_diet_record_userid_index` (`user_id`),
  KEY `FK_fk_food_diet` (`food_id`),
  CONSTRAINT `FK_fk_food_diet` FOREIGN KEY (`food_id`) REFERENCES `t_community_food` (`food_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_fk_user_diet` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_diet_record`
--

LOCK TABLES `t_diet_record` WRITE;
/*!40000 ALTER TABLE `t_diet_record` DISABLE KEYS */;
INSERT INTO `t_diet_record` VALUES (116,106,5,'2023-02-08','早餐'),(123,114,36,'2023-02-11','中餐'),(124,115,36,'2023-02-10','中餐'),(125,116,5,'2023-02-11','早餐'),(126,117,5,'2023-02-11','早餐'),(127,118,5,'2023-02-11','中餐'),(128,119,36,'2023-02-11','中餐'),(129,120,36,'2023-02-11','早餐'),(130,121,5,'2023-02-11','早餐'),(131,122,5,'2023-02-11','早餐'),(132,123,5,'2023-02-10','中餐'),(133,124,5,'2023-02-11','中餐'),(134,125,36,'2023-02-11','中餐'),(135,126,36,'2023-02-10','中餐'),(136,127,36,'2023-02-10','中餐'),(137,128,5,'2023-02-11','早餐'),(139,130,7,'2023-02-12','中餐'),(140,131,7,'2023-02-12','中餐'),(141,132,7,'2023-02-12','晚餐'),(142,133,7,'2023-02-12','晚餐'),(143,134,7,'2023-02-12','中餐'),(144,135,5,'2023-02-12','中餐'),(146,137,39,'2023-02-12','中餐'),(147,138,39,'2023-02-12','晚餐'),(148,139,39,'2023-02-12','早餐'),(149,140,39,'2023-02-13','早餐');
/*!40000 ALTER TABLE `t_diet_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_health_medication`
--

DROP TABLE IF EXISTS `t_health_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_health_medication` (
  `medication_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `drug_id` bigint NOT NULL,
  `medication_date` date DEFAULT NULL,
  `medication_type` char(2) NOT NULL,
  `is_take` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`medication_id`),
  KEY `FK_fk_drug_medication` (`drug_id`),
  KEY `FK_fk_user_medication` (`user_id`),
  CONSTRAINT `FK_fk_drug_medication` FOREIGN KEY (`drug_id`) REFERENCES `t_community_drug` (`drug_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_fk_user_medication` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_health_medication`
--

LOCK TABLES `t_health_medication` WRITE;
/*!40000 ALTER TABLE `t_health_medication` DISABLE KEYS */;
INSERT INTO `t_health_medication` VALUES (113,5,2,NULL,'午间',1),(114,5,2,NULL,'晚间',1),(115,5,1,NULL,'晚间',1),(117,7,2,NULL,'晨间',1),(118,7,1,NULL,'晨间',1),(179,5,3,NULL,'晨间',1),(182,36,1,NULL,'晚间',1),(183,36,1,NULL,'午间',1),(184,36,1,NULL,'晨间',1),(187,36,2,NULL,'晨间',1),(188,36,2,NULL,'午间',1),(189,36,2,NULL,'晚间',1),(193,36,3,NULL,'晨间',1),(195,39,1,NULL,'晨间',1),(196,39,1,NULL,'午间',1),(197,39,1,NULL,'晚间',1);
/*!40000 ALTER TABLE `t_health_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_health_record`
--

DROP TABLE IF EXISTS `t_health_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_health_record` (
  `health_record_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `record_date` date NOT NULL,
  `user_bloodsugar` decimal(3,1) DEFAULT NULL,
  `user_low_bloodpressure` int DEFAULT NULL,
  `user_high_bloodpressure` int DEFAULT NULL,
  `user_heartrate` int DEFAULT NULL,
  `user_weight` int DEFAULT NULL,
  `user_health_condition` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`health_record_id`),
  KEY `t_health_record_user_id` (`user_id`),
  CONSTRAINT `FK_fk_user_health` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_health_record`
--

LOCK TABLES `t_health_record` WRITE;
/*!40000 ALTER TABLE `t_health_record` DISABLE KEYS */;
INSERT INTO `t_health_record` VALUES (84,7,'2022-11-25',6.0,120,200,67,67,NULL),(86,5,'2022-11-25',6.0,120,200,67,67,NULL),(87,5,'2023-11-08',6.0,120,200,67,67,NULL),(92,5,'2022-11-29',6.0,120,200,67,67,NULL),(96,7,'2022-11-30',1.0,120,200,67,67,NULL),(98,7,'2022-12-01',6.0,120,200,67,67,NULL),(126,7,'2022-12-03',6.0,120,200,67,67,NULL),(127,7,'2022-12-04',6.0,120,200,67,67,NULL),(149,5,'2023-02-07',10.0,123,123,123,123,NULL),(150,5,'2023-02-08',11.0,123,123,12,12,NULL),(151,5,'2023-02-03',12.0,NULL,NULL,NULL,NULL,NULL),(152,5,'2023-02-04',11.0,NULL,NULL,NULL,NULL,NULL),(153,5,'2023-02-05',11.0,NULL,NULL,NULL,NULL,NULL),(154,5,'2023-02-06',7.0,NULL,NULL,NULL,NULL,NULL),(155,5,'2023-02-09',10.0,NULL,NULL,NULL,NULL,NULL),(175,5,'2023-02-10',17.0,NULL,NULL,NULL,NULL,NULL),(177,5,'2023-02-11',12.0,12,122,111,111,NULL),(179,36,'2023-02-06',22.0,NULL,NULL,NULL,NULL,NULL),(180,36,'2023-02-07',34.0,NULL,NULL,NULL,NULL,NULL),(181,36,'2023-02-08',23.0,NULL,NULL,NULL,NULL,NULL),(182,36,'2023-02-09',34.0,NULL,NULL,NULL,NULL,NULL),(183,36,'2023-02-10',23.0,NULL,NULL,NULL,NULL,NULL),(190,36,'2023-02-05',99.1,NULL,NULL,NULL,NULL,NULL),(192,37,'2023-02-05',5.2,NULL,NULL,NULL,NULL,NULL),(193,37,'2023-02-06',5.4,NULL,NULL,NULL,NULL,NULL),(194,37,'2023-02-07',5.7,NULL,NULL,NULL,NULL,NULL),(195,37,'2023-02-08',5.2,NULL,NULL,NULL,NULL,NULL),(196,37,'2023-02-09',5.9,NULL,NULL,NULL,NULL,NULL),(198,37,'2023-02-11',5.6,NULL,NULL,NULL,NULL,NULL),(199,37,'2023-02-10',5.5,11,12,55,55,NULL),(201,36,'2023-02-11',34.0,90,120,96,96,NULL),(214,36,'2023-02-12',5.2,90,120,96,96,NULL),(215,7,'2023-02-12',5.7,60,70,90,90,NULL),(216,7,'2023-02-11',5.4,60,90,75,75,NULL),(217,7,'2023-02-10',5.9,60,70,100,100,NULL),(218,7,'2023-02-08',5.4,50,80,90,90,NULL),(219,7,'2023-02-09',5.1,40,80,80,80,NULL),(220,7,'2023-02-06',5.5,50,70,60,60,NULL),(221,7,'2023-02-07',5.8,50,70,90,90,NULL),(222,5,'2023-02-12',11.0,11,111,111,111,NULL),(232,39,'2023-02-12',11.0,11,111,111,111,NULL),(240,39,'2023-02-06',0.0,NULL,NULL,NULL,NULL,NULL),(242,39,'2023-02-08',0.0,NULL,NULL,NULL,NULL,NULL),(243,39,'2023-02-09',0.0,NULL,NULL,NULL,NULL,NULL),(244,39,'2023-02-10',0.0,NULL,NULL,NULL,NULL,NULL),(245,39,'2023-02-11',0.0,NULL,NULL,NULL,NULL,NULL),(246,39,'2023-02-07',0.0,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_health_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_health_suggest`
--

DROP TABLE IF EXISTS `t_health_suggest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_health_suggest` (
  `suggest_id` bigint NOT NULL AUTO_INCREMENT,
  `health_data_type` char(2) NOT NULL,
  `health_data_range` varchar(10) NOT NULL,
  `suggest_content` text NOT NULL,
  PRIMARY KEY (`suggest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_health_suggest`
--

LOCK TABLES `t_health_suggest` WRITE;
/*!40000 ALTER TABLE `t_health_suggest` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_health_suggest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log_login`
--

DROP TABLE IF EXISTS `t_log_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_log_login` (
  `login_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `login_time` time NOT NULL,
  PRIMARY KEY (`login_id`),
  KEY `t_log_login_userid_index` (`user_id`),
  CONSTRAINT `FK_fk_user_login` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log_login`
--

LOCK TABLES `t_log_login` WRITE;
/*!40000 ALTER TABLE `t_log_login` DISABLE KEYS */;
INSERT INTO `t_log_login` VALUES (27,5,'12:42:29'),(33,5,'13:30:49'),(37,5,'13:47:24'),(48,5,'21:53:03'),(50,5,'21:56:02'),(52,5,'22:15:56'),(55,5,'11:09:04'),(61,7,'12:49:05'),(62,5,'14:34:39'),(65,5,'18:07:46'),(66,5,'22:39:31'),(67,7,'22:44:42'),(68,5,'22:45:04'),(72,7,'10:10:31'),(79,5,'19:14:47'),(81,7,'19:43:16'),(84,5,'20:09:22'),(85,7,'21:56:18'),(86,7,'22:04:04'),(87,7,'12:13:26'),(97,5,'14:31:04'),(98,7,'14:31:08'),(99,7,'14:56:28'),(103,7,'17:18:30'),(104,7,'17:22:41'),(117,7,'10:10:39'),(124,7,'08:23:59'),(125,7,'10:23:52'),(128,7,'12:22:44'),(130,7,'15:22:12'),(132,5,'19:17:54'),(135,5,'18:42:03'),(136,5,'18:42:07'),(137,5,'19:25:00'),(138,5,'19:30:24'),(139,5,'19:30:28'),(140,5,'19:30:31'),(141,5,'22:02:12'),(145,5,'19:44:09'),(147,5,'23:10:19'),(148,5,'23:10:55'),(149,5,'23:13:07'),(150,5,'23:14:04'),(151,5,'23:15:24'),(152,7,'19:44:38'),(153,7,'19:57:21'),(154,7,'19:58:34'),(155,7,'13:40:14'),(156,7,'13:45:08'),(157,7,'09:29:01'),(162,5,'17:08:38'),(164,5,'14:04:28'),(171,5,'12:53:38'),(184,7,'23:20:11'),(186,7,'23:39:53'),(190,7,'01:53:24'),(197,5,'16:37:11'),(202,5,'17:21:13'),(213,5,'22:26:01'),(215,5,'00:03:35'),(216,5,'00:06:14'),(223,5,'11:08:56'),(224,5,'13:00:45'),(225,5,'13:03:58'),(229,5,'14:57:20'),(230,36,'14:59:52'),(233,36,'15:56:16'),(235,5,'17:42:55'),(236,5,'18:48:37'),(237,5,'19:05:10'),(238,5,'19:30:00'),(239,1,'19:33:15'),(240,5,'19:33:36'),(241,5,'20:06:07'),(242,5,'20:25:46'),(243,5,'21:21:30'),(244,1,'21:23:34'),(245,5,'21:29:22'),(246,36,'21:34:30'),(247,36,'21:37:58'),(249,5,'22:17:29'),(250,5,'22:30:06'),(251,5,'22:34:58'),(252,36,'22:38:36'),(253,5,'22:53:08'),(254,37,'23:02:44'),(255,36,'23:05:22'),(256,5,'23:07:12'),(257,5,'23:08:20'),(261,36,'23:18:12'),(262,5,'23:19:03'),(266,37,'23:51:58'),(267,5,'00:08:00'),(268,5,'00:13:05'),(269,36,'00:43:28'),(271,7,'01:40:09'),(275,5,'10:24:58'),(280,5,'13:12:13'),(281,36,'14:14:10'),(282,5,'14:35:59'),(283,39,'14:57:34'),(284,39,'14:57:46'),(285,39,'15:36:22'),(286,5,'15:44:18'),(287,39,'15:45:03'),(288,36,'16:44:49'),(289,5,'18:17:44'),(290,37,'10:24:24');
/*!40000 ALTER TABLE `t_log_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_personal_message`
--

DROP TABLE IF EXISTS `t_personal_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_personal_message` (
  `message_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `message_from` bigint NOT NULL,
  `message_content` text NOT NULL,
  `message_date` datetime NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `FK_pk_user_message` (`user_id`),
  CONSTRAINT `FK_pk_user_message` FOREIGN KEY (`user_id`) REFERENCES `t_all_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_personal_message`
--

LOCK TABLES `t_personal_message` WRITE;
/*!40000 ALTER TABLE `t_personal_message` DISABLE KEYS */;
INSERT INTO `t_personal_message` VALUES (53,36,35,'我已同意你的绑定申请','2023-02-11 16:08:44'),(54,5,35,'我已同意你的绑定申请','2023-02-11 16:09:04'),(55,5,35,'我已同意你的绑定申请','2023-02-11 16:09:04'),(56,5,35,'我已同意你的绑定申请','2023-02-11 16:09:04'),(57,5,35,'我已同意你的绑定申请','2023-02-11 16:10:32'),(60,36,35,'shuai\n','2023-02-11 16:13:18'),(63,36,35,'as啊','2023-02-11 16:30:38'),(66,36,35,'哦莫','2023-02-11 16:31:32'),(68,5,35,'啊啊啊','2023-02-11 16:33:48'),(69,5,35,'同意了啊','2023-02-11 16:33:59'),(70,36,35,'我已同意你的绑定申请','2023-02-11 16:49:47'),(89,36,35,'efvsvcSCASCASc','2023-02-11 17:11:01'),(109,1,5,'我已同意你的绑定申请','2023-02-11 19:33:56'),(110,5,1,'Hello World','2023-02-11 19:34:51'),(111,1,5,'asgucauigciausgciuasgc\n','2023-02-11 19:35:19'),(112,5,1,'hello world','2023-02-11 21:23:46'),(113,5,1,'s','2023-02-11 21:24:11'),(115,5,35,'我已同意你的绑定申请','2023-02-11 22:01:44'),(124,36,35,'哈哈哈','2023-02-11 23:21:53'),(127,36,35,'Vjhbjhbiu\n','2023-02-11 23:36:01'),(128,7,35,'我已同意你的绑定申请','2023-02-12 02:26:29'),(130,5,39,'我已同意你的绑定申请','2023-02-12 15:45:15'),(131,5,39,'收到VS的','2023-02-12 15:57:00'),(132,7,37,'我已同意你的绑定申请','2023-02-15 10:24:50');
/*!40000 ALTER TABLE `t_personal_message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 10:41:21
