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
    
FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN ./gradlew build -x test
RUN mkdir -p build/libs/dependency && (cd build/libs/dependency; jar -xf ../*.jar)

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/libs/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","product-service-0.0.1-SNAPSHOT"]
