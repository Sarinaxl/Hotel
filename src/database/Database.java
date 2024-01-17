package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";



    public Database() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
            // Perform additional actions upon successful connection if needed
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Establishes a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Executes a SELECT query and returns a ResultSet
    public static ResultSet executeQuery(String query, Object... parameters) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement, parameters);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Executes an INSERT, UPDATE, or DELETE query
    public static int executeUpdate(String query, Object... parameters) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement, parameters);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Sets parameters for a PreparedStatement
    private static void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}
