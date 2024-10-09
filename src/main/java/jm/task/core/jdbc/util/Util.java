package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    static final String URL = "jdbc:mysql://localhost:3306/users";
    static final String USER = "Rudolf";
    static final String PASS = "root";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
