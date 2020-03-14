package dao;

import model.User;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{
    @Override
    public boolean registerUser(User user){
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        try{
            Connection connection = DatabaseUtil.createConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (user_username, user_email, user_password) VALUES (?, ?, ?)"
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int state = preparedStatement.executeUpdate();

            connection.close();

            if(state != 0){
                return true; // Registration went with no errors
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Something went wrong with the registration
    }

    @Override
    public User getUserByUsername(String username) {
        try{
            Connection connection = DatabaseUtil.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Users WHERE user_username=?"
            );
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = new User(
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3)
            );

            return user;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try{
            Connection connection = DatabaseUtil.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Users WHERE user_email=?"
            );
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = new User(
                    resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getString(3)
            );
            return user;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean userExists(String username) {
        try{
            Connection connection = DatabaseUtil.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Users WHERE user_username=?"
            );
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                System.out.println("User with username " + "'" + username + "' not found.");
                return false;
            }

            connection.close();
            System.out.println("User with username " + "'" + username + "' exists.");
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
