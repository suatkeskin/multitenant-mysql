CREATE TABLE IF NOT EXISTS mysql_tenant_2.hibernate_sequence
(
    next_val bigint null
);
CREATE TABLE IF NOT EXISTS mysql_tenant_2.city
(
    ID   INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(256),
    PRIMARY KEY (ID)
);