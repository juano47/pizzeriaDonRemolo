FROM openjdk:11
ADD target/pizzeriaRemoloApiRest.jar pizzeriaRemoloApiRest.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "pizzeriaRemoloApiRest.jar"]