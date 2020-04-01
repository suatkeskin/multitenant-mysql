CREATE TABLE IF NOT EXISTS mysql_master.hibernate_sequence
(
    next_val bigint null
);

CREATE TABLE IF NOT EXISTS mysql_master.datasource_config
(
    ID                INT PRIMARY KEY,
    DRIVER_CLASS_NAME VARCHAR(255),
    DATASOURCE_URL    VARCHAR(255),
    DATABASE_NAME     VARCHAR(255),
    USERNAME          VARCHAR(255),
    PASSWORD          VARCHAR(255)
);

INSERT INTO mysql_master.datasource_config
VALUES (1, 'com.mysql.jdbc.Driver', 'jdbc:mysql://localhost:3306/mysql_tenant_1?useSSL=false', 'mysql_tenant_1', 'mysql_tenant_1', 'mysql_tenant_1');

INSERT INTO mysql_master.datasource_config
VALUES (2, 'com.mysql.jdbc.Driver', 'jdbc:mysql://localhost:3306/mysql_tenant_2?useSSL=false', 'mysql_tenant_2', 'mysql_tenant_2', 'mysql_tenant_2');