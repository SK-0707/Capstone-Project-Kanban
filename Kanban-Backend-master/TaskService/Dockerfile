FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9007
ADD ./target/TaskService-0.0.1-SNAPSHOT.jar /usr/src/TaskService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","TaskService-0.0.1-SNAPSHOT.jar"]