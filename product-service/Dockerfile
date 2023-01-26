FROM gradle:jdk17-alpine
ARG JAR_FILE="product-service-0.0.1-SNAPSHOT.jar"
WORKDIR /app
COPY . /app
RUN gradle build -x test

RUN echo $(ls -1 /app)
RUN echo $(ls -1 /app/build)
RUN echo $(ls -1 /app/build/libs)

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/build/libs/product-service-0.0.1-SNAPSHOT.jar"]