package tests;

import dao.UserDAOImpl;

public class Main {
    public static void main(String[] args) {
        // Check if username exists within the user database
        /*
        UserDAOImpl userDAO = new UserDAOImpl();
        System.out.println(userDAO.userExists("username"));
        */

        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.getUserByUsername("username");
    }
}
