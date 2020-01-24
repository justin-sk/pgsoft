# PG-SOFT-API

[![Build Status](https://dev.azure.com/pgsoftcrm/pgsoftapi/_apis/build/status/pgsoftapi?branchName=master)](https://dev.azure.com/pgsoftcrm/pgsoftapi/_build/latest?definitionId=1&branchName=master)

# PG SOft API (Java/Spring Boot)

This is a Java/Spring Boot application to support PG CRM Web UI.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

# Repository structure

The main files in this repository are:

* `/azure-pipelines.yml` contains the build pipeline instructions
* `src/` contains the actual Java application
* `src/main/java/com/pgsoft/exception` contains the custom exception files
* `src/main/java/com/pgsoft/persistence` contains the JPAs, Query DSL, and Repository files for all database objects
* `src/main/java/com/pgsoft/service` contains the service, DTOs, mapper, and Binding files for all persistence objects
* `src/main/java/com/pgsoft/web/rest` contains the resource files for all database objects
* `src/main/java/com/pgsoft/web/config` contains the configuration files used in the application

# Usage

* To package this service locally via maven:
  * mvn package -P dev,swagger,mssql-database

# License

Licensed under Apache 2.0. Please see LICENSE for details.