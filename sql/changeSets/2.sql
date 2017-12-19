--liquibase formatted sql

--changeset przemekmagiera:2

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

CREATE TABLE address_person
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

--rollback drop table person_address, address, car, person;