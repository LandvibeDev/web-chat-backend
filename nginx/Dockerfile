# docker build -t nginx ./nginx
# docker run --name nginx -d -p 8080:80 -v front:/front:ro --link web-chat nginx
FROM nginx

COPY mime.types /etc/nginx/conf/mime.types
COPY nginx.conf /etc/nginx/nginx.conf
