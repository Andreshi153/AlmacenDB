CREATE TABLE proveedor (
    cif VARCHAR(9) PRIMARY KEY,
    tipo_producto VARCHAR(15),
    FOREIGN KEY (cif) REFERENCES empresa (cif)
)