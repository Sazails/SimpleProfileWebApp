package dao;

import model.User;

public interface UserDAO {
    boolean registerUser(User user);
    boolean userExists(String username); // Use username because the database can only hold one type of username ignoring uppercase/lowercase differences
//    boolean updateUser(User user);
//    User getUserByUsername(String username);
//    User getUserByEmail(String email);
}
