package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    // создание таблицы с юзерами
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    // удалить таблицу
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
    }

    // сохранить юзера (добавить в таблицу)
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name, lastName, age);

    }
    // удалить из таблицы юзера под id
    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);

    }
    // вернуть списком всех юзеров из таблицы
    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    // очистить таблицу пользователей
    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();

    }
}
