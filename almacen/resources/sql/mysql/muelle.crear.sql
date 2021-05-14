CREATE TABLE muelle (
    id_muelle INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_zona CHAR,
    disponible BOOLEAN DEFAULT false,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona)
)