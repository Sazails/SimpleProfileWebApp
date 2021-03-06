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
            int userRowID = preparedStatement.executeQuery().getRow();
            System.out.println(userRowID);

            // By default rows in the database start with 1, if we get no row id then it will stay 0 (false)
//            if(userRowID == 0) return new User();

//            ResultSet resultSet = preparedStatement.executeQuery();


//            return user;
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
