# FROM gradle:jdk17-alpine AS gradle
# ARG JAR_FILE="product-service-0.0.1-SNAPSHOT.jar"
# WORKDIR /app
# COPY . /app
# RUN gradle build -x test

# FROM scratch
# WORKDIR /app
# COPY --from=gradle /app /app/

# RUN echo $(ls -1 /app)
# RUN echo $(ls -1 /app/build)
# RUN echo $(ls -1 /app/build/libs)

# EXPOSE 8081
# ENTRYPOINT ["java", "-jar", "/app/build/libs/product-service-0.0.1-SNAPSHOT.jar"]

# using multistage docker build
# ref: https://docs.docker.com/develop/develop-images/multistage-build/
    
# temp container to build using gradle
# FROM gradle:5.3.0-jdk-alpine AS TEMP_BUILD_IMAGE
FROM gradle:eclipse-temurin:17-jdk-alpine AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
  
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src
    
# RUN gradle build -x test || return 0
# COPY . .
# RUN gradle clean build
RUN gradle build || return 0
COPY . .
RUN gradle clean build
    
# actual container
FROM gradle:eclipse-temurin:17-jdk-alpine
ENV ARTIFACT_NAME=product-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
    
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
    
EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
