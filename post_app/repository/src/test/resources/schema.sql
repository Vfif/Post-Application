CREATE TABLE author
(
    id bigserial,
    name character varying(30)  NOT NULL,
    surname character varying(30)  NOT NULL
);



CREATE TABLE post
(
    id bigserial,
    title character varying(30) NOT NULL,
    short_text character varying(100) NOT NULL,
    full_text character varying(2000) NOT NULL,
    creation_date date NOT NULL,
    modification_date date NOT NULL
);

CREATE TABLE tag
(
    id bigserial,
    name character varying(30)  NOT NULL
);


CREATE TABLE user
(
    id bigserial,
    name character varying(20) NOT NULL,
    surname character varying(20) NOT NULL,
    login character varying(30) NOT NULL,
    password character varying(32) NOT NULL
);


CREATE TABLE post_author
(
    post_id bigint NOT NULL,
    author_id bigint NOT NULL,
);

CREATE TABLE post_tag
(
    post_id bigint NOT NULL,
    tag_id bigint NOT NULL,
);