INSERT INTO ItemType(name)
VALUES('CPU');

INSERT INTO ItemType(name)
VALUES('MBO');

INSERT INTO ItemType(name)
VALUES('RAM');

INSERT INTO ItemType(name)
VALUES('STORAGE');

INSERT INTO ItemType(name)
VALUES('OTHER');



INSERT INTO HARDWARE(code, name, price, typeId, amount)
VALUES('3437932', 'AMD RYZEN 7', 400, 1, 5);

INSERT INTO HARDWARE(code, name, price, typeId, amount)
VALUES( '45343250',  'Intel Core Ultra 7', 250, 1, 4);

INSERT INTO HARDWARE(code, name, price, typeId, amount)
VALUES('4592351',  ' G.Skill Trident Z5 RGB DDR5-6000', 600, 4, 7);

INSERT INTO HARDWARE(code, name, price, typeId, amount)
VALUES( '6789026',  'GeForce RTX 5090', 1900, 2, 12);