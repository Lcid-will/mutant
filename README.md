# mutant
Proyecto mutant(API REST):

El  proyecto mutan consiste en un servicio servicio rest que contiene 2 metodos: 

1-/mutant/ metodo POST donde recibira un arreglo (array[] string) donde se pasara el DNA donde es de NxN y se valida que que las columnas sean iguales en todas las filas y validara que las letras del DNA sea solo (A|T|C|G), despues validara que al menos tenga filas o columnas con tamaño >=4 y demostrara cuantos tienen cadenas con 4 letras iguales(para >1 retorna OK y los que no cumplen las condiciones retorna 403) y recibiria el siguiente objeto:

POST → /api/mutant/
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}


2-/stats/ metodo GET donde no recibe ninguno  parametro de entrada y consiste en traer el contador de mutantes y de humanos por aparte y el promedio de mutantes, la respuesta del objeto json es :

GET → /api/stats/
{“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}

El proyecto esta desarrollado en JAVA(con Framework SpringBoot), Base de datos MariaDB y contiene archivo Dockerfile para montar imagen si se requiere.

Para Ejecutar el proyecto se debe:

1- Correr Script sql en Maria DB parar Crear BD y tabla respectiva, el archivo esta en el archivo con ruta /sql/mutant.sql
2- Una ves ejecutado el punto anterior crear usuario BD con los permisos de SELECT,SHOWDATABASE y INSERT, despues modificar el properties de la ruta: mutant/src/main/resources/application.properties

spring.datasource.url=jdbc:mariadb://localhost:6630/mutant
spring.datasource.username=#usuariocreado
spring.datasource.password=#password

la url cambiar servidor y puerto a su gusto

3-Una ves ejecutado los dos puntos anteriores y con ayuda de MAVEN sobre la carpeta del proyecto ejecutar el comando "mvn clean install" para limpiar y generar el jar mutant-0.0.1-SNAPSHOT.jar , despues para ejecutarlo desde CMD(Windows) o SHELL(linux) ejecutar el comando "java -jar mutant-0.0.1-SNAPSHOT.jar". o en su defecto cargar el proyecto en el IDE eclipse y darle run al proyecto.

4-Una ves ejecutado el proyecto se desplegara en el puerto 8081 con el servidor Tomcat enbebido y los dos servicios se podran ejecutar de la siguiente forma:
  4.1- http://localhost:8081/api/mutant/  (Metodo POST)
  4.2- http://localhost:8081/api/stats/ (Metodo GET)



