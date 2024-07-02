# Используем базовый образ с Java
FROM openjdk:11-jre-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR-файл в контейнер
COPY aqa-shop.jar /app/aqa-shop.jar

# Указываем порт, который будет использоваться приложением
EXPOSE 8080

# Запускаем приложение
CMD ["java", "-jar", "aqa-shop.jar"]