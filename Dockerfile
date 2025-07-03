FROM eclipse-temurin:21-jdk as build

COPY . /app
WORKDIR /app

RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar app.jar

FROM eclipse-temurin:21-jre as build

ARG PORT
ENV PORT=${PORT}

COPY --FROM=app /app/app.jar /app/app.jar

RUN useradd runtime 
USER runtime

ENTRYPOINT ["java","-Dserver.port=${PORT}", "-jar", "/app.jar"]
