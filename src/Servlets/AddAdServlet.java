package Servlets;

import Entities.Advertisement;
import Entities.User;
import Models.AdvertisementModel;
import Models.HouseModel;
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
    private HouseModel house_model;

    @Override
    public void init() throws ServletException {
        super.init();
        ads_model = new AdvertisementModel();
        house_model = new HouseModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        HttpSession current_session = request.getSession();
        UserModel userModel = new UserModel();

        String user_name = current_session.getAttribute("name").toString();
        Advertisement ad = new Advertisement();
        User user = userModel.select(user_name);

        House house = parseHouse(request);
        //house.setId(id); house_model.insert(house) should return the inserted house id


        ad.setType(request.getParameter("type"));
        ad.setSuspended(false);
        ad.setRate(0);
        ad.setHouse(house);
        ad.setUser(user);

        if(ads_model.insert(ad)){
            out.print("success");
        }else{
            out.print("fail");
        }
    }

    private House parseHouse(HttpServletRequest houseData) {
        House house = new House();

        house.setLatitude(Double.parseDouble(houseData.getParameter("lat")));
        house.setLongitude(Double.parseDouble(houseData.getParameter("lng")));
        house.setFloor(Integer.parseInt(houseData.getParameter("floor")));
        house.setSize(Integer.parseInt(houseData.getParameter("space")));
        house.setDescription(houseData.getParameter("description"));
        house.setArea(houseData.getParameter("area"));


        return house;
    }
}
