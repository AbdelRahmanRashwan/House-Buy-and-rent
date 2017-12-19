package Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("id", null);
        session.setAttribute("name", null);
        session.setAttribute("email", null);
        session.setAttribute("address", null);
        session.setAttribute("phone", null);
        session.setAttribute("type", null);
        response.sendRedirect("home.jsp");
    }
}
