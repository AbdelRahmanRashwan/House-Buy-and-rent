package Servlets;

import Entities.User;
import Models.UserModel;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserModel userModel = new UserModel();
        User user = userModel.selectByEmailAndPassword(request.getAttribute("email").toString()
                , request.getAttribute("password").toString());
        if (user == null) {
            request.setAttribute("error", true);
            response.sendRedirect("login.jsp");
        }
        else{
            HttpSession session = request.getSession(true);
            session.setAttribute("name", user.getName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("phone", user.getPhone());
            response.sendRedirect("home.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
