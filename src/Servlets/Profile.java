package Servlets;

import Entities.Advertisement;
import Entities.Preferences;
import Models.AdvertisementModel;
import Models.PreferencesModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Profile", urlPatterns = "/Profile")
public class Profile extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreferencesModel preferencesModel = new PreferencesModel();
        int user_id = Integer.parseInt(request.getSession().getAttribute("id").toString());
        Preferences preferences = preferencesModel.selectWhere("*","id = "+user_id).get(0);
        AdvertisementModel advertisementModel = new AdvertisementModel();
        List<Advertisement> advertisements = advertisementModel.selectWhere(null,
                "userID = "+(int)request.getSession().getAttribute("userID"));
//        Preferences preferences = new Preferences(1, 1, 200, 2,"villa","giza");
//        List<Advertisement> advertisements = new ArrayList<>();
//        House house = new House(1, 100, "hamada",4, "good","villa", "giza");
//        Advertisement advertisement = new Advertisement(1, house, 1, "buy");
//        advertisements.add(advertisement);
//        advertisements.add(advertisement);
//        advertisements.add(advertisement);
//        advertisements.add(advertisement);
//        advertisements.add(advertisement);
        request.getSession().setAttribute("preferences", preferences);
        request.setAttribute("advertisements", advertisements);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
