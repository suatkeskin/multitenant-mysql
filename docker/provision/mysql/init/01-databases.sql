CREATE DATABASE IF NOT EXISTS mysql_tenant_1;
CREATE DATABASE IF NOT EXISTS mysql_tenant_2;

CREATE USER 'mysql_tenant_1'@'localhost' IDENTIFIED BY 'mysql_tenant_1';
CREATE USER 'mysql_tenant_2'@'localhost' IDENTIFIED BY 'mysql_tenant_2';
GRANT ALL ON mysql_tenant_1.* TO 'mysql_tenant_1'@'localhost';
GRANT ALL ON mysql_tenant_2.* TO 'mysql_tenant_2'@'localhost';
FLUSH PRIVILEGES;