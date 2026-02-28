CREATE USER 'repl_user'@'%' 
IDENTIFIED WITH caching_sha2_password 
BY 'secret123';

GRANT REPLICATION SLAVE, REPLICATION CLIENT
ON *.*
TO 'repl_user'@'%';

FLUSH PRIVILEGES;