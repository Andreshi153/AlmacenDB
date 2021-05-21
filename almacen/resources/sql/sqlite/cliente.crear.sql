CREATE TABLE cliente (
    cif TEXT PRIMARY KEY,
    porcentaje_desc REAL,
    FOREIGN KEY (cif) REFERENCES empresa (cif) ON DELETE CASCADE
)