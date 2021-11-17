FROM openjdk:11
ARG JAR_FILE=target/cicd-todo-app.jar
COPY ${JAR_FILE} cicd-todo-app.jar
ENTRYPOINT ["java","-jar","/cicd-todo-app.jar"]