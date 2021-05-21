CREATE TABLE estanteria (
    id_estanteria INTEGER PRIMARY KEY AUTOINCREMENT,
    id_zona TEXT,
    num_alturas INTEGER DEFAULT 5,
    num_huecos_ocupados INTEGER DEFAULT 0,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona) ON DELETE CASCADE
)