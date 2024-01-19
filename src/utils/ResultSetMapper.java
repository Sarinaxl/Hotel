package utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetMapper<T> {

    public T mapResultSetToObject(ResultSet resultSet, Class<T> clazz) throws SQLException {
        try {
            T resultObject = clazz.getDeclaredConstructor().newInstance();

            // Get the ResultSet metadata to retrieve column names
            var metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Iterate through the ResultSet columns
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);

                // Use reflection to set the field value in the object
                try {
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(resultObject, resultSet.getObject(columnName));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            return resultObject;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Error mapping ResultSet to object", e);
        }
    }
}
