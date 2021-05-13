CREATE TABLE cliente (
    cif VARCHAR(9) PRIMARY KEY,
    porcentaje_desc FLOAT,
    FOREIGN KEY (cif) REFERENCES empresa (cif)
)