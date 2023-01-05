#FROM maven:3-openjdk-18 as build
#WORKDIR /build
#COPY . .
#RUN mvn clean package -DskipTests

#FROM openjdk:11 AS RUN
#WORKDIR /app
#COPY --from=build ./build/target/*.jar ./application.jar
#EXPOSE 5000
#ENTRYPOINT java -jar application.jar

FROM openjdk:11 AS RUN
WORKDIR /app
COPY ./target/*.jar ./application.jar
EXPOSE 5000
ENTRYPOINT java -jar application.jar