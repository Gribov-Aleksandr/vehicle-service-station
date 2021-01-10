package by.aleksandr.vehicleservicestation.dao.user;

import by.aleksandr.vehicleservicestation.entity.user.Role;
import by.aleksandr.vehicleservicestation.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDB implements UserDao {
    Connection connection;

    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/vehicleServiceStation", "postgres", "1111");
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }


    @Override
    public boolean save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values ( default, ?, ?, ?, ?, ? )");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String username = resultSet.getString(2);
                String pass = resultSet.getString(3);
                String name = resultSet.getString(4);
                String surname = resultSet.getString(5);
                Role role = Role.valueOf(resultSet.getString(6));
                User user = new User(id, username, pass, name, surname, role);
                users.add(user);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }


    @Override
    public User getById(long id) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        Role.valueOf(resultSet.getString(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    Role.valueOf(resultSet.getString(6)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return user;
    }


    @Override
    public boolean contains(long id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select id from users");
            while (resultSet.next()) {
                if (resultSet.getLong(1) == id) {
                    return true;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        List<User> users = getAll();
        for (int i = 0; i < users.size(); i++) {
            if (user.equals(users.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String username) {
        List<User> users = getAll();
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException throwable) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public String updateName(String newName, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ? where id = ?");
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newName;
    }

    @Override
    public String updateSurname(String newSurname, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set surname = ? where id = ?");
            preparedStatement.setString(1, newSurname);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return newSurname;
    }

    @Override
    public String updatePassword(String password, long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where id = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return password;
    }
}

