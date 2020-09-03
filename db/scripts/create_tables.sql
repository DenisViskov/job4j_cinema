DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS rows;
DROP TABLE IF EXISTS halls;

CREATE TABLE accounts(
    id SERIAL PRIMARY KEY,
    name TEXT,
    phone TEXT UNIQUE
);

CREATE TABLE seats(
    id SERIAL PRIMARY KEY,
    seat INTEGER UNIQUE
);

CREATE TABLE rows(
    id SERIAL PRIMARY KEY,
    row INTEGER UNIQUE
);

CREATE TABLE halls(
    id SERIAL PRIMARY KEY,
    rowID INTEGER REFERENCES rows (id),
    seatID INTEGER REFERENCES seats (id),
    accountID INTEGER REFERENCES accounts (id)
);

INSERT INTO seats(seat) VALUES (1);
INSERT INTO seats(seat) VALUES (2);
INSERT INTO seats(seat) VALUES (3);
INSERT INTO seats(seat) VALUES (4);
INSERT INTO seats(seat) VALUES (5);
INSERT INTO seats(seat) VALUES (6);
INSERT INTO seats(seat) VALUES (7);
INSERT INTO seats(seat) VALUES (8);
INSERT INTO seats(seat) VALUES (9);
INSERT INTO seats(seat) VALUES (10);

INSERT INTO rows(row) VALUES (1);
INSERT INTO rows(row) VALUES (2);
INSERT INTO rows(row) VALUES (3);
INSERT INTO rows(row) VALUES (4);
INSERT INTO rows(row) VALUES (5);
