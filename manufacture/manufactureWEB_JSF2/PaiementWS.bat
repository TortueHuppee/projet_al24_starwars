echo %JAVA_HOME%
set WSDL_URL=http://192.168.20.110:8080/ode/processes/BPELBank?wsdl
cd /d "%~dp0"
"%JAVA_HOME%/bin/wsimport" -s C://Users/Stagiaire/Desktop/workspace-starwars-projetWS-2/starwars_eshop/manufacture/manufactureWEB_JSF2/src/main/java/manufacture/web/webService -keep %WSDL_URL%
pause