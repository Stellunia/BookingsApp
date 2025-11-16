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
FROM gradle:8.5-jdk17-alpine AS build
WORKDIR /app

COPY gradlew gradlew.bat ./
COPY gradle gradle

COPY build.gradle.kts settings.gradle.kts ./
COPY src src

RUN ./gradlew clean bootJar --no-daemon

# RUNTIME
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

#RUN addgroup -S spring && \
#    adduser -S spring -G spring
#USER spring:spring




