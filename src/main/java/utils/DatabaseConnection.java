package main.java.utils;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {

    private static Connection connection;

    static {
        try {
            // Charger les propriétés depuis le fichier config.properties
            Properties props = new Properties();
            InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("config.properties");
            props.load(input);

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            // Établir la connexion
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion à la base de données établie avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Échec de la connexion à la base de données");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}