# docker build -t web-chat .
# docker run --name webchat -d -p 8089:8089 webchat
FROM ubuntu
FROM openjdk:11

COPY ./build/libs/ /home/user/webchat/

WORKDIR /home/user/webchat/

ENTRYPOINT [ "sh", "-c", "java -jar -Dspring.profiles.active=dev web-chat-backend-0.0.1-SNAPSHOT.jar"]