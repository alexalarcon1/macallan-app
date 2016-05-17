# --- !Ups
INSERT INTO users (username, password, first_name, last_name, address, phone_number, role) VALUES ('warriorsPG', '1234', 'Steph', 'Curry', '123 Golden St.', '+151612345678', 'manager');

# --- !Downs
DELETE FROM users;