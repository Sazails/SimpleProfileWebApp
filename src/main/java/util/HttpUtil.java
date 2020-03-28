package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtil {
    public static void setRequestAttribute(HttpServletRequest request, HttpServletResponse response, String attributeName, String message, String pageName) throws ServletException, IOException {
        try{
            request.setAttribute(attributeName, message);
            request.getRequestDispatcher(pageName).forward(request, response);
        }catch (ServletException ex){
            ex.printStackTrace();
        }
    }
}
