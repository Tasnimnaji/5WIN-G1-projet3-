FROM openjdk:8
LABEL authors="tasni"
EXPOSE 8082
RUN curl -O http://192.168.33.10:8081/repository/maven-releases/com/esprit/examen/tpAchatProject/1.0/tpAchatProject-1.0.jar
ENTRYPOINT ["java", "-jar", "/tpAchatProject-1.0.jar"]




