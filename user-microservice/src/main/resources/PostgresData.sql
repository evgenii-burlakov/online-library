INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('USER', 'USER');
INSERT INTO USERS (USERNAME, PASSWORD)
VALUES ('ADMIN', 'ADMIN');

INSERT INTO ROLES (ROLE, USERNAME)
SELECT 'USER', U.USERNAME
FROM USERS U
WHERE U.USERNAME = 'USER';

INSERT INTO ROLES (ROLE, USERNAME)
SELECT 'USER', U.USERNAME
FROM USERS U
WHERE U.USERNAME = 'ADMIN';

INSERT INTO ROLES (ROLE, USERNAME)
SELECT 'ADMIN', U.USERNAME
FROM USERS U
WHERE U.USERNAME = 'ADMIN';