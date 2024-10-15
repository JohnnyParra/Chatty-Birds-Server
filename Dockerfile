FROM openjdk:23-jdk
WORKDIR /spring
COPY build/libs/real-time-chat-0.0.1-SNAPSHOT.jar spring.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring.jar"]