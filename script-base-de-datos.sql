DROP DATABASE IF EXISTS almacen;
CREATE DATABASE almacen;
USE almacen;

CREATE TABLE producto (
    id_producto VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(30),
    max_producto INT,
    tipo ENUM('Normal', 'Frío', 'Congelados')
);
CREATE TABLE zona (
    id_zona VARCHAR(5) PRIMARY KEY,
    tipo ENUM('Normal', 'Frío', 'Congelados')
);
CREATE TABLE estanteria (
    id_estanteria VARCHAR(5) PRIMARY KEY,
    id_zona VARCHAR(5),
    num_alturas INT,
    num_huecos_ocupados INT,
    CONSTRAINT FOREIGN KEY (id_zona) REFERENCES zona (id_zona)
);
CREATE TABLE producto_estanteria (
    id_producto VARCHAR(10),
    id_estanteria VARCHAR(5),
    cantidad INT,
    PRIMARY KEY (id_producto, id_estanteria),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto),
    FOREIGN KEY (id_estanteria) REFERENCES estanteria (id_estanteria)
);
CREATE TABLE lista_productos (
    id_lista_productos VARCHAR(10),
    id_producto VARCHAR(10),
    cantidad INT,
    PRIMARY KEY (id_lista_productos, id_producto),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
);
CREATE TABLE muelle (
    id_muelle VARCHAR(5) PRIMARY KEY,
    id_zona VARCHAR(5),
    disponible BOOLEAN,
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
CREATE TABLE cliente (
    cif VARCHAR(9) PRIMARY KEY,
    tipo_producto ENUM('Normal','Frío','Congelados'),
    FOREIGN KEY (cif) REFERENCES empresa (cif)
);
CREATE TABLE operacion (
    id_lista_productos VARCHAR(10) PRIMARY KEY,
    id_muelle VARCHAR(5),
    fecha DATE,
    tipo_operacion ENUM('Entrada','Salida'),
    cif VARCHAR(9),
    FOREIGN KEY (id_lista_productos) REFERENCES lista_productos (id_lista_productos),
    FOREIGN KEY (id_muelle) REFERENCES muelle (id_muelle),
    FOREIGN KEY (cif) REFERENCES empresa (cif)
);