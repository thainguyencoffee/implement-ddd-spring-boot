# DDD Library

[![Java CI with Gradle](https://github.com/thainguyencoffee/ddd-library/actions/workflows/build.yaml/badge.svg)](https://github.com/thainguyencoffee/ddd-library/actions/workflows/build.yaml)


## Project description

The project uses:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.3/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.3/gradle-plugin/packaging-oci-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#io.validation)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.3/reference/htmlsingle/index.html#web)
* [Spring Modulith](http://github.com/spring-projects/spring-modulith)
* [jMolecules](https://github.com/xmolecules/jmolecules)

### Spring Data JPA

The Spring Data repository mechanism is used to reduce the effort to implement persistence for the domain objects to the
declaration of an interface per aggregate root

### Spring Modulith

Spring Modulith supports modular architecture in monoliths. It treats each root-level package as a separate module with
top-level public members exposed as API and all other code protected as module-internal (unless explicitly specified
otherwise). Through tests, it verifies compliance to modular architecture rules and also generates documentation for
architecturally relevant components of the system. See `ModularityTests` and also `build/spring-modulith-docs/`, once
the test has been executed.
