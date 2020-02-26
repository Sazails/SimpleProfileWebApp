package dao;

import model.User;
import util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public boolean userExists(String username) {
        try{
            Connection connection = DatabaseUtil.createConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Users user_username=?"
            );
            preparedStatement.setString(1, username);

            connection.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
