package Servlets;

import Controllers.NotificationController;
import Entities.Advertisement;
import Models.AdvertisementModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/EditAdServlet")
public class EditAdServlet extends HttpServlet {

    private AdvertisementModel ads_model;

    @Override
    public void init() throws ServletException {
        super.init();
        ads_model = new AdvertisementModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        List<FileItem> adData = null;
        try {
            System.out.println(1);
            adData = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            System.out.println(2);
            Advertisement ad  = parseAd(adData);
            System.out.println(3);
            if(ads_model.update(ad)){
                System.out.println(4);
                ad.setId(ad.getId());
                System.out.println(5);
                NotificationController notificationController = new NotificationController();
                System.out.println(6);
                notificationController.notify(ad);
                System.out.println(7);

                out.print("success");
            }else{
                out.print("fail");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
    }

    private Advertisement parseAd(List<FileItem> adData) {
        Advertisement ad = new Advertisement();

        ad.setId(Integer.parseInt(adData.get(0).getString()));
        ad.setType(adData.get(1).getString());
        ad.setSize(Integer.parseInt(adData.get(2).getString()));
        ad.setDescription(adData.get(3).getString());
        ad.setArea(adData.get(4).getString());
        ad.setFloor(Integer.parseInt(adData.get(5).getString()));
        ad.setLatitude(Double.parseDouble(adData.get(7).getString()));
        ad.setLongitude(Double.parseDouble(adData.get(8).getString()));
        ad.setSuspend(false);
        ad.setRate(0);
        ad.setStatus("Rent");


        return ad;
    }
}