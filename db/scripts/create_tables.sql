DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS halls CASCADE;

CREATE TABLE accounts(
    id SERIAL PRIMARY KEY,
    name TEXT,
    phone TEXT UNIQUE
);

CREATE TABLE halls(
    id SERIAL PRIMARY KEY,
    row INTEGER ,
    seat INTEGER ,
    accountID INTEGER REFERENCES accounts (id)
);

INSERT INTO halls(row,seat) VALUES (1,1);
INSERT INTO halls(row,seat) VALUES (1,2);
INSERT INTO halls(row,seat) VALUES (1,3);
INSERT INTO halls(row,seat) VALUES (1,4);
INSERT INTO halls(row,seat) VALUES (1,5);

INSERT INTO halls(row,seat) VALUES (2,1);
INSERT INTO halls(row,seat) VALUES (2,2);
INSERT INTO halls(row,seat) VALUES (2,3);
INSERT INTO halls(row,seat) VALUES (2,4);
INSERT INTO halls(row,seat) VALUES (2,5);

INSERT INTO halls(row,seat) VALUES (3,1);
INSERT INTO halls(row,seat) VALUES (3,2);
INSERT INTO halls(row,seat) VALUES (3,3);
INSERT INTO halls(row,seat) VALUES (3,4);
INSERT INTO halls(row,seat) VALUES (3,5);

INSERT INTO halls(row,seat) VALUES (4,1);
INSERT INTO halls(row,seat) VALUES (4,2);
INSERT INTO halls(row,seat) VALUES (4,3);
INSERT INTO halls(row,seat) VALUES (4,4);
INSERT INTO halls(row,seat) VALUES (4,5);

INSERT INTO halls(row,seat) VALUES (5,1);
INSERT INTO halls(row,seat) VALUES (5,2);
INSERT INTO halls(row,seat) VALUES (5,3);
INSERT INTO halls(row,seat) VALUES (5,4);
INSERT INTO halls(row,seat) VALUES (5,5);