
-- ADICIONANDO USUARIO
INSERT INTO mysql_users (
  username,
  password,
  default_hostgroup,
  transaction_persistent
) VALUES (
  'proxysql_user',
  'secret123',
  10,
  1
);

LOAD MYSQL USERS TO RUNTIME;
SAVE MYSQL USERS TO DISK;


-- ADICIONANDO SERVIDORES
--MASTER
INSERT INTO mysql_servers (hostgroup_id, hostname, port, weight, max_connections) 
VALUES (10, 'mysql_master', 3306, 1000, 15);

--REPL 1
INSERT INTO mysql_servers (hostgroup_id, hostname, port, weight, max_connections) 
VALUES (20, 'mysql_repl_1', 3306, 1000, 15);

--REPL 2
INSERT INTO mysql_servers (hostgroup_id, hostname, port, weight, max_connections) 
VALUES (20, 'mysql_repl_2', 3306, 1000, 15);

LOAD MYSQL SERVERS TO RUNTIME;
SAVE MYSQL SERVERS TO DISK;

-- REGRAS
INSERT INTO mysql_query_rules (rule_id, active, match_pattern, destination_hostgroup, apply) 
VALUES (100, 1, '^SELECT', 20, 1 );

LOAD MYSQL QUERY RULES TO RUNTIME;
SAVE MYSQL QUERY RULES TO DISK;

SET mysql-server_version='8.0.4';
LOAD MYSQL VARIABLES TO RUNTIME;
SAVE MYSQL VARIABLES TO DISK;