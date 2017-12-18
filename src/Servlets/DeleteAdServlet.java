package Servlets;

import Models.AdvertisementModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteAdServlet")
public class DeleteAdServlet extends HttpServlet {
    private AdvertisementModel advertisement_model;

    @Override
    public void init() throws ServletException {
        super.init();
        advertisement_model = new AdvertisementModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ad_id = Integer.parseInt(request.getParameter("id"));
        if(advertisement_model.delete(ad_id)){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("fail");
        }
    }
}
