#FROM openjdk:14-alpine
FROM openjdk:11
#RUN apk add --no-cache net-tools
#RUN apk add --no-cache netcat-openbsd
EXPOSE 8083
EXPOSE 5005
ENV JAVA_OPTS -Xmx512m
#RUN mkdir /deploy
#Set app home folder
ENV APP_HOME /app
#Create base app folder
RUN mkdir $APP_HOME
#Create folder to save configuration files
RUN mkdir $APP_HOME/config
#Create folder with application logs
RUN mkdir $APP_HOME/logs
VOLUME $APP_HOME/logs
VOLUME $APP_HOME/config
WORKDIR $APP_HOME
#ADD target/*.jar /deploy/app.jar
ADD target/*.jar app.jar
#CMD java ${JAVA_OPTS} -Dserver.port=8083 -Djava.security.egd=file:/dev/./urandom -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar /deploy/app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dserver.port=8083 -Djava.security.egd=file:/dev/./urandom -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005 -jar app.jar" ]
#Second option using shell form:
#ENTRYPOINT exec java $JAVA_OPTS -jar app.jar $0 $@