package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    // создание таблицы с юзерами (работает)
    public void createUsersTable() {

        String sql = "CREATE TABLE user (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`))";

        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("создал таблицу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // удалить таблицу (работает)
    public void dropUsersTable() {
        String sql = "DROP TABLE if EXISTS USER";

        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("удалил таблицу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    // сохранить юзера (добавить в таблицу) (работает)
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (id, name, lastName, age) VALUES (NULL, ?, ?, ?)";

        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql); // создаем заявление

            preparedStatement.setString(1,name);// в SQL запросе устаналиваем значения
            preparedStatement.setString(2,lastName);// в SQL запросе устаналиваем значения
            preparedStatement.setLong(3,age);// в SQL запросе устаналиваем значения
            preparedStatement.executeUpdate(); // метод возвращает количество измененных строк

            System.out.println("добавил в таблицу сроку");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // удалить из таблицы юзера под id (работает)
    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE ID=?";

        Connection connection = Util.getConnection(); // возвращаем коннект
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql); // создаем заявление
            preparedStatement.setLong(1,id); // в SQL указываем нужный индекс (устаналиваем значения)
            preparedStatement.executeUpdate(); // метод возвращает количество измененных строк

            System.out.println("удалил из таблицы сроку по id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // вернуть списком всех юзеров из таблицы
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM USER;";

        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;

        List<User> users = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            // задаем поля для объекта User (работает)
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                users.add(user);
            }

            System.out.println("создан объект из строки таблицы");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    // очистить таблицу пользователей (работает)
    public void cleanUsersTable() {
        String sql = "DELETE FROM USER;";

        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            System.out.println("таблица очищена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
