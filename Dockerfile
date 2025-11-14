#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY target/bookingsapp.jar app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
#ENV SPRING_PROFILES_ACTIVE=production
#ENV DB_PASSWORD=

#FROM eclipse-temurin:17-jre-alpine
#WORKDIR /app
#COPY target/*.jar app.jar
#RUN addgroup -S spring && \
#    adduser -S spring -G spring
#USER spring:spring
#EXPOSE 8080CMD
#CMD ["java", "-jar", "app.jar"]

# BUILD
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# RUNTIME
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN addgroup -S spring && \
    adduser -S spring -G spring
USER spring:spring

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

# .dockerignore
#.git
#.gitignore
#node_modules/
#target/
#*.iml
#*.log
#README.md
#.DS_Store
#.env
#.idea
#.vscode