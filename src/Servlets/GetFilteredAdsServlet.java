package Servlets;

import Entities.Advertisement;
import Entities.Picture;
import Models.AdvertisementModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@WebServlet("/GetFilteredAdsServlet")
public class GetFilteredAdsServlet extends HttpServlet {

    private AdvertisementModel ads_model;

    @Override
    public void init() throws ServletException {
        super.init();
        ads_model = new AdvertisementModel();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String filters_json_string = request.getParameter("filter");
        JsonObject filters_json = new JsonParser().parse(filters_json_string).getAsJsonObject();
        String query = build_query(filters_json);

        List<Advertisement> ads_list;
        System.out.println(query);
        if(query.isEmpty())
            ads_list = ads_model.selectAll();
        else ads_list = ads_model.selectWhere("*", query);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    private String build_query(JsonObject filters_json) {
        StringBuilder query = new StringBuilder();

        int index = 0;
        for(String key:filters_json.keySet()){
            if(key.equals("size")) continue;

            String val = filters_json.get(key).getAsString();
            if(index < filters_json.size() - 1 && (query.length() > 0) &&!(val.isEmpty()))
                query.append(" and ");

            if(!(val.isEmpty()))
                query.append(key).append(" = '").append(val).append("'");

            index++;
        }

        try {
            JsonObject size = filters_json.get("size").getAsJsonObject();
            int size_lower = size.get("lower_bound").getAsInt();
            query.append((query.length() == 0) ? "" : " and ");
            query.append("size" + " >= ").append(size_lower);
        }catch (Exception ignored){}

        try {
            JsonObject size = filters_json.get("Size").getAsJsonObject();
            int size_upper = size.get("upper_bound").getAsInt();
            query.append((query.length() == 0) ? "" : " and ");
            query.append("Size" + " <= ").append(size_upper);
        }catch (Exception ignored){}

        System.out.println(query.toString());

        return query.toString();
    }


    private String getImageBytes(String path) throws IOException {
        return getString(path);
    }

    static String getString(String path) throws IOException {
        System.out.println(path);
        File image = new File(path);
        FileInputStream in = new FileInputStream(image);
        byte[] imageBytes = new byte[(int)image.length()];
        in.read(imageBytes);

        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
