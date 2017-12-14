package Servlets;

import Entities.Picture;
import Entities.User;
import Models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = "/Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserModel userModel = new UserModel();
        if (userModel.selectWhere("*","email = '"+email+"'").size() == 0){
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(email);
            user.setPassword(request.getParameter("password"));
            user.setAddress(request.getParameter("address"));
            user.setPhone(request.getParameter("phone"));
            user.setPicture(new Picture("test","test"));
            System.out.println(userModel.insert(user));
            HttpSession session = request.getSession(true);
            session.setAttribute("id", user.getId());
            session.setAttribute("name", user.getName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("address", user.getAddress());
            session.setAttribute("phone", user.getPhone());

            response.sendRedirect("addPreferences.jsp");
        }
        else{
            request.setAttribute("error", true);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
