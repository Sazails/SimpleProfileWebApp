package dao;

import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean validate(String email, String password) {
        try{
            Connection connection = DatabaseUtil.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Users WHERE user_email=? AND user_password=?"
            );

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            connection.close();

            return rs.next(); // Return true if user does exist.
        }catch (Exception e){
            e.printStackTrace();
        }

        return false; // Something went wrong, just return false.
    }
}
