DROP TABLE IF EXISTS USERS CASCADE;
CREATE TABLE USERS
(
    ID    SERIAL PRIMARY KEY,
    USERNAME VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL
        );

DROP TABLE IF EXISTS ROLES CASCADE;
CREATE TABLE ROLES
(
    ID    SERIAL PRIMARY KEY,
    ROLE VARCHAR(255) NOT NULL,
    USER_ID BIGINT NOT NULL references USERS (ID) ON DELETE CASCADE
        );