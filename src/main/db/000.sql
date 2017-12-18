--liquibase formatted sql

--changeset przemek-magiera:1

CREATE SEQUENCE hibernate_sequence;

--rollback drop sequence hibernate_sequence;