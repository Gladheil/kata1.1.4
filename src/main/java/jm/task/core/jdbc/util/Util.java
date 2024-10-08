package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/users";
    static final String USER = "Rudolf";
    static final String PASS = "root";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

//    public static void main(String[] args) {
//        try (Connection connection = Util.getConnection();
//             Statement statement = connection.createStatement()) {
//            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
//                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
//                    "name VARCHAR(50), " +
//                    "lastName VARCHAR(50), " +
//                    "age INT" +
//                    ")";
//            statement.executeUpdate(createTableSQL);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
