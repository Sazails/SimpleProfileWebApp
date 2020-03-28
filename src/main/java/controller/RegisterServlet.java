package controller;

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

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        // Simple checks to see if the minimum requirements are met.
        if(email.equals("") || username.equals("") || password.equals("") || passwordConfirm.equals("")){
            setRequestAttribute(request, response,
                    "errorMessage", "Missing information.",
                    "/register.jsp");
            return;
        }

        if(!email.contains("@")){
            setRequestAttribute(request, response,
                    "errorMessage", "Enter a valid email.",
                    "/register.jsp");
            return;
        }

        if(username.length() < 3){
            setRequestAttribute(request, response,
                    "errorMessage", "Username must contain at least three characters.",
                    "/register.jsp");
            return;
        }

        if(password.length() < 6){
            setRequestAttribute(request, response,
                    "errorMessage", "Password must contain at least six characters.",
                    "/register.jsp");
            return;
        }

        if(!password.equals(passwordConfirm)){
            setRequestAttribute(request, response,
                    "errorMessage", "Passwords do not match, please check and try again.",
                    "/register.jsp");
            return;
        }

        // Check to see if user email or username is not in use.
        try{
            // Check if same email does not exist in the database as only one type of it is allowed by the database (unique)
            User user = new UserDAOImpl().getUserByEmail(email);
            if(user.getEmail() != ""){
                setRequestAttribute(request, response,
                        "errorMessage", "Failed to register user. Email already exists.",
                        "/register.jsp");
                return;
            }

            if(user.getUsername() != ""){
                setRequestAttribute(request, response,
                        "errorMessage", "Failed to register user. Username already exists.",
                        "/register.jsp");
                return;
            }
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }

        // Set the new user details for the user account creation and return registered status
        boolean userRegistered = new UserDAOImpl().registerUser(
                new User(
                        email,
                        username,
                        password
                )
        );

        // Handle page loading and error message for the returned state of userRegistered.
        if(userRegistered){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);
            session.setAttribute("userUsername", username);
            dispatcher.forward(request, response);
        }else{
            setRequestAttribute(request, response,
                    "errorMessage", "Failed to register user. Please try again.",
                    "/register.jsp");
        }
    }
}
