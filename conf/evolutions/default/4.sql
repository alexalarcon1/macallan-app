# --- !Ups
CREATE TABLE IF NOT EXISTS orders (
    id BIGSERIAL PRIMARY KEY,
    userId BIGINT NOT NULL,
    order_date DATE NOT NULL,
    ordered_at TIMESTAMP,
    status VARCHAR NOT NULL

);

CREATE TABLE IF NOT EXISTS ordered_products (
  id BIGSERIAL PRIMARY KEY,
  productId BIGINT NOT NULL,
  orderId BIGINT
);


# --- !Downs
DROP TABLE orders;
DROP TABLE ordered_products;