package controller;

import dao.UserDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profileServlet")
public class ProfileServlet extends HttpServlet {

    // PROBLEM: CANT CHANGE NAME BECAUSE THE CURRENT SESSION USERNAME IS null...

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("usernameChangeInput"));
        HttpSession session = request.getSession(true);
        System.out.println(request.getParameter((String)session.getAttribute("userUsername")));
        request.getRequestDispatcher("/profile.jsp").forward(request,response);

//        if(request.getParameter("usernameChangeButton") != null){
//            if(request.getParameter("usernameChangeInput") != "" && request.getParameter("usernameChangeInput").length() > 2){
//                HttpSession session = request.getSession(false);
//                if(new UserDAOImpl().updateUsername((String)session.getAttribute("userUsername"),
//                        request.getParameter("usernameChangeInput"))) {
//                    session.setAttribute("userUsername", new UserDAOImpl().getUserByUsername(request.getParameter("usernameChangeInput")));
//                }
//
//                request.getRequestDispatcher("/profile.jsp").forward(request,response);
//            }
//        }
    }
}
