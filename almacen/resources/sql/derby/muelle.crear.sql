CREATE TABLE muelle (
    id_muelle INT GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1) PRIMARY KEY,
    id_zona CHAR,
    disponible BOOLEAN DEFAULT false,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona)
)