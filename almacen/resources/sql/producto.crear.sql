CREATE TABLE producto (
    id_producto INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(75),
    precio_unitario DECIMAL(6, 2),
    tipo ENUM('Normal', 'Frio', 'Congelados')
);