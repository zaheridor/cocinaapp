--create role
CREATE ROLE administrator WITH LOGIN PASSWORD 'qwerty123';
ALTER ROLE administrator CREATEDB;

--create database
CREATE DATABASE cocinadb;
GRANT ALL PRIVILEGES ON DATABASE cocinadb TO administrator;

--create table COCINERO
CREATE TABLE public."COCINERO"
(
    "ID" integer,
    "NOMBRE" text,
    "PRIMER_APELLIDO" text,
    "SEGUNDO_APELLIDO" text,
    CONSTRAINT "PK_COCINERO" PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."COCINERO"
    OWNER to administrator;

--create table CAMARERO
CREATE TABLE public."CAMARERO"
(
    "ID" integer,
    "NOMBRE" text,
    "PRIMER_APELLIDO" text,
    "SEGUNDO_APELLIDO" text,
    CONSTRAINT "PK_CAMARERO" PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."CAMARERO"
    OWNER to administrator;

--create table MESA
CREATE TABLE public."MESA"
(
    "ID" integer,
    "NUM_MAX_COMENSALES" integer,
    "UBICACION" text,
    CONSTRAINT "PK_MESA" PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."MESA"
    OWNER to administrator;

--create table CLIENTE
CREATE TABLE public."CLIENTE"
(
    "ID" integer,
    "NOMBRE" text,
    "PRIMER_APELLIDO" text,
    "SEGUNDO_APELLIDO" text,
    "OBSERVACIONES" text,
    CONSTRAINT "PK_CLIENTE" PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."CLIENTE"
    OWNER to administrator;

--create table FACTURA
CREATE TABLE public."FACTURA"
(
    "ID" integer,
    "CLIENTE_ID" integer,
    "CAMARERO_ID" integer,
    "MESA_ID" integer,
    "FECHA_FACTURA" date,
    CONSTRAINT "PK_FACTURA" PRIMARY KEY ("ID"),
    CONSTRAINT "FK_CLIENTE_FACTURA" FOREIGN KEY ("CLIENTE_ID")
        REFERENCES public."CLIENTE" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_CAMARERO_FACTURA" FOREIGN KEY ("CAMARERO_ID")
        REFERENCES public."CAMARERO" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_MESA_FACTURA" FOREIGN KEY ("MESA_ID")
        REFERENCES public."MESA" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."FACTURA"
    OWNER to administrator;

--create table DETALLE_FACTURA
CREATE TABLE public."DETALLE_FACTURA"
(
    "ID" integer,
    "FACTURA_ID" integer,
    "COCINERO_ID" integer,
    "PLATO" text,
    "IMPORTE" numeric(18),
    CONSTRAINT "PK_DETFACTURA" PRIMARY KEY ("ID"),
    CONSTRAINT "FK_FACTURA_DETFACTURA" FOREIGN KEY ("FACTURA_ID")
        REFERENCES public."FACTURA" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT "FK_COCINERO_DETFACTURA" FOREIGN KEY ("COCINERO_ID")
        REFERENCES public."COCINERO" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."DETALLE_FACTURA"
    OWNER to administrator;