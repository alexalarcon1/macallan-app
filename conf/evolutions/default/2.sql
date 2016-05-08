-- noinspection SqlNoDataSourceInspectionForFile
# --- !Ups
INSERT INTO users (username, password, first_name, last_name, address, phone_number, role) VALUES ('warriorsPG', '1234', 'Steph', 'Curry', '123 Golden St.', '+151612345678', 'manager');
/*
INSERT INTO users (first_name, last_name, address, phone_number, role) VALUES ('Klay', 'Thompson', '123 Golden St.', '+151612345678', 'employee');
INSERT INTO users (first_name, last_name, address, phone_number, role) VALUES ('Draymond', 'Green', '123 Golden St.', '+151612345678', 'employee');
*/

# --- !Downs
DELETE FROM users;