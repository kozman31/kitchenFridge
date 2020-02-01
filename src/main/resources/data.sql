INSERT INTO roles (role_name, role_id, default_role)
    SELECT role_name, role_id, default_role
    FROM (SELECT 'ROLE_ADMIN' as role_name, 1 as role_id, false as default_role) t
    WHERE NOT EXISTS (SELECT 1 FROM roles r WHERE r.role_name = t.role_name);

INSERT INTO roles (role_name, role_id, default_role)
    SELECT role_name, role_id, default_role
    FROM (SELECT 'ROLE_USER' as role_name, 2 as role_id, true as default_role) t
    WHERE NOT EXISTS (SELECT 1 FROM roles r WHERE r.role_name = t.role_name);
-------------------------------
INSERT INTO authorities (authority_name, authority_id)
    SELECT authority_name, authority_id
    FROM (SELECT 'admin' as authority_name, 1 as authority_id) t
    WHERE NOT EXISTS (SELECT 1 FROM authorities a WHERE a.authority_name = t.authority_name);

INSERT INTO authorities (authority_name, authority_id)
    SELECT authority_name, authority_id
    FROM (SELECT 'user' as authority_name, 2 as authority_id) t
    WHERE NOT EXISTS (SELECT 1 FROM authorities a WHERE a.authority_name = t.authority_name);
-------------------------------
INSERT INTO roles_authorities (role_id, authorities_id)
    SELECT role_id, authorities_id
    FROM (SELECT 1 as role_id, 1 as authorities_id) t
    WHERE NOT EXISTS (SELECT 1 FROM roles_authorities a WHERE a.role_id = t.role_id);

INSERT INTO roles_authorities (role_id, authorities_id)
    SELECT role_id, authorities_id
    FROM (SELECT 2 as role_id, 2 as authorities_id) t
    WHERE NOT EXISTS (SELECT 1 FROM roles_authorities a WHERE a.role_id = t.role_id);