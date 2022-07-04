FROM openjdk:8 as drones
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY ./src ./src
COPY ./pom.xml ./pom.xml

RUN chmod 755 /app/mvnw

RUN ./mvnw package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/drones.jar"]