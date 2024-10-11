package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("ivan", "ivanov", (byte) 20);
        User user2 = new User("alexander", "alexandrov", (byte) 25);
        User user3 = new User("olga", "veselova", (byte) 30);
        User user4 = new User("maria", "yakimova", (byte) 35);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("Дабвлен User с именем - " + user1.getName());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("Дабвлен User с именем - " + user2.getName());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("Дабвлен User с именем - " + user3.getName());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("Дабвлен User с именем - " + user4.getName());
        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
