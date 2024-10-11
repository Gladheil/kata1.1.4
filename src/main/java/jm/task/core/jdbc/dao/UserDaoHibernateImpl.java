package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    private Session session = Util.getSessionFactory().openSession();
    private Transaction transaction = session.beginTransaction();
    private Connection connection;
    {
        try {
            connection = Util.getConnection();
        } catch (SQLException ignored) { }
    }

    @Override
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

    @Override
    public void dropUsersTable() {
        try(Statement statement = connection.createStatement()) {
            String dropTableSQL = "DROP TABLE IF EXISTS USERS";
            statement.executeUpdate(dropTableSQL);
        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        return session.createQuery("from User").list();
    }

    @Override
    public void cleanUsersTable() {
        session.createQuery("delete from USERS").executeUpdate();
        transaction.commit();
    }
}
