package dao;

import model.User;

public interface UserDAO {
    boolean registerUser(User user);
    boolean userExists(String username);
//    boolean updateUser(User user);
//    User getUserByUsername(String username);
//    User getUserByEmail(String email);
}
