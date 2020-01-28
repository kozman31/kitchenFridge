INSERT INTO roles (role_name, role_id)
SELECT 'ROLE_ADMIN', 1
WHERE NOT EXISTS (SELECT * FROM roles WHERE role_name='ROLE_ADMIN');

INSERT INTO roles (role_name, role_id)
SELECT 'ROLE_USER', 2
WHERE NOT EXISTS (SELECT * FROM roles WHERE role_name='ROLE_USER');

INSERT INTO authorities (authority_name, authority_id)
SELECT 'admin', 1
WHERE NOT EXISTS (SELECT * FROM authorities WHERE authority_name='admin');

INSERT INTO authorities (authority_name, authority_id)
SELECT 'user', 2
WHERE NOT EXISTS (SELECT * FROM authorities WHERE authority_name='user');