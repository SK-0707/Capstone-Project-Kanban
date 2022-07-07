FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9005
ADD ./target/kanbanService-0.0.1-SNAPSHOT.jar /usr/src/kanbanService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","kanbanService-0.0.1-SNAPSHOT.jar"]