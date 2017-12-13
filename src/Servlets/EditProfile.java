package Servlets;

import Entities.User;
import Models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditProfile", urlPatterns = "/EditProfile")
public class EditProfile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= new User();
        UserModel userModel = new UserModel();
        user.setName((request.getParameter("name")== null)?
                (String) request.getSession().getAttribute("name") :(request.getParameter("name")));
        user.setPhone((request.getParameter("phone")== null)?
                (String) request.getSession().getAttribute("phone") :(request.getParameter("phone")));
        user.setAddress((request.getParameter("address")== null)?
                (String) request.getSession().getAttribute("address") :(request.getParameter("address")));
        user.setPassword((request.getParameter("password")== null)?
                request.getParameter("oldPassword") :(request.getParameter("password")));
        user.setId((Integer) request.getSession().getAttribute("id"));
        userModel.update(user);

        HttpSession session = request.getSession();

        session.setAttribute("id", user.getId());
        session.setAttribute("name", user.getName());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("address", user.getAddress());
        session.setAttribute("phone", user.getPhone());

        response.sendRedirect("profile.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
