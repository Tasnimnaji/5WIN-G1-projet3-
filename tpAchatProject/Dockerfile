FROM openjdk:11

# Set the working directory
WORKDIR /tpAchatProject

COPY target/*.jar /tpAchatProject.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/tpAchatProject.jar"]

