@echo off

set MYSQL_USER=proxysql_user
set MYSQL_PASS=secret123

echo Iniciando verificacao dos nodes...
echo.

for %%N in (mysql_master mysql_repl_1 mysql_repl_2) do (

    echo Conectando ao node: %%N

    docker exec %%N mysql -u %MYSQL_USER% -p%MYSQL_PASS% -e "SELECT @@hostname, NOW();"

    if errorlevel 1 (
        echo Erro ao conectar no node %%N
    )

    echo --------------------------------
)

echo Entrando no container ProxySQL...

docker exec -it proxysql mysql -u admin -padmin -h 127.0.0.1 -P 6032

echo.
echo Script executado com sucesso.
pause