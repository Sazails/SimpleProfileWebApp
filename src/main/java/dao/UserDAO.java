package dao;

import model.User;

public interface UserDAO {
    boolean registerUser(User user);
    boolean userExists(String username);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
//    boolean updateUser(User user);
}
