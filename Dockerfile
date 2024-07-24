# Etapa de construção
FROM maven:3.8.4-openjdk-17-slim AS build

# Cria o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e resolve as dependências do projeto (usando o cache do Docker)
COPY pom.xml /app/pom.xml
RUN mvn dependency:go-offline

# Copia todos os arquivos de código fonte e compila o projeto
COPY src /app/src
RUN mvn clean package -DskipTests

# Etapa de implantação
FROM openjdk:17-jdk-slim AS deploy

# Define o diretório de trabalho
WORKDIR /app

# Copia o jar do build para o diretório de trabalho na etapa de deploy
COPY --from=build /app/target/*.jar /app/tc-backend-api.jar

# Define o comando de entrada
ENTRYPOINT ["java", "-jar", "tc-backend-api.jar"]
