package database;

import interfaces.InsertCallbacks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < columns.length; i++) {
            insertQuery.append(columns[i]).append(", ");
            valuesClause.append("?, ");
        }

        // Remove the trailing comma and space
        insertQuery.setLength(insertQuery.length() - 2);
        valuesClause.setLength(valuesClause.length() - 2);

        insertQuery.append(") ").append(valuesClause).append(")");

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery.toString())) {

            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }

            int affectedRows = preparedStatement.executeUpdate();


            callbacks.onSuccess(affectedRows);

        } catch (SQLException e) {
            callbacks.onError(e);
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


    public String value(String value) {
        return value + ", ";
    }

}
