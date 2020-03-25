CREATE SCHEMA task2;
CREATE TABLE task2.post
(
    id bigint NOT NULL DEFAULT nextval('task2.post_id'),
    tittle character varying(30) NOT NULL,
    short_text character varying(100) NOT NULL,
    full_text character varying(2000) NOT NULL,
    creation_date date NOT NULL,
    modification_date date NOT NULL
);

CREATE SEQUENCE task2.post_id
    INCREMENT 1
    START 7
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
