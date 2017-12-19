--liquibase formatted sql

--changeset przemekmagiera:3

CREATE INDEX person_number_of_children_idx ON person USING btree (number_of_children);

CREATE INDEX address_code_idx ON address USING btree (code);
CREATE INDEX address_number_idx ON address USING btree (number);
CREATE INDEX address_flat_number_idx ON address USING BTREE (flat_number);

CREATE INDEX address_person_address_id_idx ON person_address USING btree (address_id);
CREATE INDEX address_person_person_id_idx ON person_address USING btree (person_id);

CREATE INDEX car_person_id_idx ON car USING btree (person_id);

--rollback drop index person_number_of_children_idx, address_code_idx,address_number_idx,address_flat_number_idx,address_person_address_id_idx,address_person_person_id_idx,car_person_id_idx;