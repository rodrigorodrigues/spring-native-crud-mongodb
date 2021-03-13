### Spring Boot Native project with Spring MVC + Spring Data MongoDB.

Project based on [data-mongodb](https://github.com/spring-projects-experimental/spring-native/tree/master/samples/data-mongodb)

To build and run the native application packaged in a lightweight container:
```
mvn spring-boot:build-image
```

To run with docker use `docker-compose up` or executable `target/spring-native-crud-mongodb`

To run the code locally you can use `./build.sh` but need `GraalVM native-image` installed follow documentation at https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-native-image

### Github Action

It will build two images(`amd64, aarch64`) and publish on Docker Hub for [AMD64](https://hub.docker.com/repository/docker/fielcapao/spring-native-crud-mongodb-amd64) and another for [AARCH64 - Raspberry Pi 4](https://hub.docker.com/repository/docker/fielcapao/spring-native-crud-mongodb-aarch64)

PS: `AARCH64` is under development more details look at [GraalVM Documentation](https://www.graalvm.org/docs/introduction/)