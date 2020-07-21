-- Table Schemas
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS address;

CREATE TABLE address(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    city VARCHAR(15) NOT NULL,
    street VARCHAR(15),
    building_no INT,
    door_no INT
);

CREATE TABLE users(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(20),
    lastname VARCHAR(20),
    username VARCHAR(20) NOT NULL,
    email VARCHAR(20),
    password varchar(100) NOT NULL,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

-- Sample test data insertions
INSERT INTO address ( city, street, building_no, door_no) VALUES    ( 'Malatya', 'Ayvalık Sokagı', 3, 5 ),
                                                                    ( 'Afyon', 'Uluköy Sokagı', 45, 15 ),
                                                                    ( 'Istanbul', 'Senlik Sokagı', 32, 55 ),
                                                                    ( 'Kocaeli', 'Pekcan Sokagı', 67, 12 ),
                                                                    ( 'Urfa', 'Gelgel Sokagı', 4, 56 );


INSERT INTO users (name, lastname, username, password, address_id, email) VALUES    ( 'Admin', 'Test', 'adminuser', 'test', 1  , 'admin@mail.com' ),
                                                                                    ( 'Selim', 'Canlı', 'adminuser', 'test', 2  , null ),
                                                                                    ( 'Ahmet', 'Kaya', 'adminuser', 'test', 5  , 'ahmet@mail.com' ),
                                                                                    ( 'Rıdvan', 'Peknaz', 'adminuser', 'test', 4 , 'ridvan@mail.com' );