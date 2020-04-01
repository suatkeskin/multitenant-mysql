CREATE TABLE IF NOT EXISTS mysql_tenant_1.hibernate_sequence
(
    next_val bigint null
);

CREATE TABLE IF NOT EXISTS mysql_tenant_1.city
(
    ID   INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(256),
    PRIMARY KEY (ID)
);