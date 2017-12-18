package Servlets;

import Entities.User;
import Models.UserModel;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
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
        System.out.println(request.getParameter("email"));
        System.out.println(request.getParameter("password"));
        User user = userModel.selectByEmailAndPassword(request.getParameter("email")
                , request.getParameter("password"));
        if (user == null) {
            request.setAttribute("error", true);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
        else{
            HttpSession session = request.getSession(true);
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("phone", user.getPhone());
            session.setAttribute("type", "normal"); //for test only
            response.sendRedirect("home.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
