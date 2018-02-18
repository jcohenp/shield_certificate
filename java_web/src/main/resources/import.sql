-- the password hash is generated by BCrypt Calculator Generator(https://www.dailycred.com/article/bcrypt-calculator)
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (1, 'user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Fan', 'Jin', 'user@example.com', true, '2017-10-01 21:58:58.508-07');
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (2, 'admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jing', 'Xiao', 'admin@example.com',true, '2017-10-01 18:57:58.508-07');
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (3, 'user1', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jing', 'Xiao', 'admin@example.com',true, '2017-10-01 18:57:58.508-07');
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (4, 'user2', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jing', 'Xiao', 'admin@example.com',true, '2017-10-01 18:57:58.508-07');
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (5, 'user3', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jing', 'Xiao', 'admin@example.com',true, '2017-10-01 18:57:58.508-07');
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (6, 'user4', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jing', 'Xiao', 'admin@example.com',true, '2017-10-01 18:57:58.508-07');
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (7, 'user5', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jing', 'Xiao', 'admin@example.com',true, '2017-10-01 18:57:58.508-07');
INSERT INTO USERS (id, username, password, first_name, last_name, email, enabled, last_password_reset_date) VALUES (8, 'user6', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Jing', 'Xiao', 'admin@example.com',true, '2017-10-01 18:57:58.508-07');

INSERT INTO AUTHORITY (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (user_id, authority_id) VALUES (2, 2);