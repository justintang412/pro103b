docker container stop archive-app
docker container rm archive-app
cd docker
docker build -t justintang412/private:archive-app-03 .
docker run --name archive-app -d -p 3306:3306 -p 3000:3000 -p 8080:8080 justintang412/private:archive-app-03