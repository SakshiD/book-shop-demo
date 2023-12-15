FROM eclipse-temurin:17

COPY ./build/libs/book-shop-demo-1.0-SNAPSHOT.jar ./service.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "service.jar"]