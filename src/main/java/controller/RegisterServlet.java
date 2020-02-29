package controller;

import dao.UserDAOImpl;
import model.User;

import javax.servlet.RequestDispatcher;
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
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        if(email.equals("") || username.equals("") || password.equals("") || passwordConfirm.equals("")){
            request.setAttribute("errorMessage", "Missing information.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if(!email.contains("@")){
            request.setAttribute("errorMessage", "Enter a valid email.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if(username.length() < 3){
            request.setAttribute("errorMessage", "Username must contain at least three characters.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if(password.length() < 6){
            request.setAttribute("errorMessage", "Password must contain at least six characters.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if(!password.equals(passwordConfirm)){
            request.setAttribute("errorMessage", "Passwords do not match, please check and try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        boolean userRegistered = new UserDAOImpl().registerUser(user);

        if(userRegistered){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            request.setAttribute("userEmail", email);
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("errorMessage", "Failed to register user. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }
}
