# Imagem base do OpenJDK para o Java 17
FROM openjdk:17-jdk-slim

LABEL authors="sergio"

# Diretório de trabalho dentro da imagem
WORKDIR /app

# Copia o arquivo JAR da aplicação para a imagem
COPY target/cartoes-0.0.1-SNAPSHOT.jar app.jar

# Expondo a porta em que a aplicação Spring Boot será executada
EXPOSE 8080

# Comando para iniciar a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]
