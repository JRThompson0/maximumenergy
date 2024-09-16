-- Insert 'ROLE_ADMIN' if it does not already exist
INSERT INTO maxxenergy.roles (role_Name)
SELECT 'ROLE_ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM maxxenergy.roles WHERE role_Name = 'ROLE_ADMIN'
);

-- Insert 'ROLE_EMPLOYEE' if it does not already exist
INSERT INTO maxxenergy.roles (role_Name)
SELECT 'ROLE_EMPLOYEE'
WHERE NOT EXISTS (
    SELECT 1 FROM maxxenergy.roles WHERE role_Name = 'ROLE_EMPLOYEE'
);

-- Insert 'ROLE_USER' if it does not already exist
INSERT INTO maxxenergy.roles (role_Name)
SELECT 'ROLE_USER'
WHERE NOT EXISTS (
    SELECT 1 FROM maxxenergy.roles WHERE role_Name = 'ROLE_USER'
);

-- Insert 'ROLE_GUEST' if it does not already exist
INSERT INTO maxxenergy.roles (role_Name)
SELECT 'ROLE_GUEST'
WHERE NOT EXISTS (
    SELECT 1 FROM maxxenergy.roles WHERE role_Name = 'ROLE_GUEST'
);