# Spring and JSP Example

Example application to show use of the following technologies:

* Spring with XML configuration
* JSP
* Spring's named parameter JDBC templates
* H2 in-memory database

## Building application
Build the application using Apache Maven.

```
mvn clean install
```

This will create a Java web application (WAR) artifact within the `target` folder.

**Note:** The `groupId` field in the `pom.xml` will need to be populated for the build to be successful.

## Running application
The application is run using Jetty servlet container.

```
mvn jetty:run
```