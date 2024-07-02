package config;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class DatabaseConnector {

    private static final String USERNAME = System.getProperty("username");
    private static final String PASSWORD = System.getProperty("password");
    public static Connection connection;


    @SneakyThrows
    public static void getConnection(String host) {
        String databaseUrl = System.getProperty(host);

        if (databaseUrl == null || databaseUrl.isEmpty()) {
            log.error("Can not connect to database, if databaseUrl = {}", databaseUrl);
            log.error("Check your application.properties");
            throw new IllegalArgumentException("Database URL is not set or is empty");
        }

        try {
            connection = DriverManager.getConnection(databaseUrl, USERNAME, PASSWORD);
            log.info("Connection database is successful! {}", databaseUrl);
        } catch (CommunicationsException e) {
            log.error("Failed to establish connection with database url: " + databaseUrl);
        }
    }
}