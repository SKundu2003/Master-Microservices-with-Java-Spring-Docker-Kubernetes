
#Start with a base image containing java run time
FROM openjdk:19-slim as build

#Information about who maintain's the image
MAINTAINER Souvik

LABEL authors="souvik"

#Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar","/accounts-0.0.1-SNAPSHOT.jar"]