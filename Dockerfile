FROM openjdk:17-oracle as build

COPY build/libs/Inventario-*.jar Inventario.jar

CMD ["java", "-jar", "Inventario.jar"]