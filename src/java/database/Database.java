package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.Constants;

public class Database {

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static void initConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Constants.DB_CONNECTION_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            initConnection();
            return statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void destroyDb() {
        if (resultSet != null) resultSet = null;
        if (statement != null) statement = null;
        if (connection != null) connection = null;
    }
}
