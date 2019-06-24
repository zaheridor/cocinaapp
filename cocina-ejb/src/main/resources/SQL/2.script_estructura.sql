--validacion inical
DROP SEQUENCE IF EXISTS mesa_id_seq;
DROP SEQUENCE IF EXISTS factura_id_seq;
DROP SEQUENCE IF EXISTS detfactura_id_seq;
DROP SEQUENCE IF EXISTS cocinero_id_seq;
DROP SEQUENCE IF EXISTS cliente_id_seq;
DROP SEQUENCE IF EXISTS camarero_id_seq;
DROP TABLE IF EXISTS detalle_factura;
DROP TABLE IF EXISTS factura;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS mesa;
DROP TABLE IF EXISTS camarero;
DROP TABLE IF EXISTS cocinero;

--create table COCINERO
CREATE TABLE public.cocinero(
    id integer,
    nombre text,
    primer_apellido text,
    segundo_apellido text,
    CONSTRAINT pk_cocinero PRIMARY KEY (id)
);

ALTER TABLE public.cocinero OWNER to administrator;

--create table CAMARERO
CREATE TABLE public.camarero
(
    id integer,
    nombre text,
    primer_apellido text,
    segundo_apellido text,
    CONSTRAINT pk_camarero PRIMARY KEY (id)
);

ALTER TABLE public.camarero OWNER to administrator;

--create table MESA
CREATE TABLE public.mesa
(
    id integer,
    num_max_comensales integer,
    ubicacion text,
    CONSTRAINT pk_mesa PRIMARY KEY (id)
);

ALTER TABLE public.mesa OWNER to administrator;

--create table CLIENTE
CREATE TABLE public.cliente
(
    id integer,
    nombre text,
    primer_apellido text,
    segundo_apellido text,
    observaciones text,
    CONSTRAINT pk_cliente PRIMARY KEY (id)
);

ALTER TABLE public.cliente OWNER to administrator;

--create table FACTURA
CREATE TABLE public.factura
(
    id integer,
    cliente_id integer,
    camarero_id integer,
    mesa_id integer,
    fecha_factura date,
    CONSTRAINT pk_factura PRIMARY KEY (id),
    CONSTRAINT fk_cliente_factura FOREIGN KEY (cliente_id)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_camarero_factura FOREIGN KEY (camarero_id)
        REFERENCES public.camarero (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_mesa_factura FOREIGN KEY (mesa_id)
        REFERENCES public.mesa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.factura OWNER to administrator;

--create table DETALLE_FACTURA
CREATE TABLE public.detalle_factura
(
    id integer,
    factura_id integer,
    cocinero_id integer,
    plato text,
    importe numeric(18),
    CONSTRAINT pk_detfactura PRIMARY KEY (id),
    CONSTRAINT fk_factura_detfactura FOREIGN KEY (factura_id)
        REFERENCES public.factura (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cocinero_detfactura FOREIGN KEY (cocinero_id)
        REFERENCES public.cocinero (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.detalle_factura OWNER to administrator;

--sequence CAMARERO
CREATE SEQUENCE public.camarero_id_seq;

ALTER SEQUENCE public.camarero_id_seq OWNER TO administrator;

--sequence CLIENTE
CREATE SEQUENCE public.cliente_id_seq;

ALTER SEQUENCE public.cliente_id_seq OWNER TO administrator;

--sequence COCINERO
CREATE SEQUENCE public.cocinero_id_seq;

ALTER SEQUENCE public.cocinero_id_seq OWNER TO administrator;

--sequence DETALLE_FACTURA
CREATE SEQUENCE public.detfactura_id_seq;

ALTER SEQUENCE public.detfactura_id_seq OWNER TO administrator;

--sequence FACTURA
CREATE SEQUENCE public.factura_id_seq;

ALTER SEQUENCE public.factura_id_seq OWNER TO administrator;

--sequence MESA
CREATE SEQUENCE public.mesa_id_seq;

ALTER SEQUENCE public.mesa_id_seq OWNER TO administrator;