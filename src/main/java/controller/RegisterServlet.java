package controller;

import dao.UserDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    public RegisterServlet(){}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TESTING");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        if(!password.equals(passwordConfirm)){
            request.setAttribute("errorMessage", "Passwords do not match, please check and try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        UserDAOImpl userDAO = new UserDAOImpl();

        boolean userRegistered = userDAO.registerUser(user);

        if(userRegistered){
            request.getRequestDispatcher("/home.jsp").forward(request,response);
        }else{
            request.setAttribute("errorMessage", "Failed to register user. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }
}
