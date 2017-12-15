SET SESSION AUTHORIZATION demo;

BEGIN;

CREATE SEQUENCE hibernate_sequence;

CREATE TABLE person
(
  person_id bigint PRIMARY KEY,
  name varchar(255) NOT NULL ,
  email varchar(255) NOT NULL UNIQUE,
  eye_color varchar(5) NOT NULL ,
  sex varchar(1) NOT NULL,
  number_of_children integer NOT NULL
);

CREATE TABLE address
(
  address_id bigint PRIMARY KEY,
  street varchar(255) NOT NULL,
  "number" varchar(15) NOT NULL ,
  flat_number varchar(15),
  code varchar(255)NOT NULL ,
  city varchar(255) NOT NULL
);

CREATE TABLE person_address
(
  person_id bigint NOT NULL REFERENCES person (person_id) ON DELETE CASCADE,
  address_id bigint NOT NULL REFERENCES address (address_id) ON DELETE CASCADE
);

CREATE TABLE car
(
  car_id bigint PRIMARY KEY,
  plate_number varchar(255) NOT NULL UNIQUE,
  person_id bigint REFERENCES person(person_id) ON DELETE CASCADE
);

CREATE INDEX person_number_of_children_idx ON person USING btree (number_of_children);

CREATE INDEX address_code_idx ON address USING btree (code);
CREATE INDEX address_number_idx ON address USING btree (number);
CREATE INDEX address_flat_number_idx ON address USING BTREE (flat_number);

CREATE INDEX address_person_address_id_idx ON person_address USING btree (address_id);
CREATE INDEX address_person_person_id_idx ON person_address USING btree (person_id);

CREATE INDEX car_person_id_idx ON car USING btree (person_id);

COMMIT;