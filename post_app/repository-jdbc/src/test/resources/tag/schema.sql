CREATE SCHEMA task2;

CREATE TABLE task2.tag
(
    id bigint NOT NULL DEFAULT NEXTVAL('task2.tag_id'),
    name character varying(30)  NOT NULL
);

CREATE SEQUENCE task2.tag_id
    INCREMENT 1
    START 4
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
