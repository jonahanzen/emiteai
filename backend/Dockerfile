FROM eclipse-temurin:21-jdk-alpine as build
WORKDIR /app
# Copy only pom.xml and mvnw files first for dependency caching
COPY pom.xml mvnw mvnw.cmd ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline
# Now copy the rest of the source code
COPY . .
RUN ./mvnw clean package 

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
