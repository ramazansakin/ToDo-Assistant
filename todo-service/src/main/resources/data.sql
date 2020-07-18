-- Table Schemas
DROP TABLE IF EXISTS todo;

CREATE TABLE todo(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    headline VARCHAR(50) NOT NULL,
    details VARCHAR(250)
);

INSERT INTO todo (headline, details) VALUES ('Refactor', 'I need to make a refactor on my todo-app'),
                                            ('Buy something in morning', null),
                                            ('Call mom', 'I have to call momy at wednesday'),
                                            ('Visit the site', 'The site may be really useful for me , www.blablabla.com');
