FROM openjdk:11-jre
MAINTAINER pedro-catalisa-5
COPY target/contaBancaria-0.0.1-SNAPSHOT.jar contaBancaria-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","/contaBancaria-0.0.1-SNAPSHOT.jar"]