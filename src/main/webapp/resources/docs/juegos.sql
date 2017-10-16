-- MySQL dump 10.13  Distrib 5.7.13, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: juegos
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.19-MariaDB

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
-- Table structure for table `ahorcado`
--

DROP TABLE IF EXISTS `ahorcado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ahorcado` (
  `id_ahorcado` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `palabra` varchar(45) NOT NULL,
  `letras_usadas` varchar(70) NOT NULL,
  `intentos_sobrantes` int(11) NOT NULL,
  `gano` tinyint(1) NOT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_ahorcado`),
  KEY `fk_juegos_ahorcado_usuarios_idx` (`id_usuario`),
  CONSTRAINT `fk_juegos_ahorcado_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ahorcado`
--

LOCK TABLES `ahorcado` WRITE;
/*!40000 ALTER TABLE `ahorcado` DISABLE KEYS */;
INSERT INTO `ahorcado` VALUES (1,NULL,'2017-06-01 08:59:33','jaranero','[]',0,0,'1.1.1.1',1),(2,NULL,'2017-06-01 09:04:06','aventador','[]',0,0,'1.1.1.1',1),(3,NULL,'2017-06-01 09:04:45','impuramente','[]',0,0,'1.1.1.1',1),(4,NULL,'2017-06-01 09:05:14','nictaginaceo','[]',0,0,'1.1.1.1',1),(5,NULL,'2017-06-01 09:08:15','sonsaca','[]',0,0,'1.1.1.1',1),(6,NULL,'2017-08-19 04:42:34','inconsultamente','[A, E, I, O, U, R, M, T, P, N, L, S, C]',8,1,'1.1.1.1',1),(7,NULL,'2017-08-19 04:49:22','atochar','[A, E, I, O, U, R, T, P, M, N, L, Z, V, C, H]',1,1,NULL,1),(8,NULL,'2017-08-19 04:55:49','espaldero','[A, E, I, O, U, R, P, M, T, L, S, D]',6,1,NULL,1),(9,NULL,'2017-08-19 05:19:00','alfilerazo','[A, E, I, O, U, R, L, D, T, N, M, P, C, V, S, Z, F]',1,1,NULL,1),(10,2,'2017-08-19 05:20:05','zafiedad','[A, E, I, O, U, R, L, P, M, N, S, D, V, C]',0,0,NULL,1),(11,3,'2017-08-19 07:03:16','gobio','[A, E, I, O, U, R, T, L, P, F, N, M]',0,0,NULL,1),(12,2,'2017-08-23 08:18:12','panamericanismo','[A, E, I, O, U, R, T, V, D, L, M, N, P, C, S]',5,1,NULL,1),(13,2,'2017-08-23 08:24:07','inspirativo','[I, N, S, P, R, A, T, V, O]',10,1,NULL,1),(14,2,'2017-08-23 11:39:57','desentronizar','[A, E, I, O, U, R, T, F, C, S, D, Z, V, B, N]',5,1,NULL,1),(15,2,'2017-10-14 06:46:40','amabilidad','[A, E, I, O, U, R, P, L, N, T, S, D, M, B]',2,1,NULL,1),(16,2,'2017-10-14 06:47:04','pesimismo','[A, E, I, O, U, R, T, L, P, D, M, N, S]',3,1,NULL,1);
/*!40000 ALTER TABLE `ahorcado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuracion`
--

DROP TABLE IF EXISTS `configuracion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuracion` (
  `id_configuracion` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `valor_texto` varchar(100) DEFAULT NULL,
  `valor_entero` int(11) DEFAULT NULL,
  `valor_decimal` decimal(15,5) DEFAULT NULL,
  `valor_fecha` date DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_configuracion`),
  KEY `fk_configuracion_2` (`id_usuario`),
  KEY `fk_configuracion_1_idx` (`id_tipo`),
  CONSTRAINT `fk_configuracion_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipos` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_configuracion_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuracion`
--

LOCK TABLES `configuracion` WRITE;
/*!40000 ALTER TABLE `configuracion` DISABLE KEYS */;
INSERT INTO `configuracion` VALUES (1,1,18,NULL,15,NULL,NULL,1),(2,2,18,NULL,12,NULL,NULL,1),(3,1,22,NULL,6,NULL,NULL,1),(4,2,22,NULL,13,NULL,NULL,1),(5,1,19,'es',NULL,NULL,NULL,1),(6,2,19,'en',NULL,NULL,NULL,1),(7,1,23,'rubik',NULL,NULL,NULL,1),(8,2,23,'ahorcado',NULL,NULL,NULL,1),(9,3,18,NULL,15,NULL,NULL,1),(10,3,22,NULL,6,NULL,NULL,1),(11,3,19,'es',NULL,NULL,NULL,1),(12,3,23,'rubik',NULL,NULL,NULL,1),(13,4,18,NULL,15,NULL,NULL,1),(14,4,22,NULL,6,NULL,NULL,1),(15,4,19,'es',NULL,NULL,NULL,1),(16,4,23,'rubik',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `configuracion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credenciales`
--

DROP TABLE IF EXISTS `credenciales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credenciales` (
  `id_credencial` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `correo` varchar(120) NOT NULL,
  `clave` varchar(32) NOT NULL,
  `fecha_inicio` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_credencial`),
  KEY `fk_credenciales_1_idx` (`id_usuario`),
  CONSTRAINT `fk_credenciales_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credenciales`
--

LOCK TABLES `credenciales` WRITE;
/*!40000 ALTER TABLE `credenciales` DISABLE KEYS */;
/*!40000 ALTER TABLE `credenciales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estados` (
  `id_estado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_estado` varchar(45) NOT NULL,
  `name_estado` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'activo','active',1);
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisos` (
  `id_permiso` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) DEFAULT NULL,
  `id_padre` int(11) DEFAULT NULL,
  `nombre_permiso` varchar(45) NOT NULL,
  `descripcion_permiso` varchar(45) NOT NULL,
  `name_permiso` varchar(45) NOT NULL,
  `description_permiso` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_permiso`),
  KEY `fk_permisos_1_idx` (`id_padre`),
  CONSTRAINT `fk_permisos_1` FOREIGN KEY (`id_padre`) REFERENCES `permisos` (`id_permiso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(15) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Superusuario','Superusuario',1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_permisos`
--

DROP TABLE IF EXISTS `roles_permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_permisos` (
  `id_rol` int(11) NOT NULL,
  `id_permiso` int(11) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_rol`,`id_permiso`),
  KEY `fk_roles_permisos_1_idx` (`id_permiso`),
  CONSTRAINT `fk_roles_permisos_1` FOREIGN KEY (`id_permiso`) REFERENCES `permisos` (`id_permiso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_permisos_2` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_permisos`
--

LOCK TABLES `roles_permisos` WRITE;
/*!40000 ALTER TABLE `roles_permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesiones_rubik`
--

DROP TABLE IF EXISTS `sesiones_rubik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sesiones_rubik` (
  `id_sesion` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_sesion`),
  KEY `fk_sesion_rubik_1_idx` (`id_usuario`),
  CONSTRAINT `fk_sesion_rubik_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesiones_rubik`
--

LOCK TABLES `sesiones_rubik` WRITE;
/*!40000 ALTER TABLE `sesiones_rubik` DISABLE KEYS */;
INSERT INTO `sesiones_rubik` VALUES (1,NULL,'2017-05-31 11:55:12',NULL,1),(2,NULL,'2017-05-31 11:58:32',NULL,1),(3,NULL,'2017-06-01 12:14:42',NULL,1),(4,NULL,'2017-06-01 12:17:10',NULL,1),(5,NULL,'2017-06-01 07:27:51',NULL,1),(6,NULL,'2017-08-19 04:13:55',NULL,1),(7,1,'2017-08-19 04:31:45',NULL,1),(8,NULL,'2017-08-19 04:33:14',NULL,1),(9,2,'2017-08-19 04:35:00',NULL,1),(10,2,'2017-08-19 09:44:01',NULL,1),(11,1,'2017-09-02 03:53:40',NULL,1),(12,2,'2017-09-02 04:03:24',NULL,1);
/*!40000 ALTER TABLE `sesiones_rubik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiempos_rubik`
--

DROP TABLE IF EXISTS `tiempos_rubik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiempos_rubik` (
  `id_tiempo` int(11) NOT NULL AUTO_INCREMENT,
  `id_sesion` int(11) DEFAULT NULL,
  `id_tipo_cubo` int(11) NOT NULL,
  `mezcla` varchar(255) NOT NULL,
  `tiempo_inspeccion_segundos` int(11) NOT NULL,
  `tiempo_inspeccion_usado_milisegundos` int(11) NOT NULL,
  `tiempo_inspeccion_usado_texto` varchar(15) NOT NULL,
  `tiempo_milisegundos` int(11) NOT NULL,
  `tiempo_texto` varchar(15) NOT NULL,
  `dnf` tinyint(1) NOT NULL,
  `penalizacion` tinyint(1) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `video` varchar(255) DEFAULT NULL,
  `ip` varchar(15) DEFAULT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_tiempo`),
  KEY `fk_tiempos_rubik_tipos_juegos_idx` (`id_tipo_cubo`),
  KEY `fk_tiempos_rubik_2_idx` (`id_sesion`),
  CONSTRAINT `fk_tiempos_rubik_1` FOREIGN KEY (`id_tipo_cubo`) REFERENCES `tipos` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tiempos_rubik_2` FOREIGN KEY (`id_sesion`) REFERENCES `sesiones_rubik` (`id_sesion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiempos_rubik`
--

LOCK TABLES `tiempos_rubik` WRITE;
/*!40000 ALTER TABLE `tiempos_rubik` DISABLE KEYS */;
INSERT INTO `tiempos_rubik` VALUES (1,1,5,'F U F2 U2 F R\' U F2 R\' ',15,320,'0.32',650,'00:00.65',0,0,NULL,NULL,NULL,1),(2,1,5,'R\' F2 U\' F2 U R F2 U F2 ',15,220,'0.22',860,'00:00.86',0,0,NULL,NULL,NULL,1),(3,1,5,'R\' U F2 R2 F\' R\' U2 F2 R\' ',15,280,'0.28',590,'00:00.59',0,0,NULL,NULL,NULL,1),(4,1,5,'R F2 R\' F R\' U\' R\' U F2 ',15,120,'0.12',590,'00:00.59',0,0,NULL,NULL,NULL,1),(5,1,5,'U2 R2 U2 R2 U R\' F\' R F\' ',15,250,'0.25',680,'00:00.68',0,0,NULL,NULL,NULL,1),(6,2,6,'L2 B\' F D B2 D2 L2 D\' F U2 F L2 U2 D2 L2 D\' R\' L\' F\' U L ',15,460,'0.46',730,'00:00.73',0,0,NULL,NULL,NULL,1),(7,2,6,'R U D2 L2 U2 F R L\' B2 U\' D\' L2 F2 L\' B2 D2 F\' D\' L\' U B\' ',15,390,'0.39',790,'00:00.79',0,0,NULL,NULL,NULL,1),(8,2,6,'R2 B L B F U2 F\' U2 B\' D\' U2 B\' F\' U2 L2 F\' B\' U R2 D\' U2 ',15,190,'0.19',1000,'00:01.00',0,0,NULL,NULL,NULL,1),(9,3,5,'U\' R2 F R F U2 R F\' U\' ',15,580,'0.58',1100,'00:01.10',0,0,NULL,NULL,NULL,1),(10,3,5,'F\' R2 F R U F2 U2 F2 U ',15,350,'0.35',370,'00:00.37',0,0,NULL,NULL,NULL,1),(11,4,5,'U\' F2 R2 U\' R U2 R2 U F2 ',15,550,'0.55',750,'00:00.75',0,0,NULL,NULL,NULL,1),(12,5,5,'R2 F U R F\' U2 F R2 U2 ',15,290,'0.29',410,'00:00.41',0,0,NULL,NULL,NULL,1),(13,5,5,'R F U R\' F\' R F R2 F\' ',15,40,'0.04',3760,'00:03.76',0,0,NULL,NULL,NULL,1),(14,5,5,'F R2 F\' R\' U\' R U2 R F\' ',15,300,'0.3',360,'00:00.36',0,0,NULL,NULL,NULL,1),(15,6,5,'R\' U F2 U\' R2 F\' R\' U F ',15,970,'0.97',1060,'00:01.06',0,0,NULL,NULL,NULL,1),(16,6,5,'U2 F\' U2 R\' F R F R U ',15,2220,'2.22',5140,'00:05.14',0,0,NULL,NULL,NULL,1),(17,7,6,'F\' B L B2 U2 D\' L\' F D L\' D B2 U B D F\' D U\' B2 R\' F2 ',15,3120,'3.12',9820,'00:09.82',0,0,NULL,NULL,NULL,1),(18,8,5,'R\' F\' R U2 F\' U F2 U2 R2 ',15,340,'0.34',340,'DNF(00:00.34)',1,0,NULL,NULL,NULL,1),(19,8,5,'R2 U\' R U R2 F\' R2 U\' F\' ',15,810,'0.81',1230,'00:01.23 +2',0,1,NULL,NULL,NULL,1),(20,9,5,'R\' U R U R F R U F ',15,380,'0.38',490,'00:00.49',0,0,NULL,NULL,NULL,1),(21,9,5,'U2 R\' U\' F2 R2 U F R U2 ',15,130,'0.13',230,'00:00.23',0,0,NULL,NULL,NULL,1),(22,9,5,'F\' U R F R\' U\' F U\' R2 ',15,360,'0.36',1110,'00:01.11',0,0,NULL,NULL,NULL,1),(23,9,5,'R2 U2 R U\' F\' U\' F\' R F2 ',15,250,'0.25',370,'00:00.37',0,0,NULL,NULL,NULL,1),(24,9,5,'U2 R2 F U\' R2 U F2 R\' U2 ',15,460,'0.46',740,'00:00.74',0,0,NULL,NULL,NULL,1),(25,9,5,'U2 R\' F2 R U\' F\' R F U\' ',15,340,'0.34',180,'00:00.18',0,0,NULL,NULL,NULL,1),(26,9,5,'U2 F2 R F\' U\' R2 U\' R\' F2 ',15,560,'0.56',380,'00:00.38',0,0,NULL,NULL,NULL,1),(27,9,5,'F2 R\' U F R2 F\' R\' U R ',15,510,'0.51',1030,'00:01.03',0,0,NULL,NULL,NULL,1),(28,10,5,'U\' F2 R2 F\' R F\' R2 U\' R\' ',15,670,'0.67',2360,'00:02.36',0,0,NULL,NULL,NULL,1),(29,10,5,'F\' R U\' R\' U\' F U\' F R\' ',15,460,'0.46',3640,'00:03.64',0,0,NULL,NULL,NULL,1),(30,10,5,'R\' U\' F2 U\' F U\' F U R\' ',15,420,'0.42',2910,'00:02.91',0,0,NULL,NULL,NULL,1),(31,10,5,'U\' F\' R\' U\' R\' U\' F\' R2 F2 ',15,340,'0.34',600,'00:00.60',0,0,NULL,NULL,NULL,1),(32,11,6,'B2 R U D L\' B U\' B2 F U2 B F\' D L\' B L U L F\' R B\' ',15,280,'0.28',270,'00:00.27',0,0,NULL,NULL,NULL,1),(33,11,5,'F R\' F U\' R\' U\' R2 F\' R2 ',15,180,'0.18',240,'00:00.24',0,0,NULL,NULL,NULL,1),(34,11,6,'U2 F2 B L\' U B F U R2 L U2 F2 B D B F L\' R U\' B2 F ',15,240,'0.24',260,'00:00.26',0,0,NULL,NULL,NULL,1),(35,11,6,'R B\' R2 D L F R B\' D L\' U2 B\' D\' R2 L D\' F U2 D2 B2 D\' ',15,190,'0.19',260,'00:00.26',0,0,NULL,NULL,NULL,1),(36,11,6,'F\' R2 F U2 F\' U R F L2 F2 U2 B\' D F2 L D2 R\' F2 U2 B\' R ',15,240,'0.24',400,'00:00.40',0,0,NULL,NULL,NULL,1),(37,12,5,'F2 R\' F\' U2 F U\' F2 R2 F ',5,290,'0.29',180,'00:00.18',0,0,NULL,NULL,NULL,1),(38,12,6,'L2 U\' D B2 F2 L\' R2 D2 B L2 D\' B\' U2 L2 U2 L2 F\' D L D\' R\' ',5,240,'0.24',260,'00:00.26',0,0,NULL,NULL,NULL,1),(39,12,11,'D U\' B2 D\' F2 U L F\' L B2 L\' R D\' F D\' F\' U2 F2 B D R2 ',5,250,'0.25',300,'00:00.30',0,0,NULL,NULL,NULL,1),(40,12,12,'B L\' R\' D2 B\' F2 D R B F R U2 B\' D2 R\' F\' U\' R2 L B R2 ',5,260,'0.26',270,'00:00.27',0,0,NULL,NULL,NULL,1),(41,12,13,'U D\' B\' U2 D F\' U\' R B R2 D\' B U2 D\' R2 D L\' U\' D L U ',5,90,'0.09',270,'00:00.27',0,0,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `tiempos_rubik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos` (
  `id_tipo` int(11) NOT NULL AUTO_INCREMENT,
  `id_padre` int(11) DEFAULT NULL,
  `nombre_tipo` varchar(45) NOT NULL,
  `name_tipo` varchar(45) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_tipo`),
  KEY `fk_tipos_1_idx` (`id_padre`),
  CONSTRAINT `fk_tipos_1` FOREIGN KEY (`id_padre`) REFERENCES `tipos` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,NULL,'categorias','categories',1),(2,1,'tipo de cubo','type of puzzle',1),(3,1,'configuraciones de usuario','user settings',1),(4,1,'idioma','language',1),(5,2,'2 x 2 x 2','2 x 2 x 2',1),(6,2,'3 x 3 x 3','3 x 3 x 3',1),(7,2,'4 x 4 x 4','4 x 4 x 4',0),(8,2,'5 x 5 x 5','5 x 5 x 5',0),(9,2,'6 x 6 x 6','6 x 6 x 6',0),(10,2,'7 x 7 x 7','7 x 7 x 7',0),(11,2,'3 x 3 x 3 BLD','3 x 3 x 3 BLD',1),(12,2,'3 x 3 x 3 With Feet','3 x 3 x 3 With Feet',1),(13,2,'3 x 3 x 3 OH','3 x 3 x 3 OH',1),(14,2,'4 x 4 x 4 BLD','4 x 4 x 4 BLD',0),(15,2,'5 x 5 x 5 BLD','5 x 5 x 5 BLD',0),(16,2,'MBLD','MBLD',0),(17,2,'Fewest Moves','Fewest Moves',0),(18,3,'tiempo de inspeccion','inspection time',1),(19,3,'idioma preferido','favorite languaje',1),(20,4,'Español','es',1),(21,4,'English','en',1),(22,3,'id_cubo_preferido','id_favorite_cube',1),(23,3,'pagina inicial','initial page',1);
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `correo` varchar(100) NOT NULL,
  `clave` varchar(32) NOT NULL,
  `nombres` varchar(60) DEFAULT NULL,
  `apellidos` varchar(60) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'njariza0@misena.edu.co','50c0bbbfc0458f0e480bbc800d079d73','Nelson Javier','Ariza Santamaría','M','1989-12-08','2017-08-17 08:49:29','2017-08-17 13:49:29',1),(2,'njarizas@hotmail.com','50c0bbbfc0458f0e480bbc800d079d73','Nelson Javier','Ariza Santamaría','M','1989-12-08','2017-08-19 08:06:53','2017-08-19 13:06:53',1),(3,'fabioandrescar@gmail.com','50c0bbbfc0458f0e480bbc800d079d73','Fabio Andrés','Hurtado Cardona','M','2002-08-24','2017-08-19 07:01:43','2017-08-19 12:01:43',1),(4,'njarizas@gmail.com','50c0bbbfc0458f0e480bbc800d079d73','Nelson Javier','Ariza Santamaría','M','1989-12-08','2017-10-14 07:29:46','2017-10-14 12:29:46',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_roles` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `fk_usuarios_roles_1_idx` (`id_rol`),
  CONSTRAINT `fk_usuarios_roles_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_roles_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_roles`
--

LOCK TABLES `usuarios_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_roles` DISABLE KEYS */;
INSERT INTO `usuarios_roles` VALUES (1,1,1),(2,1,1),(3,1,1),(4,1,1);
/*!40000 ALTER TABLE `usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-15 21:05:01
