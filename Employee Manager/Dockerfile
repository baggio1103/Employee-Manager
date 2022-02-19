FROM openjdk:11
WORKDIR /app
COPY /target/employee-manager.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "employee-manager.jar"]