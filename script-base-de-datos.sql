DROP DATABASE IF EXISTS almacen;
CREATE DATABASE almacen;
USE almacen;

-- Creación de las tablas
CREATE TABLE producto (
    id_producto INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(75),
    precio_unitario DECIMAL(6, 2),
    tipo ENUM('Normal', 'Frio', 'Congelados')
);
CREATE TABLE zona (
    id_zona CHAR PRIMARY KEY,
    tipo ENUM('Normal', 'Frio', 'Congelados','Zona de picking')
);
CREATE TABLE estanteria (
    id_estanteria INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_zona CHAR,
    num_alturas INT DEFAULT 5,
    num_huecos_ocupados INT DEFAULT 0,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona)
);
CREATE TABLE producto_estanteria (
    id_producto INT UNSIGNED,
    id_estanteria INT UNSIGNED,
    cantidad INT,
    PRIMARY KEY (id_producto, id_estanteria),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto),
    FOREIGN KEY (id_estanteria) REFERENCES estanteria (id_estanteria)
);
CREATE TABLE lista_productos (
    id_lista_productos INT UNSIGNED,
    id_producto INT UNSIGNED,
    cantidad INT,
    PRIMARY KEY (id_lista_productos, id_producto),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);
CREATE TABLE muelle (
    id_muelle INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_zona CHAR,
    disponible BOOLEAN DEFAULT false,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona)
);
CREATE TABLE empresa (
    cif VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(50),
    direccion  VARCHAR(100),
    telefono VARCHAR(11),
    correo VARCHAR(50)
);
CREATE TABLE cliente (
    cif VARCHAR(9) PRIMARY KEY,
    porcentaje_desc FLOAT,
    FOREIGN KEY (cif) REFERENCES empresa (cif)
);
CREATE TABLE proveedor (
    cif VARCHAR(9) PRIMARY KEY,
    tipo_producto ENUM('Normal','Frio','Congelados'),
    FOREIGN KEY (cif) REFERENCES empresa (cif)
);
CREATE TABLE operacion (
    id_lista_productos INT UNSIGNED PRIMARY KEY,
    id_muelle INT UNSIGNED,
    fecha DATE,
    tipo_operacion ENUM('Entrada','Salida'),
    cif VARCHAR(9),
    FOREIGN KEY (id_lista_productos) REFERENCES lista_productos (id_lista_productos),
    FOREIGN KEY (id_muelle) REFERENCES muelle (id_muelle),
    FOREIGN KEY (cif) REFERENCES empresa (cif)
);

-- Inserciones
-- Producto
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Botella de agua 500mL', 0.50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Peluche de oso Disney 16x12x10cm', 12, 'Normal');           
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Coche Hot Wheels', 4.95, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Ambrosias Tirma Pack 37', 3.69, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Platos de cerámica', 5.00, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Vasos de cristal', 5.00, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Lasagna de atun', 2.79, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Lapices Alpino', 1.67, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Munchitos', 1.00, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Lego Piratas del Caribe Juego PSP', 49.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Shrek la pelicula DVD', 99.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Disco duro Seagate BarraCuda 3.5" 1TB SATA3', 50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Puerta de abeto maciza', 109, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Grinder de yerbas 4 compartimentos', 4.20, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Nintendo Switch Lite', 199.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Camiseta Oasis Talla L', 25.34, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Queso Parmesano', 3.69, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Yogur estilo griego con stracciatella 4x125g.', 2.05, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Falafel tradicional con Tofu Biológico 250g.', 5.78, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Paquete de cartas de Yu-Gi-Oh!', 7.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Pokemon Rojo', 26.04, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Boligrafos BIC', 1.25, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Auriculares inalambricos', 75, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Teclado Logitech G213 Prodigy Teclado Gaming', 50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Cubitos de hielo', 2.00, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Helados Magnum almendrado', 3.00, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Playstation 2', 150, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Crash of the Titans PS2', 50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Televisor TCL 32 Smart TV Android S615 HD negro', 179, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Super Smash Bros Melee', 69, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Danacol', 5.60, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Langostinos', 7.89, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Dancing with the Devil: The Art of Starting Over Demi Lovato CD', 16.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Mortadelo y Filemon La Pelicula', 5.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bolsa de guisantes', 3,20, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Chips Ahoy', 2,74, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Oreos', 2,15, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Llave inglesa', 5.49, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Tarta helada', 10.50, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Clipper de fresa 1,5 L', 1.00, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Volkswagen Beetle 1200', 11490, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Paquete de cartas Magic', 10.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Procesador Intel Core i5-7400', 129.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Disco duro portatil 1 TB ', 39.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Hamburguesa vacuno ', 2.96, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Tocadiscos', 55.89, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Disco Back in Black de AC/DC', 21.56, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Megaman 2', 100, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Guagua Titsa', 150000,'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Mantequilla La Irlandesa', 1.59, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Jugo Don Simon de Naranja', 1.23, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Robot Aspirador', 159.89, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Cepillo de dientes', 1.52, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Pasta de dientes Colgate', 1.33, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Muñeca Pinypon', 5.99,  'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Palitos de cangrejo', 2.99, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bicicleta de montaña', 250, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Almohadas viscoelàsticas', 50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Figura de Alien tamaño real', 2500, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('E.T la pelicula', 15.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Berlinas', 2, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Croissant ', 2, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Botella de vino Dime Que si', 1.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Silla de madera', 57, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Xiaomi Redmi S2', 150, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Pendientes Oro Rosa', 12.63, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bidon de plastico', 10.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Marco de madera', 5,99,'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Hacha larga', 30.50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Yogur de coco', 2.13, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Lomo adobado', 7.99, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bombilla', 5.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Pechuga de pollo', 2.99, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Marihuana terapeutica 2g', 10, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Macarrones', 1.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Pulpo', 27.99, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Chicharro', 6.99, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bolsa de papas bonitas', 10, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Camisetas FUCK YOU', 7.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Fanta de naranja 33cl', 0.69, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Chorizo revilla', 1.99, 'frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Balon de futbol', 15.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bolsa de garbanzos', 1.50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Destornillador de punta plana', 2.50, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Destornillador de punta de estrella', 2.51,'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Nintendo 64', 64, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Super Mario 64', 64, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Martillo de carpintero', 42.89, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Cables HDMI de 20m', 39.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Los cazafantasmas', 15.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Patinete electrico', 259.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Botella de te de melocoton', 0.99, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bolsas de te marihuana', 2.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Tazas de cafe', 2.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Cesped artificial 40mm2', 3.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bote de salsa de tomate', 1.99, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bote de mojo picon', 2.30, 'Frio');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Muslos de pollo', 4.99, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Bolsa de gueldes', 5.99, 'Congelados');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Paquete de choco krispies', 3.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Magdalenas', 1.99, 'Normal');
INSERT INTO producto (nombre, precio_unitario, tipo) VALUES ('Paquete de Conguitos', 2.99, 'Normal');

-- Zona
INSERT INTO zona (id_zona, tipo) values('A', 'Normal');
INSERT INTO zona (id_zona, tipo) values('B', 'Normal');
INSERT INTO zona (id_zona, tipo) values('C', 'Congelados');
INSERT INTO zona (id_zona, tipo) values('D', 'Frio');
INSERT INTO zona (id_zona, tipo) values('E', 'Zona de picking');
INSERT INTO zona (id_zona, tipo) values('F', 'Normal');

-- Estanteria
-- Procedimiento para generar estanterias
DROP PROCEDURE IF EXISTS pa_insert_estanterias;
DELIMITER //
CREATE PROCEDURE pa_insert_estanterias(IN num_estanterias INT, IN zona_estanteria CHAR)
BEGIN
    SET @contador = 1;
    WHILE (@contador <= num_estanterias) DO
        INSERT INTO estanteria(id_zona) VALUES (zona_estanteria);
        SET @contador = @contador + 1;
    END WHILE;
END //
DELIMITER ;

CALL pa_insert_estanterias(10, 'A');
CALL pa_insert_estanterias(10, 'B');
CALL pa_insert_estanterias(10, 'C');
CALL pa_insert_estanterias(10, 'D');
CALL pa_insert_estanterias(10, 'F');

-- Trigger de los huecos de la estanteria
DROP TRIGGER IF EXISTS after_producto_estanteria_insert;
DELIMITER //
CREATE TRIGGER after_producto_estanteria_insert
    AFTER INSERT ON producto_estanteria FOR EACH ROW
BEGIN
    UPDATE estanteria SET num_huecos_ocupados = (SELECT COUNT(*) FROM producto_estanteria 
    WHERE estanteria.id_estanteria = producto_estanteria.id_estanteria) 
    WHERE new.id_estanteria = estanteria.id_estanteria;
END //
DELIMITER ;

-- Trigger de la cantidad de productos
/* 
TODO: Documentar
*/
DROP TRIGGER IF EXISTS after_operacion_cantidad;
DELIMITER //
CREATE TRIGGER after_operacion_cantidad
    AFTER INSERT ON operacion FOR EACH ROW
BEGIN
    IF (new.tipo_operacion = 'Entrada') THEN
        SET @operador = 1;
    ELSE
        SET @operador = -1;
    END IF;
    SET @fila = 1;
    SET @rowCount = (SELECT COUNT(*) FROM producto);
    WHILE (@fila <= @rowCount) DO
        IF ((SELECT id_producto FROM lista_productos NATURAL JOIN operacion WHERE id_producto = @fila) = @fila) THEN
            UPDATE producto_estanteria SET cantidad = cantidad + (@operador * (SELECT cantidad FROM lista_productos 
            WHERE new.id_lista_productos = lista_productos.id_lista_productos AND id_producto = @fila)) 
            WHERE id_producto = @fila;
        END IF;
        SET @fila = @fila + 1;
    END WHILE;
END //
DELIMITER ;

-- Producto estanteria
/*
TODO: INSERCIONES
*/
INSERT INTO producto_estanteria VALUES (1, 1, 400);
INSERT INTO producto_estanteria VALUES (2, 3, 200);

-- Lista producto
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (1, 1, 4);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (2, 3, 4);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (5, 31, 70);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (8, 34, 51);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (1, 24, 4);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (2, 12, 10);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (3, 90, 45);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (9, 42, 68);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (9, 14, 2);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (4, 6, 32);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (7, 23, 12);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (1, 1, 4);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (3, 17, 9);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (5, 5, 5);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (2, 2, 21);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (1, 86, 34);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (8, 69, 34);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (6, 43, 5);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (3, 55, 6);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (7, 1, 6);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (6, 9, 12);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (2, 83, 56);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (7, 17 49);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (2, 33, 5);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (4, 65, 10);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (3, 42, 19);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (4, 72, 23);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (1, 5, 23);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (6, 32, 5);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (5, 23, 2);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (7, 67, 12);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (1, 90, 5);
INSERT INTO lista_productos (id_lista_productos, id_producto, cantidad) VALUES (2, 100, 14);


-- Muelle
INSERT INTO muelle (id_zona, disponible) VALUES ('A', false);
INSERT INTO muelle (id_zona, disponible) VALUES ('A', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('A', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('B', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('B', false);
INSERT INTO muelle (id_zona, disponible) VALUES ('B', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('C', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('C', false);
INSERT INTO muelle (id_zona, disponible) VALUES ('C', false);
INSERT INTO muelle (id_zona, disponible) VALUES ('D', false);
INSERT INTO muelle (id_zona, disponible) VALUES ('D', false);
INSERT INTO muelle (id_zona, disponible) VALUES ('D', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('E', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('E', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('E', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('F', false);
INSERT INTO muelle (id_zona, disponible) VALUES ('F', true);
INSERT INTO muelle (id_zona, disponible) VALUES ('F', false);

-- Empresa
INSERT INTO empresa VALUES ('A11111111', 'Papas Aureliano', 'Poligono Industrial La Ganania, 8', '555-555-555', 'papasaureliano@mail.com');
INSERT INTO empresa VALUES ('A22222222', 'Congelados Manolo', 'Calle Winona Ryder, 5', '666-555-555', 'congeladosmanolo@mail.com');
INSERT INTO empresa VALUES ('A33333333', 'Discoteca Brawl', 'Avenida Estrella, 4', '777-555-555', 'discotecabrawl@mail.com');
INSERT INTO empresa VALUES ('A44444444', 'Dispensario Laguna Verde', 'Calle Laguna, 420', '420-420-420', 'dispensariolagunaverde@mail.com');
INSERT INTO empresa VALUES ('A55555555', 'Volquetronic', 'Calle Retama, 6', '999-555-555', 'volquetronic@mail.com');
INSERT INTO empresa VALUES ('A66666666', 'Videojuegos Ernesto', 'Avenida Limon, 15', '555-666-555', 'videojuegosernesto@mail.com');
INSERT INTO empresa VALUES ('A77777777', 'Autobar Manolo', 'Pollich Park, 12', '555-777-555', 'autobarmanolo@mail.com');
INSERT INTO empresa VALUES ('A88888888', 'Tecnoloco', 'Simone Keys, 420', '555-888-555', 'tecnoloco@mail.com');
INSERT INTO empresa VALUES ('A99999999', 'Pizzeria Maxipizza', 'Ernestine Street, 319', '555-999-555', 'pizzeriamaxipizza@mail.com');
INSERT INTO empresa VALUES ('B11111111', 'Zapateria El Dragon', 'Emie Oval, s/n', '555-555-666', 'zapateriaeldragon@mail.com');
INSERT INTO empresa VALUES ('B22222222', 'Electrodomesticos Fermin', 'Quitzon Via, 65', '555-555-777', 'electrodomesticosfermin@mail.com');
INSERT INTO empresa VALUES ('B33333333', 'Tienda de ropa Maria', 'Fahey Extensions, 98', '555-555-888', 'tiendaderopamaria@mail.com');
INSERT INTO empresa VALUES ('B44444444', 'Merceria Adela', 'Gabrielle Groves, 17', '555-555-999', 'merceriaadela@mail.com');
INSERT INTO empresa VALUES ('B55555555', 'KMB FAST FOOD', 'Rowan Gardens, 54', '111-555-555', 'kmbfastfood@mail.com');
INSERT INTO empresa VALUES ('B66666666', 'Ferreteria Aurelio', 'Bobby Center, 145', '555-111-555', 'ferreteriaaurelio@mail.com');
INSERT INTO empresa VALUES ('B77777777', 'Libreria Martin', 'Angus Locks, 56', '555-555-111', 'libreriamartin@mail.com');
INSERT INTO empresa VALUES ('B88888888', 'Tienda de pesca Salome', 'Imogene Hill, 45', '222-555-555', 'tiendadepescasalome@mail.com');
INSERT INTO empresa VALUES ('B99999999', 'Gestoria Wally', 'Feeney Island, 56', '555-222-555', 'GestoriaWally@mail.com');
INSERT INTO empresa VALUES ('C11111111', 'ElProcesador', 'Tyson Circle, 42', '555-555-222', 'ElProcesador@mail.com');
INSERT INTO empresa VALUES ('C22222222', 'Tabacocaro.com', 'Sydnee Trail, 67', '333-555-555', 'Tabacocaro@mail.com');

-- Cliente
INSERT INTO cliente VALUES ('A33333333', 1);
INSERT INTO cliente VALUES ('A55555555', 0.87);
INSERT INTO cliente VALUES ('A66666666', 0.95);
INSERT INTO cliente VALUES ('A77777777', 0.98);
INSERT INTO cliente VALUES ('A88888888', 0.52);
INSERT INTO cliente VALUES ('A99999999', 0.67);
INSERT INTO cliente VALUES ('C11111111', 0.94);
INSERT INTO cliente VALUES ('C22222222', 0.91);

-- Proveedor
INSERT INTO proveedor VALUES ('A11111111', 'Normal');
INSERT INTO proveedor VALUES ('A22222222', 'Congelados');
INSERT INTO proveedor VALUES ('A44444444', 'Normal');
INSERT INTO proveedor VALUES ('B11111111', 'Frio');
INSERT INTO proveedor VALUES ('B22222222', 'Normal');
INSERT INTO proveedor VALUES ('B33333333', 'Normal');
INSERT INTO proveedor VALUES ('B44444444', 'Normal');
INSERT INTO proveedor VALUES ('B55555555', 'Frio');
INSERT INTO proveedor VALUES ('B66666666', 'Normal');
INSERT INTO proveedor VALUES ('B77777777', 'Normal');
INSERT INTO proveedor VALUES ('B88888888', 'Congelados');
INSERT INTO proveedor VALUES ('B99999999', 'Frio');

-- Operacion
INSERT INTO operacion (id_lista_productos, id_muelle, fecha, tipo_operacion, cif) VALUES (1, 3, '2020-11-14', 'Entrada', 'B66666666');
INSERT INTO operacion (id_lista_productos, id_muelle, fecha, tipo_operacion, cif) VALUES (2, 15, '2021-02-15', 'Salida', 'C22222222');

-- Creacion de vistas
DROP VIEW IF EXISTS vista_producto_lista, vista_proveedor, vista_cliente;
CREATE VIEW vista_producto_lista AS SELECT id_lista_productos AS 'ID lista productos', id_producto AS 'ID producto', 
        nombre, cantidad, tipo FROM producto NATURAL JOIN lista_productos;
CREATE VIEW vista_proveedor AS SELECT * FROM empresa NATURAL JOIN proveedor;
CREATE VIEW vista_cliente AS SELECT * FROM empresa NATURAL JOIN cliente;