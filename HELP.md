# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#using-boot-devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Session](https://docs.spring.io/spring-session/reference/)
* [JDBC API](https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/reference/htmlsingle/#boot-features-sql)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)

### mysql8
unzip mysql8.zip downloaded from official website
create data folder
change directory into it

create my.ini
[mysqld]
basedir=C:/mysql-8.0.28-winx64
datadir=data

mysqld --initialize
refer to this [data initialization](https://dev.mysql.com/doc/refman/8.0/en/data-directory-initialization.html)
find the password in this log file:
DESKTOP-HQS9ML0.err
promoted password is: PjTPwdMqx8:y

mysqld --defaults-file=my.ini
mysql -u root -p

ALTER USER 'root'@'localhost' IDENTIFIED with mysql_native_password BY 'root';
flush privileges;

mysqld
mysqladmin -u root shutdown

reference of [spring jdbcTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html)

```
import axios from 'axios'
const http = axios.create()
http(`/api${base64src}`, {
params: {}
}).then(resp => {
this.$refs.Captcha.src = `data:image/png;base64,${resp.data}`
})

```

and this line does the same as above:
```const { data } = await Vue.prototype.$http('/ccas/dashboard/user/info')```

docker pull mariadb
docker run --name archive-mariadb -e MARIADB_ROOT_PASSWORD=root -d -p 3306:3306 mariadb:latest
docker container stop archive-mariadb
docker container rm archive-mariadb

create a file named dockerfile
docker build .

docker tag <image-id> justintang412/private:archive-app

docker run --name archive-app -e MARIADB_ROOT_PASSWORD=root -d -p 3306:3306 -p 3000:3000 -p 8080:8080 justintang412/private:archive-app-01

To commit a new image from a running container:
docker ps
docker commit <container id> justintang412/private:archive-app-02

To run an image:
docker run --name archive-app -d -p 3306:3306 -p 3000:3000 -p 8080:8080 justintang412/private:archive-app-03

To find version of linux of the docker:
cat /etc/os-release

apt install default-jdk

To deploy to docker:
mvn package
docker cp C:\Users\tang\Desktop\pro103b\target\app-0.0.1-SNAPSHOT.jar archive-app:/root/

cp C:\Users\tang\Desktop\pro103b\docker\jdk18.sh archive-app:/etc/profile.d/

tar xvf openjdk-18_linux-x64_bin.tar.gz
mv jdk-18 /opt/
export JAVA_HOME=/opt/jdk-18
export PATH=$PATH:$JAVA_HOME/bin

To reset vscode:
delete content in %APPDATA%\Code and %USERPROFILE%\.vscode and everything is brand new.

