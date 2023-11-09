FROM node:14

WORKDIR /usr/src/tpAchatProject

COPY package*.json ./


RUN npm install -g @angular/cli


RUN npm install


COPY . .


RUN ng build --prod


EXPOSE 80


CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "80"]
