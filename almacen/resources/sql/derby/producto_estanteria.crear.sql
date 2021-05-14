CREATE TABLE producto_estanteria (
    id_producto INT,
    id_estanteria INT,
    cantidad INT,
    PRIMARY KEY (id_producto, id_estanteria),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto),
    FOREIGN KEY (id_estanteria) REFERENCES estanteria (id_estanteria)
)