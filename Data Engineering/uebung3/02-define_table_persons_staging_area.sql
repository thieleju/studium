CREATE SCHEMA staging;

CREATE TABLE staging.persons (
    userid NUMERIC(5)      NOT NULL,
	name VARCHAR (50)      NOT NULL,
	age NUMERIC(3)         NOT NULL,
	street VARCHAR (50)    NOT NULL,
	city VARCHAR (50)      NOT NULL,
	us_state VARCHAR (50)  NOT NULL,
	zipcode VARCHAR (10)   NOT NULL
);