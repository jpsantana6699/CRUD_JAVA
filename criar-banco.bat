@echo off
echo ========================================
echo CRIANDO BANCO DE DADOS - Catalogo Midia
echo ========================================
echo.

set MYSQL_PATH="C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe"

echo Digite a senha do root do MySQL:
set /p SENHA=Senha: 

echo.
echo Criando banco de dados...
echo.

%MYSQL_PATH% -u root -p%SENHA% < script-banco.sql

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo SUCESSO! Banco de dados criado!
    echo ========================================
    echo.
    echo Proximo passo: Configure a senha em FabricaDeConexoes.java
    echo.
) else (
    echo.
    echo ========================================
    echo ERRO ao criar banco de dados!
    echo ========================================
    echo.
    echo Verifique:
    echo - A senha esta correta?
    echo - O MySQL esta rodando?
    echo.
)

pause
