#!/bin/bash

NODE_LIST=("mysql_master" "mysql_repl_1" "mysql_repl_2")

MYSQL_USER="proxysql_user"
MYSQL_PASS="secret123"

echo "Iniciando verificação dos nodes..."

for NODE in "${NODE_LIST[@]}"; do
    echo "Conectando ao node: $NODE"

    mysql -h "$NODE" -u "$MYSQL_USER" -p"$MYSQL_PASS" -e "SELECT @@hostname, NOW();"

    if [ $? -ne 0 ]; then
        echo "Erro ao conectar no node $NODE"
    fi

    echo "--------------------------------"
done

echo "Entrando no container ProxySQL..."

docker exec -it proxysql bash -c "mysql -u admin -padmin -h 127.0.0.1 -P 6032"