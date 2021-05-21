CREATE TABLE operacion (
    id_lista_productos INT PRIMARY KEY,
    id_muelle INT UNSIGNED,
    fecha DATE,
    tipo_operacion ENUM('Entrada','Salida'),
    cif VARCHAR(9),
    FOREIGN KEY (id_muelle) REFERENCES muelle (id_muelle),
    FOREIGN KEY (cif) REFERENCES empresa (cif)
)