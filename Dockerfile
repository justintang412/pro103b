FROM justintang412/private:archive-app-02
COPY initdb.sql /root/
COPY target/app-0.0.1-SNAPSHOT.jar /root/
COPY start.sh /usr/bin/
EXPOSE 3306