package Servlets;

import Entities.Preferences;
import Models.PreferencesModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AddPreferences", urlPatterns = "/AddPreferences")
public class AddPreferences extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Preferences preferences = new Preferences();
        preferences.setArea(request.getParameter("area"));
        preferences.setFloor(Integer.parseInt( request.getParameter("floor")));
        preferences.setSize(Integer.parseInt (request.getParameter("size")));
        preferences.setType( request.getParameter("type"));
        preferences.setUserID((Integer) request.getSession().getAttribute("id"));

        PreferencesModel preferencesModel = new PreferencesModel();
        int user_id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        ArrayList<Preferences> tmp=(ArrayList<Preferences>) preferencesModel.selectWhere("*","id = "+user_id); 
        
        if (tmp.size()!=0&& tmp.get(0) != null){
            preferencesModel.update(preferences);
            ((Preferences)request.getSession().getAttribute("preferences")).setArea(request.getParameter("area"));
            ((Preferences)request.getSession().getAttribute("preferences")).setFloor(Integer.parseInt( request.getParameter("floor")));
            ((Preferences)request.getSession().getAttribute("preferences")).setSize(Integer.parseInt (request.getParameter("size")));
            ((Preferences)request.getSession().getAttribute("preferences")).setType(request.getParameter("type"));
        }
        else{
            preferencesModel.insert(preferences);
        }
        response.sendRedirect("profile.jsp");

    }
}
