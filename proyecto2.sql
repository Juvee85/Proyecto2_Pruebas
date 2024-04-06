create database proyecto2_247700_248547;
use proyecto2_247700_248547;

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