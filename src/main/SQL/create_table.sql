DROP SCHEMA IF EXISTS restdb;
CREATE SCHEMA restdb;
USE restdb;


CREATE TABLE IF NOT EXISTS restaurants
(
  id    VARCHAR(10)  NOT NULL
    PRIMARY KEY,
  name  VARCHAR(50)  NOT NULL,
  addr  VARCHAR(100) NOT NULL,
  image VARCHAR(200) NOT NULL,
  CONSTRAINT restaurants_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS dishes
(
  id   VARCHAR(10)  NOT NULL
    PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  cid  INT          NOT NULL,
  CONSTRAINT dishes_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS dishes_in_restau
(
  did VARCHAR(10) NOT NULL,
  rid VARCHAR(10) NOT NULL,
  CONSTRAINT dishes_in_restau_dishes_did_fk
  FOREIGN KEY (did) REFERENCES Dishes (id),
  CONSTRAINT dishes_in_restau_Restaurants_rid_fk
  FOREIGN KEY (rid) REFERENCES restaurants (id)
)
  ENGINE = InnoDB;

CREATE INDEX dishes_in_restau_dishes_did_fk
  ON dishes_in_restau (did);

CREATE INDEX dishes_in_restau_restaurants_rid_fk
  ON dishes_in_restau (rid);


CREATE TABLE IF NOT EXISTS creditcards
(
  id         VARCHAR(20) NOT NULL
    PRIMARY KEY,
  firstName  VARCHAR(50) NOT NULL,
  lastName   VARCHAR(50) NOT NULL,
  expiration DATE        NOT NULL,
  CONSTRAINT creditcards_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS cuisine
(
  id   INT AUTO_INCREMENT
    PRIMARY KEY,
  type VARCHAR(32) NOT NULL,
  CONSTRAINT cuisine_cid_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS cuisine_in_restau
(
  cid INT         NOT NULL,
  rid VARCHAR(10) NOT NULL,
  CONSTRAINT cuisine_in_restau_cuisine_cid_fk
  FOREIGN KEY (cid) REFERENCES cuisine (id),
  CONSTRAINT cuisine_in_restau_restaurants_rid_fk
  FOREIGN KEY (rid) REFERENCES restaurants (id)
)
  ENGINE = InnoDB;

CREATE INDEX cuisine_in_restau_cuisine_cid_fk
  ON cuisine_in_restau (cid);

CREATE INDEX cuisine_in_restau_restaurants_rid_fk
  ON cuisine_in_restau (rid);

CREATE TABLE IF NOT EXISTS customers
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  firstName VARCHAR(50)  NOT NULL,
  lastName  VARCHAR(50)  NOT NULL,
  ccId      VARCHAR(20)  NOT NULL,
  address   VARCHAR(200) NOT NULL,
  email     VARCHAR(50)  NOT NULL,
  password  VARCHAR(20)  NOT NULL,
  CONSTRAINT customers_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS ratings
(
  rid      VARCHAR(10) NOT NULL,
  rating   FLOAT       NOT NULL,
  numVotes INT         NOT NULL,
  CONSTRAINT ratings_restaurants_rid_fk
  FOREIGN KEY (rid) REFERENCES restaurants (id)
)
  ENGINE = InnoDB;

CREATE INDEX ratings_restaurants_rid_fk
  ON ratings (rid);

CREATE TABLE IF NOT EXISTS sales
(
  id         INT AUTO_INCREMENT
    PRIMARY KEY,
  customerId INT         NOT NULL,
  rid        VARCHAR(10) NOT NULL,
  saleDate   DATE        NOT NULL,
  CONSTRAINT sales_id_uindex
  UNIQUE (id),
  CONSTRAINT sales_customers_id_fk
  FOREIGN KEY (customerId) REFERENCES customers (id),
  CONSTRAINT sales_restaurants_rid_fk
  FOREIGN KEY (rid) REFERENCES restaurants (id)
)
  ENGINE = InnoDB;

CREATE INDEX sales_customers_id_fk
  ON sales (customerId);

CREATE INDEX sales_restaurants_rid_fk
  ON sales (rid);

