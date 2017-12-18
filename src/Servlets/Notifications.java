package Servlets;

import Entities.Notification;
import Models.NotificationModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Notifications")
public class Notifications extends HttpServlet {
    private NotificationModel notification_model;

    @Override
    public void init() throws ServletException {
        super.init();
        notification_model = new NotificationModel();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("viewed")==null) {
            getNotifications(request, response);
        }else{
            int notification_id = Integer.parseInt(request.getParameter("id"));
            Notification notification = notification_model.select(notification_id);
            notification.setShowed(true);
            notification.setId(notification_id);
            notification_model.update(notification);
        }

    }

    private void getNotifications(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        List<Notification> notificationsList = notification_model.selectWhere("*","user_id = "+user_id);
        notificationsList.sort((not1, not2) -> Boolean.compare(not1.isShowed(), not2.isShowed()));

        Gson gson = new Gson();

        for(Notification not:notificationsList){
            System.out.println(not.isShowed()+" "+not.getAdvertisementId());
        }

        String json = gson.toJson(notificationsList);

        response.getWriter().print(json);
    }
}
