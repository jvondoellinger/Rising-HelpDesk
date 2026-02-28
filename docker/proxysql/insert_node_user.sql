INSERT INTO mysql_users (
  username,
  password,
  default_hostgroup,
  transaction_persistent
) VALUES (
  'proxysql_user',
  'secret',
  10,
  1
);

LOAD MYSQL USERS TO RUNTIME;
SAVE MYSQL USERS TO DISK;
