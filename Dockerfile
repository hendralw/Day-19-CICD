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
    
FROM gradle:jdk17-alpine as compile
COPY . /home/source/java
WORKDIR /home/source/java
# Default gradle user is `gradle`. We need to add permission on working directory for gradle to build.
USER root
RUN chown -R gradle /home/source/java
USER gradle
RUN gradle clean build

FROM gradle:jdk17-alpine
WORKDIR /home/application/java
COPY --from=compile "/home/source/java/build/libs/product-service-0.0.1-SNAPSHOT.jar" .
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/home/application/java/product-service-0.0.1-SNAPSHOT.jar"]



