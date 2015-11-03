

-- CREATE USER 'noroot'@'localhost' IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194'; 

CREATE SCHEMA IF NOT EXISTS `agenda`;

USE `agenda`;

-- Create Tables ----------------------------------------------------------------------------------------------




 


CREATE TABLE Usuarios
(
	`idUsuario` INT NOT NULL AUTO_INCREMENT
	,PRIMARY KEY (idUsuario)
	,`nombre` VARCHAR(50) NOT NULL 
	,`apellido` VARCHAR(50) NOT NULL 
	,`nombreUsuario` VARCHAR(50) NOT NULL 
	,`idioma` VARCHAR(7) NOT NULL 
)
ENGINE=INNODB;

  
CREATE TABLE EventosPrivados
(
	`idEvento` INT NOT NULL 
	,PRIMARY KEY (idEvento)
	,`direccion` VARCHAR(250)  NULL 
	,`descripcion` VARCHAR(250)  NULL 
)
ENGINE=INNODB;


CREATE TABLE Reuniones
(
	`idEvento` INT NOT NULL AUTO_INCREMENT
	,PRIMARY KEY (idEvento)
	,`sala_id` INT NOT NULL 
	,`temario` VARCHAR(250) NOT NULL 
)
ENGINE=INNODB;


CREATE TABLE Contrasenias
(
	`usuario_id` INT NOT NULL 
	,`contraseña` VARCHAR(250) NOT NULL 
)
ENGINE=INNODB;


CREATE TABLE Eventos
(
	`idEvento` INT NOT NULL AUTO_INCREMENT
	,PRIMARY KEY (idEvento)
	,`nombre` VARCHAR(50) NOT NULL 
	,`fecha` DATETIME NOT NULL 
	,`horadeinicio` INT NOT NULL 
	,`horadefin` INT NOT NULL 
	,`autor_id` INT NOT NULL 
)
ENGINE=INNODB;

CREATE TABLE Salas
(
	`idSala` INT NOT NULL AUTO_INCREMENT
	,PRIMARY KEY (idSala)
	,`descripcion` VARCHAR(250) NOT NULL 
)
ENGINE=INNODB;




CREATE TABLE ReunionesInvitados
(
	`reunion_id` INT NOT NULL 
	,`invitado_id` INT NOT NULL 
	,`aceptado` BIT  NULL 
)
ENGINE=INNODB;

-- Create Foreign Keys ----------------------------------------------------------------------------------------------

ALTER TABLE ReunionesInvitados ADD FOREIGN KEY (reunion_id) REFERENCES Reuniones(idEvento);

ALTER TABLE Reuniones ADD FOREIGN KEY (sala_id) REFERENCES Salas(idSala);
 
ALTER TABLE Eventos ADD FOREIGN KEY (autor_id) REFERENCES Usuarios(idUsuario);

ALTER TABLE EventosPrivados ADD FOREIGN KEY (idEvento) REFERENCES Eventos(idEvento);

ALTER TABLE Reuniones ADD FOREIGN KEY (idEvento) REFERENCES Eventos(idEvento);

ALTER TABLE ReunionesInvitados ADD FOREIGN KEY (invitado_id) REFERENCES Usuarios(idUsuario);

ALTER TABLE Contrasenias ADD FOREIGN KEY (usuario_id) REFERENCES Usuarios(idUsuario);

-- Populate Tables ----------------------------------------------------------------------------------------------

INSERT INTO Usuarios (nombre,apellido,usuario,idioma) VALUES ('pepe','argento','pepea','es_AR'),('uncle','tom','uncletom','en_US') ;
INSERT INTO Contrasenias (usuario_id ,contraseña) VALUES ('1','elpepe'),('2','ilovewar');

-- Grant privileges ----------------------------------------------------------------------------------------------

GRANT ALL PRIVILEGES ON `agenda`.* TO 'noroot'@'localhost'  IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194';
