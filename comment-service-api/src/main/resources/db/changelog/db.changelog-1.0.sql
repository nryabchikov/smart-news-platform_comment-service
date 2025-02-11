--liquibase formatted sql

--changeset ryabchikov:1
CREATE TABLE comments
(
    id      UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    text    TEXT                     NOT NULL,
    time    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    news_id UUID                     NOT NULL
);