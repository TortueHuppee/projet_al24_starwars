echo %JAVA_HOME%
set WSDL_URL=http://192.168.100.130:8080/ode/processes/BPELBank?wsdl
cd /d "%~dp0"
"%JAVA_HOME%/bin/wsimport" -s C://Users/Elsa/git/starwars_eshop/manufacture/manufactureWEB_JSF2/src/main/java/manufacture/web/webService -keep %WSDL_URL%
pause