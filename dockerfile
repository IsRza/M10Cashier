FROM azul/zulu-openjdk-alpine:21 as builder

WORKDIR /space

COPY . /space

RUN ./gradlew bootJar --no-daemon

### RUNNER IMAGE ###
FROM azul/zulu-openjdk-alpine:21

WORKDIR /app

COPY --from=builder /space/build/libs/M10Cashier-0.0.1.jar /app/app.jar
COPY --from=builder /space/src/main/resources/ /app/

EXPOSE 8181

CMD [ "java", "-jar", "app.jar" ]
