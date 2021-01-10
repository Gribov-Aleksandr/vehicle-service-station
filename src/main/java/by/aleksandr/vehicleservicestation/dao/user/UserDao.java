package by.aleksandr.vehicleservicestation.dao.user;

import by.aleksandr.vehicleservicestation.entity.user.User;

import java.util.List;

public interface UserDao {

    boolean save(User user);

    List<User> getAll();

    User getById(long id);

    User getByUsername(String username);

    boolean contains(long id);

    boolean contains(User user);

    boolean contains(String username);

    boolean delete(long id);

    String updateName(String newName, long id);

    String updateSurname(String newSurname, long id);

    String updatePassword(String password, long id);
}
