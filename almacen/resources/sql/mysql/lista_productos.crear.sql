CREATE TABLE lista_productos (
    id_lista_productos INT,
    id_producto INT UNSIGNED,
    cantidad INT,
    PRIMARY KEY (id_lista_productos, id_producto),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto)
)