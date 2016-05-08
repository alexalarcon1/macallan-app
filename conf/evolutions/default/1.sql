-- noinspection SqlNoDataSourceInspectionForFile
# --- !Ups

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL,
    role VARCHAR NOT NULL
);

# --- !Downs

DROP TABLE "users";
