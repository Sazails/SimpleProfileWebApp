package dao;

import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean validate(String email, String password) {
        try{
            Connection connection = ConnectionUtil.createTempConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Users WHERE user_email=? AND user_password=?"
            );
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet set = preparedStatement.executeQuery();
            boolean exists = set.next();

            set.close();
            preparedStatement.close();
            connection.close();
            return exists; // Return true if user does exist.
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
