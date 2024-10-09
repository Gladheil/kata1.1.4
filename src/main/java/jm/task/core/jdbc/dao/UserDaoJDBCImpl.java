package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    {
        try {
            connection = Util.getConnection();
        } catch (SQLException ignored) { }
    }
//Либо я что-то не понял, либо задача хочет что бы я использовал инициализатор экземпляра. Почему бы не запихать его просто в конструктор? Он ведь один у нас.
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "age INT" +
                    ")";
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Таблица не создана");
        }
    }

    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()) {
            String dropTableSQL = "DROP TABLE IF EXISTS USERS";
            statement.executeUpdate(dropTableSQL);
        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUserSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try(PreparedStatement prepareStatement = connection.prepareStatement(saveUserSQL)) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setInt(3, age);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Данные не сохранены");
        }
    }

    public void removeUserById(long id) {
        String removeUserSQL = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(removeUserSQL)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            System.out.println("Данные не удалены");
        }
    }

    public List<User> getAllUsers() {
        String selectUsersSQL = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectUsersSQL);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), (byte) resultSet.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Список не создан");
        }
        return users;
    }

    public void cleanUsersTable() {
        String cleanUsersSQL = "TRUNCATE TABLE users";
        try (PreparedStatement prepareStatement = connection.prepareStatement(cleanUsersSQL)) {
            prepareStatement.executeUpdate(cleanUsersSQL);
        } catch (SQLException e) {
            System.out.println("Таблица не очищена");
        }
    }
}
