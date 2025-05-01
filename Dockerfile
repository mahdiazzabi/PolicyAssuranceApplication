# Utiliser une image de base officielle OpenJDK
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré dans l'image
COPY target/policy-assurance-application.jar app.jar

# Exposer le port sur lequel l'application s'exécute
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]