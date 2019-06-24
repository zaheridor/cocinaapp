--borrar base de datos y rol
DROP DATABASE IF EXISTS cocinadb;
DROP ROLE IF EXISTS administrator;

--create role
CREATE ROLE administrator WITH LOGIN PASSWORD 'qwerty123';
ALTER ROLE administrator CREATEDB;

--create database
CREATE DATABASE cocinadb;
GRANT ALL PRIVILEGES ON DATABASE cocinadb TO administrator;