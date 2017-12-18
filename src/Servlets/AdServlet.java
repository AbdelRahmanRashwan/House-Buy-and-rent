package Servlets;

import Entities.Advertisement;
import Models.AdvertisementModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdServlet")
public class AdServlet extends HttpServlet {
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
        Advertisement advertisement = advertisement_model.select(ad_id);
        advertisement.setSuspend(true);
        if(advertisement_model.update(advertisement)){
            response.getWriter().print("success");
        }else{
            response.getWriter().print("fail");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ad_id = Integer.parseInt(req.getParameter("id"));
        if(advertisement_model.delete(ad_id)){
            resp.getWriter().print("success");
        }else{
            resp.getWriter().print("fail");
        }
    }
}
