--liquibase formatted sql

--changeset ryabchikov:1
ALTER TABLE comments ADD COLUMN author_id UUID NOT NULL;