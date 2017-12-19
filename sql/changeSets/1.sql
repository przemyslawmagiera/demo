--liquibase formatted sql

--changeset przemekmagiera:1

CREATE SEQUENCE hibernate_sequence;

--rollback drop sequence hibernate_sequence;