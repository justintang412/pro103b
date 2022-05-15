export JAVA_HOME=/opt/jdk-18
export PATH=$PATH:$JAVA_HOME/bin
nohup java -jar /root/app-0.0.1-SNAPSHOT.jar > /root/app.log &