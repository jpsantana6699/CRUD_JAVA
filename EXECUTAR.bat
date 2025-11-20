@echo off
title Compilar e Executar Catalogo de Midia
color 0A

REM Adiciona Java ao PATH temporariamente
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-8.0.392.8-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Configura Tomcat
set "CATALINA_HOME=C:\tomcat"

echo.
echo ========================================
echo   COMPILANDO CATALOGO DE MIDIA
echo ========================================
echo.

REM Define diretorios
set PROJETO_DIR=%~dp0
set SRC_DIR=%PROJETO_DIR%src\main\java
set WEBAPP_DIR=%PROJETO_DIR%src\main\webapp
set LIB_DIR=%PROJETO_DIR%lib
set BUILD_DIR=%PROJETO_DIR%build
set CLASSES_DIR=%BUILD_DIR%\WEB-INF\classes
set TOMCAT_WEBAPPS=C:\tomcat\webapps\catalogo-midia

echo [1/6] Criando estrutura de build...
if not exist "%BUILD_DIR%" mkdir "%BUILD_DIR%"
if not exist "%CLASSES_DIR%" mkdir "%CLASSES_DIR%"
if not exist "%BUILD_DIR%\WEB-INF\lib" mkdir "%BUILD_DIR%\WEB-INF\lib"

echo.
echo [2/6] Compilando codigo Java...
echo.

REM Compila Model
echo    - Compilando Model...
javac -encoding UTF-8 -cp "%LIB_DIR%\*" -d "%CLASSES_DIR%" "%SRC_DIR%\com\catalogo\model\ItemMidia.java"
if errorlevel 1 (
    echo ERRO ao compilar Model!
    pause
    exit /b 1
)

REM Compila DAO
echo    - Compilando DAO...
javac -encoding UTF-8 -cp "%LIB_DIR%\*;%CLASSES_DIR%" -d "%CLASSES_DIR%" "%SRC_DIR%\com\catalogo\dao\FabricaDeConexoes.java" "%SRC_DIR%\com\catalogo\dao\ItemMidiaDAO.java"
if errorlevel 1 (
    echo ERRO ao compilar DAO!
    pause
    exit /b 1
)

REM Compila Service
echo    - Compilando Service...
javac -encoding UTF-8 -cp "%LIB_DIR%\*;%CLASSES_DIR%" -d "%CLASSES_DIR%" "%SRC_DIR%\com\catalogo\service\ItemMidiaService.java"
if errorlevel 1 (
    echo ERRO ao compilar Service!
    pause
    exit /b 1
)

REM Compila Servlets
echo    - Compilando Servlets...
javac -encoding UTF-8 -cp "%LIB_DIR%\*;C:\tomcat\lib\servlet-api.jar;%CLASSES_DIR%" -d "%CLASSES_DIR%" "%SRC_DIR%\com\catalogo\servlet\CadastrarItemServlet.java" "%SRC_DIR%\com\catalogo\servlet\ListarItensServlet.java" "%SRC_DIR%\com\catalogo\servlet\EditarItemServlet.java" "%SRC_DIR%\com\catalogo\servlet\ExcluirItemServlet.java" "%SRC_DIR%\com\catalogo\servlet\BuscarItemServlet.java"
if errorlevel 1 (
    echo ERRO ao compilar Servlets!
    pause
    exit /b 1
)

echo.
echo [3/6] Compilacao concluida com sucesso!
echo.

echo [4/6] Preparando arquivos para deploy...

REM Copia JARs
echo    - Copiando bibliotecas...
copy /Y "%LIB_DIR%\*.jar" "%BUILD_DIR%\WEB-INF\lib\" > nul

REM Copia web.xml
echo    - Copiando web.xml...
copy /Y "%WEBAPP_DIR%\WEB-INF\web.xml" "%BUILD_DIR%\WEB-INF\" > nul

REM Copia JSPs
echo    - Copiando JSPs...
copy /Y "%WEBAPP_DIR%\*.jsp" "%BUILD_DIR%\" > nul

REM Copia CSS
echo    - Copiando CSS...
if not exist "%BUILD_DIR%\css" mkdir "%BUILD_DIR%\css"
copy /Y "%WEBAPP_DIR%\css\*.css" "%BUILD_DIR%\css\" > nul

REM Copia JS
echo    - Copiando JavaScript...
if not exist "%BUILD_DIR%\js" mkdir "%BUILD_DIR%\js"
copy /Y "%WEBAPP_DIR%\js\*.js" "%BUILD_DIR%\js\" > nul

echo.
echo [5/6] Fazendo deploy no Tomcat...

REM Remove deploy antigo se existir
if exist "%TOMCAT_WEBAPPS%" (
    echo    - Removendo deploy anterior...
    rmdir /S /Q "%TOMCAT_WEBAPPS%" > nul 2>&1
)

REM Copia para Tomcat
echo    - Copiando para webapps...
xcopy /E /I /Y "%BUILD_DIR%" "%TOMCAT_WEBAPPS%" > nul

echo.
echo [6/6] Iniciando Tomcat...
echo.

REM Para o Tomcat se estiver rodando
tasklist /FI "IMAGENAME eq java.exe" 2>NUL | find /I "java.exe">NUL
if "%ERRORLEVEL%"=="0" (
    echo    - Parando Tomcat existente...
    call C:\tomcat\bin\shutdown.bat > nul 2>&1
    timeout /t 3 /nobreak > nul
)

REM Inicia o Tomcat
echo    - Iniciando servidor...
start /B cmd /c "C:\tomcat\bin\startup.bat"

echo.
echo Aguardando Tomcat inicializar (15 segundos)...
timeout /t 15 /nobreak > nul

echo.
echo ========================================
echo          TUDO PRONTO!
echo ========================================
echo.
echo Acesse no navegador:
echo.
echo    http://localhost:8080/catalogo-midia/
echo.
echo DICA: Se der erro 404, aguarde mais 10 segundos
echo       e recarregue a pagina (F5)
echo.
echo Logs do Tomcat: C:\tomcat\logs\catalina.out
echo.
echo Para parar: C:\tomcat\bin\shutdown.bat
echo.

pause

REM Abre o navegador automaticamente
start http://localhost:8080/catalogo-midia/
