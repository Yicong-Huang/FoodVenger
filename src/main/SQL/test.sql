CREATE DATABASE restdb;
USE restdb;
CREATE TABLE creditcards
(
  id         INT         NOT NULL
    PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name  VARCHAR(50) NOT NULL,
  expiration DATE        NOT NULL,
  CONSTRAINT creditcards_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE cuisines
(
  id   INT AUTO_INCREMENT
    PRIMARY KEY,
  type VARCHAR(32) NOT NULL,
  CONSTRAINT cuisine_cid_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE cuisine_in_restau
(
  cid INT NOT NULL,
  rid INT NOT NULL,
  CONSTRAINT FK1mx08g9j3fpydu1ln1kryt4cp
  FOREIGN KEY (cid) REFERENCES cuisines (id),
  CONSTRAINT cuisine_in_restau_cuisine_cid_fk
  FOREIGN KEY (cid) REFERENCES cuisines (id)
)
  ENGINE = InnoDB;

CREATE INDEX cuisine_in_restau_cuisine_cid_fk
  ON cuisine_in_restau (cid);

CREATE INDEX cuisine_in_restau_restuarants_rid_fk
  ON cuisine_in_restau (rid);

CREATE TABLE customers
(
  id         INT AUTO_INCREMENT
    PRIMARY KEY,
  active     INT          NULL,
  email      VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  address    VARCHAR(200) NULL,
  ccid       INT          NULL
)
  ENGINE = InnoDB;


CREATE TABLE dishes
(
  id    INT               NOT NULL
    PRIMARY KEY,
  name  VARCHAR(100)      NOT NULL,
  cid   INT               NOT NULL,
  price FLOAT DEFAULT '0' NOT NULL,
  num   INT               NOT NULL,
  CONSTRAINT dishes_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

CREATE TABLE dishes_in_restau
(
  did INT NOT NULL,
  rid INT NOT NULL,
  CONSTRAINT FKr8sn543arh2mrehfsm7qayixr
  FOREIGN KEY (did) REFERENCES dishes (id),
  CONSTRAINT dishes_in_restau_dishes_did_fk
  FOREIGN KEY (did) REFERENCES dishes (id)
)
  ENGINE = InnoDB;

CREATE INDEX dishes_in_restau_dishes_did_fk
  ON dishes_in_restau (did);

CREATE INDEX dishes_in_restau_restuarants_rid_fk
  ON dishes_in_restau (rid);

CREATE TABLE ratings
(
  rid       INT   NOT NULL,
  rating    FLOAT NOT NULL,
  num_votes INT   NOT NULL
)
  ENGINE = InnoDB;

CREATE INDEX ratings_restuarants_rid_fk
  ON ratings (rid);

CREATE TABLE restaurants
(
  id    INT          NOT NULL
    PRIMARY KEY,
  name  VARCHAR(50)  NOT NULL,
  addr  VARCHAR(100) NOT NULL,
  image VARCHAR(200) NULL,
  CONSTRAINT restuarants_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;

ALTER TABLE cuisine_in_restau
  ADD CONSTRAINT FK4jpkj6pd1wm5iw8ugpwsmb8m2
FOREIGN KEY (rid) REFERENCES restaurants (id);

ALTER TABLE cuisine_in_restau
  ADD CONSTRAINT cuisine_in_restau_restuarants_rid_fk
FOREIGN KEY (rid) REFERENCES restaurants (id);

ALTER TABLE dishes_in_restau
  ADD CONSTRAINT FKjqsql0u6lue5x24a9f7m5pp24
FOREIGN KEY (rid) REFERENCES restaurants (id);

ALTER TABLE dishes_in_restau
  ADD CONSTRAINT dishes_in_restau_Restuarants_rid_fk
FOREIGN KEY (rid) REFERENCES restaurants (id);

ALTER TABLE ratings
  ADD CONSTRAINT ratings_restuarants_rid_fk
FOREIGN KEY (rid) REFERENCES restaurants (id);

CREATE TABLE roles
(
  role_id INT AUTO_INCREMENT
    PRIMARY KEY,
  role    VARCHAR(255) NULL
)
  ENGINE = InnoDB;

CREATE TABLE sales
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  cid       INT  NOT NULL,
  did       INT  NOT NULL,
  num       INT,
  sale_date DATE NOT NULL,
  CONSTRAINT sales_id_uindex
  UNIQUE (id),
  CONSTRAINT sales_customers_id_fk
  FOREIGN KEY (cid) REFERENCES customers (id),
  CONSTRAINT sales_dishes_rid_fk
  FOREIGN KEY (did) REFERENCES dishes (id)
)
  ENGINE = InnoDB;

CREATE INDEX sales_customers_id_fk
  ON sales (cid);

CREATE INDEX sales_restuarants_rid_fk
  ON sales (did);

CREATE TABLE user_role
(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o
  FOREIGN KEY (user_id) REFERENCES customers (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y
  FOREIGN KEY (role_id) REFERENCES roles (role_id)
)
  ENGINE = InnoDB;

CREATE INDEX FKa68196081fvovjhkek5m97n3y
  ON user_role (role_id);

