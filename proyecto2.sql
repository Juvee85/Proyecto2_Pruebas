create database proyecto2_247700_248547;

use proyecto2_247700_248547;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(50) NOT NULL,
    contrasenia VARCHAR(64) NOT NULL
);

-- Utilizamos la función de encriptación SHA-256 para la contraseña
INSERT INTO usuarios (correo, contrasenia) 
VALUES ('root@root.com', SHA2('root', 256));