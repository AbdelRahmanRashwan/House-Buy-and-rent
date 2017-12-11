package Servlets;

import Controllers.NotificationController;
import Entities.Advertisement;
import Entities.Picture;
import Entities.User;
import Models.AdvertisementModel;
import Models.UserModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;




@WebServlet("/AddAdServlet")
public class    AddAdServlet extends HttpServlet {

    private AdvertisementModel ads_model;
    private NotificationController notificationController;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.apache.commons.io.IOUtils");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ads_model = new AdvertisementModel();
        notificationController = new NotificationController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException{
        PrintWriter out = response.getWriter();
        HttpSession current_session = request.getSession();

        List<FileItem> adData = null;
        try {
            adData = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            UserModel userModel = new UserModel();

            int user_id= 1;
            User user = userModel.select(user_id);

            Advertisement ad  = parseAd(adData);

            List<Picture> pictures = new ArrayList<>();
            pictures.add(saveImagesToDisk(adData,out));
            ad.setPictures(pictures);
            ad.setUser(user);

            if(ads_model.insert(ad)){

                int ad_id = ads_model.selectWhere("*","user_id = "+user_id).get(0).getId();
                ad.setId(ad_id);

                notificationController.notify(ad);

                out.print("success");
            }else{
                out.print("fail");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }


    }

    private Picture saveImagesToDisk(List<FileItem> items, PrintWriter out) {

        try{
            for(FileItem item:items){
                if(!(item.isFormField())&&item.getContentType().startsWith("image")) {
                    String contentType = '.'+item.getContentType().split("/")[1];
                    File uploadDir = new File("Images/");
                    File file = File.createTempFile("img", contentType, uploadDir);
                    item.write(file);

                    out.println("File Saved Successfully");
                    return new Picture(file.getAbsolutePath(), null);
                }
            }
        }
        catch(FileUploadException e){

            out.println("upload fail "+e.getMessage());
        }
        catch(Exception ex){

            out.println("Error "+ex.getMessage());
        }

        return null;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response){

    }

    private Advertisement parseAd(List<FileItem> adData) {
        Advertisement ad = new Advertisement();

        ad.setType(adData.get(0).getString());
        ad.setSize(Integer.parseInt(adData.get(1).getString()));
        ad.setDescription(adData.get(2).getString());
        ad.setArea(adData.get(3).getString());
        ad.setFloor(Integer.parseInt(adData.get(4).getString()));
        ad.setLatitude(Double.parseDouble(adData.get(6).getString()));
        ad.setLongitude(Double.parseDouble(adData.get(7).getString()));
        ad.setSuspend(false);
        ad.setRate(0);
        ad.setStatus("Rent");


        return ad;
    }
}
