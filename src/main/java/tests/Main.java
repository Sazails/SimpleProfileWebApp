package tests;

import dao.UserDAOImpl;
import model.User;

public class Main {
    public static void main(String[] args) {
        // Check if username exists within the user database
        /*
        UserDAOImpl userDAO = new UserDAOImpl();
        System.out.println(userDAO.userExists("username"));
        */

        // Get user if exists test
        /*UserDAOImpl userDAO = new UserDAOImpl();
        try{
            User user = userDAO.getUserByUsername("username");
            System.out.println(user.toString());
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }*/
    }
}
