--OBS: Todos itens em mysql_server com ''

--MASTER
INSERT INTO mysql_servers (hostgroup_id, hostname, port, weight, max_connections) 
VALUES (10, 'mysql-master', 3306, 1000, 15);

--REPL 1
INSERT INTO mysql_servers (hostgroup_id, hostname, port, weight, max_connections) 
VALUES (20, 'mysql-repl-1', 3306, 1000, 15);

--REPL 2
INSERT INTO mysql_servers (hostgroup_id, hostname, port, weight, max_connections) 
VALUES (20, 'mysql-repl-2', 3306, 1000, 15);

LOAD MYSQL SERVERS TO RUNTIME;
SAVE MYSQL SERVERS TO DISK;