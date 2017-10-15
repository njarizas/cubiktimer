-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema juegos
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `juegos` ;

-- -----------------------------------------------------
-- Schema juegos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `juegos` DEFAULT CHARACTER SET utf8 ;
USE `juegos` ;

-- -----------------------------------------------------
-- Table `juegos`.`usuarios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`usuarios` ;

CREATE TABLE IF NOT EXISTS `juegos`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `correo` VARCHAR(100) NOT NULL,
  `clave` VARCHAR(32) NOT NULL,
  `nombres` VARCHAR(60) NULL,
  `apellidos` VARCHAR(60) NULL,
  `sexo` CHAR(1) NULL,
  `fecha_nacimiento` DATE NULL,
  `fecha_creacion` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegos`.`ahorcado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`ahorcado` ;

CREATE TABLE IF NOT EXISTS `juegos`.`ahorcado` (
  `id_ahorcado` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NULL,
  `fecha` DATETIME NOT NULL,
  `palabra` VARCHAR(45) NOT NULL,
  `letras_usadas` VARCHAR(70) NOT NULL,
  `intentos_sobrantes` INT NOT NULL,
  `gano` TINYINT(1) NOT NULL,
  `ip` VARCHAR(15) NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_ahorcado`),
  CONSTRAINT `fk_juegos_ahorcado_usuarios`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `juegos`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_juegos_ahorcado_usuarios_idx` ON `juegos`.`ahorcado` (`id_usuario` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`tipos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`tipos` ;

CREATE TABLE IF NOT EXISTS `juegos`.`tipos` (
  `id_tipo` INT NOT NULL AUTO_INCREMENT,
  `id_padre` INT NULL,
  `nombre_tipo` VARCHAR(45) NOT NULL,
  `name_tipo` VARCHAR(45) NOT NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_tipo`),
  CONSTRAINT `fk_tipos_1`
    FOREIGN KEY (`id_padre`)
    REFERENCES `juegos`.`tipos` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tipos_1_idx` ON `juegos`.`tipos` (`id_padre` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`sesiones_rubik`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`sesiones_rubik` ;

CREATE TABLE IF NOT EXISTS `juegos`.`sesiones_rubik` (
  `id_sesion` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NULL,
  `fecha` DATETIME NOT NULL,
  `ip` VARCHAR(15) NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_sesion`),
  CONSTRAINT `fk_sesion_rubik_1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `juegos`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_sesion_rubik_1_idx` ON `juegos`.`sesiones_rubik` (`id_usuario` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`tiempos_rubik`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`tiempos_rubik` ;

CREATE TABLE IF NOT EXISTS `juegos`.`tiempos_rubik` (
  `id_tiempo` INT NOT NULL AUTO_INCREMENT,
  `id_sesion` INT NULL,
  `id_tipo_cubo` INT NOT NULL,
  `mezcla` VARCHAR(255) NOT NULL,
  `tiempo_inspeccion_segundos` INT NOT NULL,
  `tiempo_inspeccion_usado_milisegundos` INT NOT NULL,
  `tiempo_inspeccion_usado_texto` VARCHAR(15) NOT NULL,
  `tiempo_milisegundos` INT NOT NULL,
  `tiempo_texto` VARCHAR(15) NOT NULL,
  `dnf` TINYINT(1) NOT NULL,
  `penalizacion` TINYINT(1) NOT NULL,
  `comentario` VARCHAR(255) NULL,
  `video` VARCHAR(255) NULL,
  `ip` VARCHAR(15) NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_tiempo`),
  CONSTRAINT `fk_tiempos_rubik_1`
    FOREIGN KEY (`id_tipo_cubo`)
    REFERENCES `juegos`.`tipos` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tiempos_rubik_2`
    FOREIGN KEY (`id_sesion`)
    REFERENCES `juegos`.`sesiones_rubik` (`id_sesion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tiempos_rubik_tipos_juegos_idx` ON `juegos`.`tiempos_rubik` (`id_tipo_cubo` ASC);

CREATE INDEX `fk_tiempos_rubik_2_idx` ON `juegos`.`tiempos_rubik` (`id_sesion` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`permisos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`permisos` ;

CREATE TABLE IF NOT EXISTS `juegos`.`permisos` (
  `id_permiso` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(100) NULL,
  `id_padre` INT NULL,
  `nombre_permiso` VARCHAR(45) NOT NULL,
  `descripcion_permiso` VARCHAR(45) NOT NULL,
  `name_permiso` VARCHAR(45) NOT NULL,
  `description_permiso` VARCHAR(45) NOT NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_permiso`),
  CONSTRAINT `fk_permisos_1`
    FOREIGN KEY (`id_padre`)
    REFERENCES `juegos`.`permisos` (`id_permiso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_permisos_1_idx` ON `juegos`.`permisos` (`id_padre` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`roles` ;

CREATE TABLE IF NOT EXISTS `juegos`.`roles` (
  `id_rol` INT NOT NULL AUTO_INCREMENT,
  `nombre_rol` VARCHAR(15) NOT NULL,
  `descripcion` VARCHAR(100) NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_rol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegos`.`roles_permisos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`roles_permisos` ;

CREATE TABLE IF NOT EXISTS `juegos`.`roles_permisos` (
  `id_rol` INT NOT NULL,
  `id_permiso` INT NOT NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_rol`, `id_permiso`),
  CONSTRAINT `fk_roles_permisos_1`
    FOREIGN KEY (`id_permiso`)
    REFERENCES `juegos`.`permisos` (`id_permiso`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_permisos_2`
    FOREIGN KEY (`id_rol`)
    REFERENCES `juegos`.`roles` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_roles_permisos_1_idx` ON `juegos`.`roles_permisos` (`id_permiso` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`usuarios_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`usuarios_roles` ;

CREATE TABLE IF NOT EXISTS `juegos`.`usuarios_roles` (
  `id_usuario` INT NOT NULL,
  `id_rol` INT NOT NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_usuario`, `id_rol`),
  CONSTRAINT `fk_usuarios_roles_1`
    FOREIGN KEY (`id_rol`)
    REFERENCES `juegos`.`roles` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuarios_roles_2`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `juegos`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_usuarios_roles_1_idx` ON `juegos`.`usuarios_roles` (`id_rol` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`estados`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`estados` ;

CREATE TABLE IF NOT EXISTS `juegos`.`estados` (
  `id_estado` INT NOT NULL AUTO_INCREMENT,
  `nombre_estado` VARCHAR(45) NOT NULL,
  `name_estado` VARCHAR(45) NOT NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_estado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `juegos`.`configuracion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`configuracion` ;

CREATE TABLE IF NOT EXISTS `juegos`.`configuracion` (
  `id_configuracion` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `id_tipo` INT NOT NULL,
  `valor_texto` VARCHAR(100) NULL,
  `valor_entero` INT NULL,
  `valor_decimal` DECIMAL(15,5) NULL,
  `valor_fecha` DATE NULL,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_configuracion`),
  CONSTRAINT `fk_configuracion_1`
    FOREIGN KEY (`id_tipo`)
    REFERENCES `juegos`.`tipos` (`id_tipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_configuracion_2`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `juegos`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_configuracion_1_idx` ON `juegos`.`configuracion` (`id_tipo` ASC);


-- -----------------------------------------------------
-- Table `juegos`.`credenciales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `juegos`.`credenciales` ;

CREATE TABLE IF NOT EXISTS `juegos`.`credenciales` (
  `id_credencial` INT NOT NULL AUTO_INCREMENT,
  `id_usuario` INT NOT NULL,
  `correo` VARCHAR(120) NOT NULL,
  `clave` VARCHAR(32) NOT NULL,
  `fecha_inicio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_credencial`),
  CONSTRAINT `fk_credenciales_1`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `juegos`.`usuarios` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_credenciales_1_idx` ON `juegos`.`credenciales` (`id_usuario` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `juegos`.`tipos`
-- -----------------------------------------------------
START TRANSACTION;
USE `juegos`;
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (1, NULL, 'categorias', 'categories', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (2, 1, 'tipo de cubo', 'type of puzzle', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (3, 1, 'configuraciones de usuario', 'user settings', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (4, 1, 'idioma', 'language', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (5, 2, '2 x 2 x 2', '2 x 2 x 2', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (6, 2, '3 x 3 x 3', '3 x 3 x 3', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (7, 2, '4 x 4 x 4', '4 x 4 x 4', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (8, 2, '5 x 5 x 5', '5 x 5 x 5', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (9, 2, '6 x 6 x 6', '6 x 6 x 6', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (10, 2, '7 x 7 x 7', '7 x 7 x 7', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (11, 2, '3 x 3 x 3 BLD', '3 x 3 x 3 BLD', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (12, 2, '3 x 3 x 3 With Feet', '3 x 3 x 3 With Feet', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (13, 2, '3 x 3 x 3 OH', '3 x 3 x 3 OH', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (14, 2, '4 x 4 x 4 BLD', '4 x 4 x 4 BLD', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (15, 2, '5 x 5 x 5 BLD', '5 x 5 x 5 BLD', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (16, 2, 'MBLD', 'MBLD', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (17, 2, 'Fewest Moves', 'Fewest Moves', 0);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (18, 3, 'tiempo de inspeccion', 'inspection time', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (19, 3, 'idioma preferido', 'language', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (20, 4, 'Español', 'Spanish', 1);
INSERT INTO `juegos`.`tipos` (`id_tipo`, `id_padre`, `nombre_tipo`, `name_tipo`, `estado`) VALUES (21, 5, 'Inglés', 'English', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `juegos`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `juegos`;
INSERT INTO `juegos`.`roles` (`id_rol`, `nombre_rol`, `descripcion`, `estado`) VALUES (1, 'Superusuario', 'Superusuario', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `juegos`.`estados`
-- -----------------------------------------------------
START TRANSACTION;
USE `juegos`;
INSERT INTO `juegos`.`estados` (`id_estado`, `nombre_estado`, `name_estado`, `estado`) VALUES (1, 'activo', 'active', 1);

COMMIT;

