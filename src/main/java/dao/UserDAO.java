package dao;

import model.User;

public interface UserDAO {
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    boolean registerUser(User user);
    boolean existsUsername(String username);
    boolean existsEmail(String email);
    boolean updateUsername(String currentUsername, String newUsername);
}
