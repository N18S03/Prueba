FROM openjdk:17
COPY "./target/Biblioteca-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8106
ENTRYPOINT [ "java", "-jar", "app.jar"]