#Ejemplo back con Spring Boot 


## Base de datos

Por defecto se utiliza MySQL aunque con Spring se pueden otros gestores, como por ejemplo Oracle, SQL Server, H2, etc.

* ***Creación de base de datos***

Para poder utilizar `RookieProject-rest` es necesario crear la base de datos `base_project` en el MySQL que tengas
instalado en local. Para ello, hay que conectar al MySQL local con el usuario `root` (o un usuario privilegiado) y
ejecutar el siguiente comando:

```sql
CREATE DATABASE `fruits` /*!40100 COLLATE 'utf8_general_ci' */
```

* ***Crear usuario de la base de datos***

```sql
CREATE USER 'fruits'@'%' IDENTIFIED BY 'fruits';

GRANT USAGE ON *.* TO 'fruits'@'%';
GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, LOCK TABLES  ON `fruits`.* TO 'fruits'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```