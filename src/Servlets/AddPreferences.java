package Servlets;

import Entities.Preferences;
import Models.PreferencesModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPreferences", urlPatterns = "/AddPreferences")
public class AddPreferences extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Preferences preferences = new Preferences();
        preferences.setArea((String) request.getAttribute("area"));
        preferences.setFloor((Integer) request.getAttribute("floor"));
        preferences.setSize((Integer) request.getAttribute("size"));
        preferences.setType((String) request.getAttribute("type"));
        preferences.setUserID((Integer) request.getSession().getAttribute("id"));

        PreferencesModel preferencesModel = new PreferencesModel();
        int user_id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        if (preferencesModel.selectWhere("*","id = "+user_id).get(0) != null){
            preferencesModel.update(preferences);
            ((Preferences)request.getSession().getAttribute("preferences")).setArea((String) request.getAttribute("area"));
            ((Preferences)request.getSession().getAttribute("preferences")).setFloor((int) request.getAttribute("floor"));
            ((Preferences)request.getSession().getAttribute("preferences")).setSize((int) request.getAttribute("size"));
            ((Preferences)request.getSession().getAttribute("preferences")).setType((String) request.getAttribute("type"));
        }
        else{
            preferencesModel.insert(preferences);
        }
        response.sendRedirect("profile.jsp");

    }
}
