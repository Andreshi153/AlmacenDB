CREATE TABLE producto_estanteria (
    id_producto INTEGER,
    id_estanteria INTEGER,
    cantidad INTEGER,
    PRIMARY KEY (id_producto, id_estanteria),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON DELETE CASCADE,
    FOREIGN KEY (id_estanteria) REFERENCES estanteria (id_estanteria) ON DELETE CASCADE
)