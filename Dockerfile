FROM openjdk:17-oracle

COPY build/libs/Inventario-0.0.1-SNAPSHOT.jar Inventario.jar

CMD ["java", "-jar", "Inventario.jar"]