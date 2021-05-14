CREATE TABLE estanteria (
    id_estanteria INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_zona CHAR,
    num_alturas INT DEFAULT 5,
    num_huecos_ocupados INT DEFAULT 0,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona)
)