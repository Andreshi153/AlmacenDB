CREATE TABLE muelle (
    id_muelle INTEGER PRIMARY KEY AUTOINCREMENT,
    id_zona TEXT,
    disponible BOOLEAN DEFAULT false,
    FOREIGN KEY (id_zona) REFERENCES zona (id_zona) ON DELETE CASCADE
)