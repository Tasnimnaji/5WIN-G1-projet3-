FROM openjdk:11
EXPOSE 8089
ADD http://192.168.0.112:8081/repository/devops-validation/com/esprit/examen/tpAchatProject/1.0/tpAchatProject-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]