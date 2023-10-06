FROM openjdk:17-oracle

COPY build/libs/Inventario-*.jar Inventario.jar

CMD ["java", "-jar", "Inventario.jar"]