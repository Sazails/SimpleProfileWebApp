package controller;

import dao.LoginDAOImpl;
import dao.UserDAOImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    public LoginServlet(){}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email.equals("") || password.equals("")){
            request.setAttribute("errorMessage", "Missing information.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if(!email.contains("@")){
            request.setAttribute("errorMessage", "Enter a valid email.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if(password.length() < 6){
            request.setAttribute("errorMessage", "Password must contain at least six characters.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        boolean loginStatus = new LoginDAOImpl().validate(email, password);

        if(loginStatus){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            request.setAttribute("userEmail", email);

            User user = new UserDAOImpl().getUserByEmail(email);
            request.setAttribute("userUsername", user.getUsername());
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("errorMessage", "Failed to login user. Please try again.");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
