FROM nginx:stable-alpine3.17-slim

RUN apk add --update nodejs npm
RUN npm install -g @angular/cli

WORKDIR /usr/src/app
COPY . .
RUN npm install
RUN npm run build

RUN rm -rf /usr/share/nginx/html/*
RUN cp -r dist/ecommerce/* /usr/share/nginx/html

COPY angular.conf /etc/nginx/conf.d/angular.conf

EXPOSE 80
