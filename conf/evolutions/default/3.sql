# --- !Ups
CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    brand VARCHAR NOT NULL,
    price DECIMAL NOT NULL,
    size VARCHAR NOT NULL,
    kind VARCHAR NOT NULL,
    quantity INTEGER NOT NULL,
    percentage DECIMAL NOT NULL,
    origin VARCHAR NOT NULL,
    status VARCHAR NOT NULL

);


# --- !Downs
DROP TABLE products;
