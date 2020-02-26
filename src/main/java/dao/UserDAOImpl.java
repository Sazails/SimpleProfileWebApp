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

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DatabaseUtil.createConnection();
            String query = "INSERT INTO users (user_username, user_email, user_password) VALUES (?, ?, ?)"; // details to insert into USERS table
            if(connection != null){
            }
            System.out.println("WORKING");

            preparedStatement = connection.prepareStatement(query);
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DatabaseUtil.createConnection();
            String query = "SELECT * FROM Users user_username=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
