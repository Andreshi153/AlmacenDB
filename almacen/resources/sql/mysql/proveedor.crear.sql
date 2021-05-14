CREATE TABLE proveedor (
    cif VARCHAR(9) PRIMARY KEY,
    tipo_producto ENUM('Normal','Frio','Congelados'),
    FOREIGN KEY (cif) REFERENCES empresa (cif)
)