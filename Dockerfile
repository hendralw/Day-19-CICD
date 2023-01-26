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
    
FROM openjdk:17 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
# RUN ./gradlew build || return 0 
# COPY . .
# RUN ./gradlew build
RUN gradle build -x test || return 0
COPY . .
RUN gradle build -x test

FROM openjdk:17
ENV ARTIFACT_NAME=product-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8081
CMD ["java","-jar",$ARTIFACT_NAME]
