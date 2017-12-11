package Servlets;


import Entities.Advertisement;
import Entities.Picture;
import Models.AdvertisementModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;
import java.util.List;

import static Servlets.GetFilteredAdsServlet.getString;

@WebServlet("/GetAdsServlet")
public class GetAdsServlet extends HttpServlet {

    private AdvertisementModel ads_model;

    @Override
    public void init() throws ServletException {
        super.init();
        ads_model = new AdvertisementModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Advertisement> ads_list = ads_model.selectAll();

        response.setContentType("application/json");

        Gson gson = new Gson();

        for(Advertisement ad:ads_list) {
            for (Picture pic:ad.getPictures()) {
                pic.imageBase64 = getImageBytes(pic.path);
            }
        }

        String json = gson.toJson(ads_list);

        response.getWriter().print(json);
    }

    private String getImageBytes(String path) throws IOException {
        return getString(path);
    }
}
