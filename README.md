### Spring Boot Native project with Spring MVC + Spring Data MongoDB.

Project based on [data-mongodb](https://github.com/spring-projects-experimental/spring-native/tree/master/samples/data-mongodb)

To build and run the native application packaged in a lightweight container:
```
mvn spring-boot:build-image
```

To run with docker use `docker-compose up` or executable `target/spring-native-crud-mongodb`

To run the code locally you can use `./build.sh` but need `GraalVM native-image` installed follow documentation at https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-native-image

### Build on Raspberry Pi 4(AARCH64)

To install GraalVM on Pi follow this link https://github.com/dongjinleekr/graalvm-ce-deb

To install Maven https://xianic.net/2015/02/21/installing-maven-on-the-raspberry-pi/

To set GraalVM as default java version use the following commands:
```
sudo update-alternatives --install /usr/bin/java java /your_path/graalvm-ce-deb/graalvm-ce-java11_aarch64_21.0.0.2/usr/lib/jvm/graalvm-ce-java11/bin/java 1
sudo update-alternatives --config java
#Choose 1

#Check version
java -version
>openjdk version "11.0.10" 2021-01-19
>OpenJDK Runtime Environment GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06)
>OpenJDK 64-Bit Server VM GraalVM CE 21.0.0.2 (build 11.0.10+8-jvmci-21.0-b06, mixed mode)

#Build native image - tests are not passing cause of embedded mongodb
mvn spring-boot:build-image -DskipTests
```

PS: `GraalVM AARCH64` is under development more details look at [GraalVM Documentation](https://www.graalvm.org/docs/introduction/)

### Github Action - not ready yet

It will build two images(`amd64, aarch64`) and publish on Docker Hub for [AMD64](https://hub.docker.com/repository/docker/fielcapao/spring-native-crud-mongodb-amd64) and another for [AARCH64 - Raspberry Pi 4](https://hub.docker.com/repository/docker/fielcapao/spring-native-crud-mongodb-aarch64)

### Rest Endpoints

To access endpoints use `http:localhost:8080/api/orders` and index page will redirect to `/actuator/env`. 