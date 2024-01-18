package database;

import interfaces.InsertCallbacks;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

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


    public void insertIntoTable(
            String tableName,
            String[] columns,
            String[] values,
            InsertCallbacks callbacks) {
        if (tableName.isEmpty() || columns.length == 0 || values.length == 0 || columns.length != values.length) {
            throw new IllegalArgumentException("Table name, columns, and values must not be empty, and the number of columns must match the number of values");
        }

        StringBuilder insertQuery = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesClause = new StringBuilder("VALUES (");

        for (String column : columns) {
            insertQuery.append(column).append(", ");
            valuesClause.append("?, ");
        }

        // Remove the trailing comma and space
        insertQuery.setLength(insertQuery.length() - 2);
        valuesClause.setLength(valuesClause.length() - 2);

        insertQuery.append(") ").append(valuesClause).append(")");

        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery.toString())) {

                for (int i = 0; i < values.length; i++) {
                    preparedStatement.setObject(i + 1, values[i]);
                }

                int affectedRows = preparedStatement.executeUpdate();


                callbacks.onSuccess(affectedRows);

            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            if (e.getErrorCode() == 1062) {
                callbacks.onError(new Exception("Your Email and national code Already Registred. "));
                return;
            }
            callbacks.onError(e);
        }
    }


    public ResultSet selectQuery(String table, LinkedHashMap<String, String> conditions) {
        String query = "SELECT * FROM " + table + " WHERE ";
        ArrayList<String> conditionsList = new ArrayList<>();


        for (String key : conditions.keySet()) {
            String value = conditions.get(key);
            String outString = String.join(" = ", key, "'" + value + "'");
            conditionsList.add(outString);
        }


        // Join the conditions using AND
        String conditionsString = String.join(" AND ", conditionsList);
        query += conditionsString;

        System.out.println(query);


        ResultSet resultSet = executeQuery(query);


        return null;
    }


    private ResultSet executeQuery(String query) {
        System.out.println(query);
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            // Check if there are any rows in the result set
            if (resultSet.next()) {
                // Move the cursor to the first row
                String email = resultSet.getString("email");
                System.out.println(email);
            }

            return  null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String[] getColumns(String tableName) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SHOW COLUMNS FROM " + tableName);
             ResultSet resultSet = statement.executeQuery()) {

            List<String> columnList = new ArrayList<>();

            while (resultSet.next()) {
                String columnName = resultSet.getString("Field");
                columnList.add(columnName);
            }

            // Convert the list to an array
            String[] columnsArray = columnList.toArray(new String[0]);

            return columnsArray;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
