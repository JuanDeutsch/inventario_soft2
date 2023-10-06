FROM openjdk:17-oracle as build

ARG JAR_FILE=build/libs/Inventario-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} Inventario.jar

ENTRYPOINT ["java", "-jar", "/Inventario.jar"]