CREATE TABLE proveedor (
    cif TEXT PRIMARY KEY,
    tipo_producto TEXT,
    FOREIGN KEY (cif) REFERENCES empresa (cif) ON DELETE CASCADE
)