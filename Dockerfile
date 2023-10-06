FROM openjdk:17-oracle

ADD build/libs/Inventario-*.jar Inventario.jar

CMD ["java", "-jar", "Inventario.jar"]