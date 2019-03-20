package com.elte.issuetracker;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

public class testConnection {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-75-230-41.eu-west-1.compute.amazonaws.com:5432/dbqup43qrkl1gn?sslmode=require", "ntfvvinbaynbmm", "6ab9358c95f8b7745069fa96b9f1e0ad5c7baf386d2d42d1246f60f295de68aa")) {

            System.out.println("Java JDBC Heroku PostgreSQL Database Connection Test");
            // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within
            // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
//          Class.forName("org.postgresql.Driver");

            System.out.println("Connected to PostgreSQL database!");
            Statement statement = connection.createStatement();
            System.out.println("Reading records...");
            System.out.printf("%-30.30s  %-30.30s%n", "Id", "Title");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM issue");
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("id"), resultSet.getString("title"));
            }

        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}
