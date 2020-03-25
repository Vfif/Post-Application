CREATE SCHEMA task2;

CREATE TABLE task2.author
(
    id bigint NOT NULL DEFAULT NEXTVAL('task2.author_id'),
    name character varying(30)  NOT NULL,
    surname character varying(30)  NOT NULL
);

CREATE SEQUENCE task2.author_id
    INCREMENT 1
    START 9
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
