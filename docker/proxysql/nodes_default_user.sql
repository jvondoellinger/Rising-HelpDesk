CREATE USER 'proxysql_user'@'%' IDENTIFIED BY "secret";

GRANT ALL PRIVILEGES ON *.* TO 'proxysql_user'@'%';
FLUSH PRIVILEGES;