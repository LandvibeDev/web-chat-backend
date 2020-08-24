# docker build -t web-chat --build-arg url='' --build-arg username='' --build-arg password='' .
# docker run --name webchat -d -p 8080:8080 webchat
FROM ubuntu
FROM openjdk:11

ARG url
ARG username
ARG password

COPY ./build/libs/ /home/user/webchat/

WORKDIR /home/user/webchat/

ENV RUNNER "java -jar -Dspring.profiles.active=dev -Dspring.datasource.url=${url}  -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} web-chat-backend-0.0.1-SNAPSHOT.jar"

ENTRYPOINT [ "sh", "-c", "$RUNNER"]