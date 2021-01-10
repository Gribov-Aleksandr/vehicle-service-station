package by.aleksandr.vehicleservicestation.dao.user;

import by.aleksandr.vehicleservicestation.entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements UserDao {
    private static List<User> users = new ArrayList<>();


    @Override
    public boolean save(User user) {
        return users.add(user);
    }

    public boolean save(List<User> users) {
        return this.users.addAll(users);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean contains(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(User user) {
        for (User eachUser : users) {
            if (eachUser.equals(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String username) {
        for (User eachUser : users) {
            if (eachUser.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (id == -1 || id == -2) {
            return false;
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String updateName(String newName, long id) {
        getById(id).setName(newName);
        return newName;
    }

    @Override
    public String updateSurname(String newSurname, long id) {
        getById(id).setSurname(newSurname);
        return newSurname;
    }

    @Override
    public String updatePassword(String password, long id) {
        getById(id).setPassword(password);
        return password;
    }
}
