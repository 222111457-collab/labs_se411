
INSERT INTO product(name, description, price) VALUES
('3d printer', 'A beginner friendly 3d printer', 1840.25),
('PC computer', 'A gaming laptop', 2300.01),
('Mobile phone', 'A 5g smartphone', 850.0),
('Scanner', 'A WIFI scanner', 260.45),
('Mouse', 'An optical mouse', 65.99),
('Keyboard', 'A simple keyboard', 105.99);

INSERT INTO users(username, password)
VALUES (
    'usera',
    '$2a$12$lwZOlgthh6IN1UIye.R4PuM8s34j8oqQ4No.35qPTsygD8qORqg8O'
)
ON CONFLICT (username) DO NOTHING;
