CREATE TABLE operacion (
    id_lista_productos INTEGER PRIMARY KEY,
    id_muelle INTEGER,
    fecha DATE,
    tipo_operacion TEXT,
    cif TEXT,
    FOREIGN KEY (id_muelle) REFERENCES muelle (id_muelle) ON DELETE CASCADE,
    FOREIGN KEY (cif) REFERENCES empresa (cif) ON DELETE CASCADE
)