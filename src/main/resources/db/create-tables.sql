SET SESSION AUTHORIZATION demo;

CREATE TABLE person
(
  person_id bigint PRIMARY KEY,
  eye_color character varying(255),
  last_name character varying(255),
  name character varying(255),
  number_of_children integer,
  sex character varying(255)
);

CREATE TABLE address
(
  address_id bigint PRIMARY KEY,
  city character varying(255),
  code character varying(255),
  "number" character varying(255),
  street character varying(255)
);

CREATE TABLE person_address
(
  person_id bigint REFERENCES person (person_id) ON UPDATE CASCADE ON DELETE CASCADE,
  address_id bigint REFERENCES address (address_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE car
(
  car_id bigint PRIMARY KEY,
  plate_number character varying(255),
  person_id bigint REFERENCES person(person_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE INDEX person_id_idx ON person USING btree (person_id);
CREATE INDEX child_number_idx ON person USING btree (number_of_children);

CREATE INDEX address_id_idx ON address USING btree (address_id);
CREATE INDEX city_idx ON address USING btree (city);
CREATE INDEX code_idx ON address USING btree (code);
CREATE INDEX number_idx ON address USING btree (number);
CREATE INDEX street_idx ON address USING btree (street);

CREATE INDEX address_id_pa_idx ON person_address USING btree (address_id);
CREATE INDEX person_id_pa_idx ON person_address USING btree (person_id);

CREATE INDEX car_id_idx ON car USING btree (car_id);
CREATE INDEX plate_num_idx ON car USING btree (plate_number);
CREATE INDEX person_id_c_idx ON car USING btree (person_id);