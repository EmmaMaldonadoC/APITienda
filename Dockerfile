# Etapa 1: compilar el jar con Gradle
FROM eclipse-temurin:25-jdk AS build
#JDK 21
#FROM eclipse-temurin:21-jdk AS build
#JDK 17
#FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon > /dev/null

COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

# Etapa 2: imagen liviana solo con el JRE
FROM eclipse-temurin:25-jre
#JDK 21
#FROM eclipse-temurin:21-jre
#JDK 17
#FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar

# Render asigna el puerto en la variable PORT (application.properties ya la lee)
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
