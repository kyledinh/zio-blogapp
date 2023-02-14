-- This migration file creates tables for each model defined in our application the rows of which match the model's parameters

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS person 
(
    id         uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name text NOT NULL,
    last_name  text NOT NULL,
    address    text NOT NULL,
    phone      text NOT NULL,
    email      text NOT NULL
);

CREATE TABLE IF NOT EXISTS scrawl 
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    title       text NOT NULL,
    body        text NOT NULL,
    create_date date NOT NULL,
    status      text NOT NULL,
    person_id     uuid NOT NULL REFERENCES person (id) ON DELETE CASCADE
);
