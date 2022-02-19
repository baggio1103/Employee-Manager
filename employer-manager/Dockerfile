FROM node:14.16.0-alpine3.13 as node
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=node /app/dist/employer-manager /usr/share/nginx/html