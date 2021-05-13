CREATE TABLE producto (
    id_producto INT GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1) PRIMARY KEY,
    nombre VARCHAR(75),
    precio_unitario DECIMAL(6, 2),
    tipo VARCHAR(15)
)