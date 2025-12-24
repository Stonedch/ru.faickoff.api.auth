INSERT INTO users_roles (user_id, role_id) SELECT
    (SELECT id FROM users WHERE username = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) SELECT
    (SELECT id FROM users WHERE username = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_USER');
