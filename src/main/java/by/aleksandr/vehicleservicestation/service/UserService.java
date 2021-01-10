package by.aleksandr.vehicleservicestation.service;

import by.aleksandr.vehicleservicestation.dao.user.InMemoryUserDao;
import by.aleksandr.vehicleservicestation.dao.user.UserDaoDB;
import by.aleksandr.vehicleservicestation.entity.user.User;
import by.aleksandr.vehicleservicestation.errors.IdDoesntExist;
import by.aleksandr.vehicleservicestation.errors.IsEmptyException;
import by.aleksandr.vehicleservicestation.errors.UserByUsernameDoesntExist;
import by.aleksandr.vehicleservicestation.errors.UsersByIdDoesntMatch;

import java.util.List;

public class UserService {
    private InMemoryUserDao iMUD = new InMemoryUserDao();
    private UserDaoDB uDDB = new UserDaoDB();

    public boolean synchronizedSave(User user) {
        if (iMUD.contains(user) && uDDB.contains(user)) {
            return false;
        } else {
            uDDB.save(user);
            iMUD.save(uDDB.getByUsername(user.getUsername()));
        }
        return true;
    }

    public void synchronizedDelete(long id) {
        if (iMUD.contains(id) && uDDB.contains(id)) {
            if (iMUD.getById(id).equals(uDDB.getById(id))) {
                iMUD.delete(id);
                uDDB.delete(id);
            } else {
                throw new UsersByIdDoesntMatch();
            }
        } else {
            throw new IdDoesntExist();
        }
    }

    public void synchronizedUpdateName(String newName, long id) {
        if (iMUD.contains(id) && uDDB.contains(id)) {
            if (iMUD.getById(id).equals(uDDB.getById(id))) {
                iMUD.updateName(newName, id);
                uDDB.updateName(newName, id);
            } else {
                throw new UsersByIdDoesntMatch();
            }
        } else {
            throw new IdDoesntExist();
        }
    }

    public void synchronizedUpdatePassword(String newPassword, long id) {
        if (iMUD.contains(id) && uDDB.contains(id)) {
            if (iMUD.getById(id).equals(uDDB.getById(id))) {
                iMUD.updatePassword(newPassword, id);
                uDDB.updatePassword(newPassword, id);
            } else {
                throw new UsersByIdDoesntMatch();
            }
        } else {
            throw new IdDoesntExist();
        }
    }

    public List<User> getAllFromInMemory() {
        if (iMUD.getAll().isEmpty()) {
            throw new IsEmptyException();
        }
        return iMUD.getAll();
    }

    public List<User> getAllFromDB() {
        if (uDDB.getAll().isEmpty()) {
            throw new IsEmptyException();
        }
        return uDDB.getAll();
    }

    public void synchronize() {
        iMUD.save(uDDB.getAll());
    }

    public User getByUsernameFromInMemory(String username) {
        if (iMUD.getByUsername(username) == null) {
            throw new UserByUsernameDoesntExist();
        }
        return iMUD.getByUsername(username);
    }

    public User getByIdFromInMemory(long id) {
        if (iMUD.getById(id) == null) {
            throw new UsersByIdDoesntMatch();
        }
        return iMUD.getById(id);
    }

    public boolean contains(String username) {
        return iMUD.contains(username);
    }
}

