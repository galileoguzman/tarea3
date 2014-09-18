Programación en Red y Web II
Desarrollo de una aplicacion JAVA Servlets
======

La aplicacion se conectará a un motor de base de datos a través de una Interface DataSource.

Requerimientos:
======

apache-tomcat-7.0.55
apache-ant-1.9.4
PostgreSQL 9.3.1.0
postgresql-9.3-1102.jdbc3.jar

Directorio SRC
======

Contiene las clases de la implementación de los Servlets así como DAOEjercicio, el cual se conecta y realiza operaciones con la base de datos, Usuario.java se encarga de la definición del objeto que se agregara, eliminara o se consultara en la BD.

Directorio web/META-INF
======

Contiene el archivo context.xml el cual define nuestra interface DataSource con JDBC para conectarse a la base de datos, declarando usuario, password, host de la fuente de datos.

Fichero web/WEB-INF/web.xml
======

Contiene el mapeo de nuestras rutas (URL) a nuestros Servlets

Fichero Build.xml
=====
Es el archivo más importante de nuestro proyecto, a través de el se declaran las operaciones que realizara ant (gestor de tareas) así como la definición del nombre de nuestro proyecto y el nombre de nuestra aplicación.

Directorio web/
=====
Contiene los archivos estaticos de nuestra aplicación: Formularios, página de inicio y Directorio CSS el cual contiene un archivo estilos.css que define los estilos de nuestro html.

