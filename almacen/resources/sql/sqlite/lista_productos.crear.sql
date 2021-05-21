CREATE TABLE lista_productos (
    id_lista_productos INTEGER,
    id_producto INTEGER,
    cantidad INTEGER,
    PRIMARY KEY (id_lista_productos, id_producto),
    FOREIGN KEY (id_producto) REFERENCES producto (id_producto) ON DELETE CASCADE
)