CREATE SEQUENCE "meal-seq-id"
    INCREMENT BY 1
    MINVALUE 1
    START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE meals (
    id int8 NOT NULL DEFAULT nextval('"meal-seq-id"'::regclass),
    "version" int8 NOT NULL DEFAULT 0,
    name text NOT NULL,
    CONSTRAINT "pk-meals" PRIMARY KEY (id)
);

ALTER TABLE meals
    ADD CONSTRAINT "unq-meals-name" UNIQUE (name);