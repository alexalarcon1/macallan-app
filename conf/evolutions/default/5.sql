# --- !Ups
CREATE TABLE IF NOT EXISTS new_orders (
    id BIGSERIAL PRIMARY KEY,
    product_name VARCHAR NOT NULL,
    product_brand VARCHAR NOT NULL,
    product_size DECIMAL NOT NULL,
    product_quantity INTEGER NOT NULL

);


# --- !Downs
DROP TABLE new_orders;
