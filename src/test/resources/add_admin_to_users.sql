INSERT INTO users(id, created_at, last_modified_at, status, email, first_name, last_name, password, username)
VALUES (1, 1657622362895, 1657622362895, 'ACTIVE', 'test@gmail.com', 'Test name', 'Test last name',
        '$2a$04$AAWHl5T71PUkmqK/oACKoulyWXmIaVMiwv10OR/6JUoS9EYt49t0m', 'admin');
INSERT INTO user_role(user_id, roles)
VALUES (1, 'USER'), (1, 'ADMIN')