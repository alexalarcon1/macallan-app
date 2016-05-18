# --- !Ups
INSERT INTO users (username, password, first_name, last_name, address, phone_number, role) VALUES ('admin', 'admin1234', 'Admin', 'User', '123 Golden St.', '+151612345678', 'manager');

# --- !Downs
DELETE FROM users;