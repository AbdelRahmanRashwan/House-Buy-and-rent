package Servlets;

import Entities.Comment;
import Models.AdvertisementModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.xml.internal.bind.v2.TODO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {

    private AdvertisementModel ads_model;

    @Override
    public void init() throws ServletException {
        super.init();
        ads_model = new AdvertisementModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment_json_string = request.getParameter("comment");
        JsonObject comment_json = new JsonParser().parse(comment_json_string).getAsJsonObject();

        String comment_val = comment_json.get("comment_val").getAsString();
        HttpSession session = request.getSession();

        int ad_id = comment_json.get("id").getAsInt();
        int user_id = Integer.parseInt(session.getAttribute("id").toString());

        if(ads_model.comment(ad_id, user_id, comment_val)){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("fail");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
