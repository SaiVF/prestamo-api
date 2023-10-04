FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
ENV TZ=America/Asuncion
RUN apk add --no-cache tzdata
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD target/extraccion-0.0.1-SNAPSHOT.jar extraccion-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Xms64m", "-Xmx900m", "extraccion-0.0.1-SNAPSHOT.jar"]