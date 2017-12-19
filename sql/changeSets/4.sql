--liquibase formatted sql

--changeset przemekmagiera:4

drop sequence hibernate_sequence;

--rollback create sequence hibernate_sequence;