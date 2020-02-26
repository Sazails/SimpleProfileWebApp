package controller;

import dao.LoginDAOImpl;

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

        boolean loginStatus = new LoginDAOImpl().validate(email, password);

        if(loginStatus){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            request.setAttribute("userEmail", email);
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("errorMessage", "Failed to login user. Please try again.");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
