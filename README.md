# app-carrocompra-soytul
Proyecto carro de compra, para conocimientos técnicos del lenguaje y solución programatica.


Para correr la aplicacion se debe tener configurado en la maquina local:
JAVA 8
MAVEN

descargar el proyecto y ejecutar
  mvn clean install 

Y a continuacion correr:
  java -jar target/app-carrocompra-soytul-0.0.1-SNAPSHOT.jar
o
  mvn spring-boot:run

Puedes ver la base de datos entrando:
  http://localhost:8080/h2

usuario: sa
password: password


Para probar las apis:
  Probar
  http://localhost:8080/v2/api-docs

  Probar UI
  http://localhost:8080/swagger-ui.html
