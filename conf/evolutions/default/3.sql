-- noinspection SqlNoDataSourceInspectionForFile
# --- !Ups
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    brand VARCHAR NOT NULL,
    price DECIMAL NOT NULL,
    size DECIMAL NOT NULL,
    kind VARCHAR NOT NULL,
    quantity INTEGER NOT NULL,
    percentage DECIMAL NOT NULL,
    origin VARCHAR NOT NULL,
    status VARCHAR NOT NULL

);

INSERT INTO products (name, brand, price, size, kind, quantity, percentage, origin, status) VALUES ('Honey Jack', 'Jack Daniels', 16.00, '1.75L', 'Whiskey', 20, .35, 'American', 'Available');


# --- !Downs
DROP TABLE products;
DELETE FROM products;
