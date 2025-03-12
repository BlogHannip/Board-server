# 1. Java 17 + Maven 빌드
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# 2. 소스 코드 복사 후 빌드
COPY . .
RUN mvn clean package -DskipTests

# 3. JAR 파일 실행
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
