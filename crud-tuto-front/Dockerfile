FROM nginx:latest

WORKDIR /crud-tuto-front

EXPOSE 80

COPY dist/crudtuto-Front /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
