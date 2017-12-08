package Servlets;

import Entities.Advertisement;
import Entities.User;
import Models.AdvertisementModel;
import Models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddAdServlet")
public class    AddAdServlet extends HttpServlet {

    private AdvertisementModel ads_model;

    @Override
    public void init() throws ServletException {
        super.init();
        ads_model = new AdvertisementModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException{
        PrintWriter out = response.getWriter();
        HttpSession current_session = request.getSession();
        UserModel userModel = new UserModel();

        int user_id= Integer.parseInt(current_session.getAttribute("id").toString());
        User user = userModel.select(user_id);

        Advertisement ad  = parseAd(request);

        ad.setUser(user);

        if(ads_model.insert(ad)){
            out.print("success");
        }else{
            out.print("fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    private Advertisement parseAd(HttpServletRequest adData) {
        Advertisement ad = new Advertisement();

        ad.setType(adData.getParameter("type"));
        ad.setSuspended(false);
        ad.setRate(0);
        ad.setLatitude(Double.parseDouble(adData.getParameter("lat")));
        ad.setLongitude(Double.parseDouble(adData.getParameter("lng")));
        ad.setFloor(Integer.parseInt(adData.getParameter("floor")));
        ad.setSize(Integer.parseInt(adData.getParameter("space")));
        ad.setDescription(adData.getParameter("description"));
        ad.setArea(adData.getParameter("area"));


        return ad;
    }
}
