package Controllers;

import Entities.Advertisement;
import Entities.Notification;
import Entities.Preferences;
import Entities.User;
import Models.NotificationModel;
import Models.PreferencesModel;
import Models.UserModel;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class NotificationController {
    private static PreferencesModel preferencesModel;
    private static NotificationModel notificationModel;

    public NotificationController() {
        preferencesModel = new PreferencesModel();
        notificationModel = new NotificationModel();
    }

    public boolean notify(Advertisement advertisement) {
        List<Preferences> preferences = preferencesModel.selectWhere(
                "*",
                "type = '" + advertisement.getType() + "' " +
                        "AND size = '" + advertisement.getSize() + "' " +
                        "AND area = '" + advertisement.getArea() + "' " +
                        "AND floor = '" + advertisement.getFloor() + "' ");

        Notification notification = new Notification();
        notification.setAdvertisementId(advertisement.getId());
        notification.setDescription("A new advertisement meets your preferences has been added. Advertisement Id:" + advertisement.getId());
        for (int i = 0; i < preferences.size(); i++) {
            UserModel user_model = new UserModel();
            User notifiedUser = user_model.select(preferences.get(i).getUserID());
            notification.setUserId(notifiedUser.getId());
            notificationModel.insert(notification);
            notifyByEmail(notifiedUser.getEmail(), advertisement.getId());
        }

        return true;
    }

    private boolean notifyByEmail(String to, int advertisementId) {
        final String username = "email";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("New advertisement meets your preferences has been added!");
            message.setContent(
                    "<h1>Go to advertisement from here</h1> <br>" +
                            "<a href= 'http://localhost:8080/show_ad.jsp?id=" +
                            String.valueOf(advertisementId) + "'>Click Here!</a>",
                    "text/html");

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return true;
    }

}
