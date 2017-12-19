package Servlets;

import Controllers.NotificationController;
import Entities.Advertisement;
import Entities.Picture;
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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
            adData = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            Advertisement ad  = parseAd(adData);
            List<Picture> pictures = new ArrayList<>();
            Picture picture = saveImagesToDisk(adData,out);
            if(picture != null)
                pictures.add(picture);
            ad.setPictures(pictures);
            if(ads_model.update(ad)){
                ad.setId(ad.getId());
                NotificationController notificationController = new NotificationController();
                notificationController.notify(ad);

                response.sendRedirect("home.jsp");
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

    private Picture saveImagesToDisk(List<FileItem> items, PrintWriter out) {

        Picture file = getPicture(items, out);
        if (file != null) return file;
        System.out.println("null");
        return null;

    }

    static Picture getPicture(List<FileItem> items, PrintWriter out) {
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
}
