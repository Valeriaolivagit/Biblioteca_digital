# Biblioteca_digital 
Proyecto modulo 5

#Autor Valeria Oliva
#Versión 1.0

# Descripción
Aplicación realizada como parte del proyecto del módulo 5, del bootcamp Fullstack Java.
Biblioteca Digital es una aplicación web desarrollada en Java que permite gestionar libros y préstamos dentro de una biblioteca. 

El sistema permite a los usuarios registrarse, iniciar sesión, consultar libros disponibles, solicitar préstamos, realizar devolución de libros, entre otros. 

# Tecnologías utilizadas 

- Java
- Servlets
- JSP
- MySQL
- JDBC
- HTML5
- CSS3
- Bootstrap
- Apache Tomcat
- Maven

## Estructura del proyecto

El proyecto sigue una arquitectura por capas, siguiendo el patrón MVC:

- **Controllers (Servlets):** Gestionan las peticiones HTTP.
- **Services:** Contienen la lógica de negocio.
- **DAO:** Se encargan del acceso a la base de datos.
- **Entities:** Representan las entidades del sistema.
- **DTO:** Objetos de transferencia de datos entre capas.
- **Utils:** Clases utilitarias como la conexión a la base de datos.
- **JSP:** Vistas del sistema.
  **VIEWS:** Vistas del usuario
  **includes:** Head, header y footer.
  **assets:** Contiene las imagenes, css y javascript utilizado.

  ## Funcionalidades

- Registro de usuarios
- Inicio de sesión
- Visualización de catálogo de libros
- Solicitud de préstamos
- Gestión de libros (devolución)
- Eliminación de libros
- Actualización de perfil

  ## Base de datos

El sistema utiliza MySQL como base de datos.
Tablas principales:

- users
- books
- loan

## Instalación y ejecución
Opcion 1:
1. Extraer el archivo rar "biblioteca-digital"
2. Importar el proyecto en Visual studio code (Open-folder -> biblioteca-digital)
3. Configurar Apache Tomcat
4. Crear la base de datos en MySQL (Para mayor rapidez, utilizar el archivo sql "Query_DB_virtual_library")
5. Ejecutar el proyecto en el servidor
   
Opcion 2:
1. Clonar el repositorio
2. Importar el proyecto en Visual studio code o cualquier otro IDE
3. Configurar Apache Tomcat
4. Crear la base de datos en MySQL 
5. Ejecutar el proyecto en el servidor

# Video demostrativo
https://drive.google.com/file/d/1X8E-8yJA8DgU4BMEmF9CJZf2Eb7SpvOA/view?usp=sharing
