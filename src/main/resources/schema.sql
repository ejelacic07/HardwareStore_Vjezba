CREATE TABLE ItemType
(
    id IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE Hardware
(
    id IDENTITY PRIMARY KEY,
    code   VARCHAR(50)    NOT NULL,
    name   VARCHAR(100)   NOT NULL,
    price  DECIMAL(10, 2) NOT NULL,
    typeId INT            NOT NULL,
    amount INT            NOT NULL,
    FOREIGN KEY (typeId) REFERENCES ItemType (id)
);