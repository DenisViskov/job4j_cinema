DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS halls;
DROP TABLE IF EXISTS places;

CREATE TABLE places(
    id SERIAL PRIMARY KEY,
    seat INTEGER UNIQUE
);

CREATE TABLE halls(
    id SERIAL PRIMARY KEY,
    row INTEGER,
    seatID INTEGER REFERENCES places (id)
);

CREATE TABLE accounts(
    id SERIAL PRIMARY KEY,
    name TEXT,
    phone TEXT UNIQUE,
    hallsID INTEGER REFERENCES halls (id)
);

INSERT INTO places(seat) VALUES (1);
INSERT INTO places(seat) VALUES (2);
INSERT INTO places(seat) VALUES (3);
INSERT INTO places(seat) VALUES (4);
INSERT INTO places(seat) VALUES (5);
INSERT INTO places(seat) VALUES (6);
INSERT INTO places(seat) VALUES (7);
INSERT INTO places(seat) VALUES (8);
INSERT INTO places(seat) VALUES (9);
INSERT INTO places(seat) VALUES (10);