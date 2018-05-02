

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
	, UNIQUE(nombreUsuario) 
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
	,`valor` VARCHAR(250) NOT NULL 
)
ENGINE=INNODB;


CREATE TABLE Eventos
(
	`idEvento` INT NOT NULL AUTO_INCREMENT
	,PRIMARY KEY (idEvento)
	,`titulo` VARCHAR(50) NOT NULL 
	,`fecha` DATETIME NOT NULL 
	,`horadeinicio` varchar(5) NOT NULL
	,`horadefin` varchar(5) NOT NULL
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




CREATE TABLE Invitaciones
(
	`idInvitacion` INT NOT NULL AUTO_INCREMENT
	,PRIMARY KEY (idInvitacion)
	,`reunion_id` INT NOT NULL 
	,`usuario_id` INT NOT NULL 
	,`aceptado` INT NOT NULL 
)
ENGINE=INNODB;

-- Create Foreign Keys ----------------------------------------------------------------------------------------------

-- ALTER TABLE Invitados ADD FOREIGN KEY (reunion_id) REFERENCES Reuniones(idEvento);
ALTER TABLE Invitaciones ADD FOREIGN KEY (reunion_id) REFERENCES Reuniones(idEvento);

ALTER TABLE Invitaciones ADD FOREIGN KEY (usuario_id) REFERENCES Usuarios(idUsuario);

ALTER TABLE Reuniones ADD FOREIGN KEY (sala_id) REFERENCES Salas(idSala);
 
ALTER TABLE Eventos ADD FOREIGN KEY (autor_id) REFERENCES Usuarios(idUsuario);

ALTER TABLE EventosPrivados ADD FOREIGN KEY (idEvento) REFERENCES Eventos(idEvento);

ALTER TABLE Reuniones ADD FOREIGN KEY (idEvento) REFERENCES Eventos(idEvento);

-- ALTER TABLE Invitados ADD FOREIGN KEY (invitado_id) REFERENCES Usuarios(idUsuario);

ALTER TABLE Contrasenias ADD FOREIGN KEY (usuario_id) REFERENCES Usuarios(idUsuario);

-- Populate Tables ----------------------------------------------------------------------------------------------

INSERT INTO Usuarios (nombre,apellido,nombreUsuario,idioma) VALUES 
	('pepe','argento','pepea','es'),
	('uncle','tom','uncletom','en'),
	('juan','alvarez','juana','en'),
	('pablo','gimenez','pablog','en'),
	('usuario1','relleno','ur1','es'),
	('usuario2','relleno','ur2','en'),
	('usuario3','relleno','ur3','en'),
	('usuario4','relleno','ur4','en'),
	('usuario5','relleno','ur5','en'),
	('usuario6','relleno','ur6','en'),
	('usuario7','relleno','ur7','en');
	
INSERT INTO Contrasenias (usuario_id ,valor) VALUES 
	('1','elpepe'),
	('2','ilovewar'),
	('3','juanp'),
	('4','pablop'),
	('5','ur'),
	('6','ur'),
	('7','ur'),
	('8','ur'),
	('9','ur'),
	('10','ur'),
	('11','ur');	

INSERT INTO Salas ( descripcion) VALUES 
	('sala oval'),
	('sala grande');
-- Grant privileges ----------------------------------------------------------------------------------------------

GRANT ALL PRIVILEGES ON `agenda`.* TO 'noroot'@'localhost'  IDENTIFIED BY PASSWORD '*B04E11FAAAE9A5A019BAF695B40F3BF1997EB194';




