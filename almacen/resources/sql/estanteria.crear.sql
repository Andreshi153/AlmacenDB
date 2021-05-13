CREATE TABLE estanteria (
    id_estanteria INT GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1) PRIMARY KEY,
    id_zona CHAR,
    num_alturas INT DEFAULT 5,
    num_huecos_ocupados INT DEFAULT 0,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona)
)