-- EJECUTAR TODO EL SCRIPT DE JALÓN
-- EJECUTAR TODO EL SCRIPT DE JALÓN
-- EJECUTAR TODO EL SCRIPT DE JALÓN

create database proyecto2_247700_248547;
use proyecto2_247700_248547;

CREATE TABLE `tarifas_placas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `costo` float NOT NULL,
  `tipo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tarifas_licencia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `costo_discapacitado` float NOT NULL,
  `costo_normal` float NOT NULL,
  `vigencia` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Creación de las tarifas de licencia.
insert into tarifas_licencia
(costo_discapacitado, costo_normal, vigencia)
values
(200, 600, "1 año"),
(500, 900, "2 años"),
(700, 1100, "3 años");

-- Creación de las tarifas de placas.
insert into tarifas_placas
(tipo, costo)
values
("Automóvil nuevo", 1500),
("Automóvil usado", 1000);