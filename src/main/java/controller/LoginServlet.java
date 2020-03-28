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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static util.HttpUtil.setRequestAttribute;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(email.equals("") || password.equals("")){
            setRequestAttribute(request, response,
                    "errorMessage", "Missing information.",
                    "/login.jsp");
            return;
        }

        if(!email.contains("@")){
            setRequestAttribute(request, response,
                    "errorMessage", "Enter a valid email.",
                    "/login.jsp");
            return;
        }

        if(password.length() < 6){
            setRequestAttribute(request, response,
                    "errorMessage", "Password must contain at least six characters.",
                    "/login.jsp");
            return;
        }

        boolean loginStatus = new LoginDAOImpl().validate(email, password);

        if(loginStatus){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);

            User user = new UserDAOImpl().getUserByEmail(email);
            session.setAttribute("userUsername", user.getUsername());
            dispatcher.forward(request, response);
        }else{
            setRequestAttribute(request, response,
                    "errorMessage", "Failed to login user. Please try again.",
                    "/login.jsp");
        }
    }
}
