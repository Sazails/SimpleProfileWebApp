package dao;

import model.User;
import util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    // Add new user to the users database
    @Override
    public boolean registerUser(User user) {
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        try {
            Connection connection = ConnectionUtil.createTempConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (user_username, user_email, user_password) VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int state = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            if (state != 0) {
                return true; // Registration went with no errors
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false; // Something went wrong with the registration
    }

    // I currently don't know how to accept multiple field changes for the preparedStatement so I only use one input here
    private User getUser(String singlePreparedStatement, int fieldID, String input) throws SQLException {
        try{
            Connection connection = ConnectionUtil.createTempConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(singlePreparedStatement);
            preparedStatement.setString(fieldID, input);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            User user = new User(
                    set.getString(2),
                    set.getString(1),
                    set.getString(3)
            );

            set.close();
            preparedStatement.close();
            connection.close();

            return user;
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            return getUser("SELECT * FROM Users WHERE user_name=?", 1, username);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return getUser("SELECT * FROM Users WHERE user_email=?", 1, email);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existsUsername(String username) {
        try{
            if(getUserByUsername(username) == null){
                System.out.println("User with username " + username + " not found.");
                return false;
            }else{
                System.out.println("User with username " + username + " found.");
                return true;
            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
            System.out.println("Couldn't get user by username " + username);
            return false;
        }
    }

    @Override
    public boolean existsEmail(String email) {
        try{
            if(getUserByEmail(email) == null){
                System.out.println("User with email " + email + " not found.");
                return false;
            }else{
                System.out.println("User with email " + email + " found.");
                return true;
            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
            System.out.println("Couldn't get user by email " + email);
            return false;
        }
    }

    @Override
    public boolean updateUsername(String currentUsername, String newUsername) {
        if (existsUsername(currentUsername)) {
            if (!existsUsername(newUsername)) {
                try {
                    Connection connection = ConnectionUtil.createTempConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "UPDATE TABLE users SET user_username=? WHERE user_username=?"
                    );
                    preparedStatement.setString(1, newUsername);
                    preparedStatement.setString(2, currentUsername);
                    preparedStatement.executeUpdate();

                    preparedStatement.close();
                    connection.close();

                    System.out.println("User with username: " + currentUsername + " has been replaced with a new username: " + newUsername);
                    return true;
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                    return false;
                }
            } else {
                System.out.println("New username: " + newUsername + " already exists, please choose another one.");
                return false;
            }
        } else {
            System.out.println("Username: " + currentUsername + " was not found within the database.");
            return false;
        }
    }
}
