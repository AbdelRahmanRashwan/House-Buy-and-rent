package Controllers;
import Models.*;
import Entities.*;

import java.util.List;

public class NotificationController {
    private static PreferencesModel preferencesModel;
    private static NotificationModel notificationModel;

    NotificationController(){
        preferencesModel = new PreferencesModel();
        notificationModel = new NotificationModel();
    }

    public boolean notify(Advertisement advertisement){
        List<Preferences> preferences = preferencesModel.selectWhere("*",
                                      "type = '" + advertisement.getType() + "' " +
                                            "AND size = '" + advertisement.getSize() + "' " +
                                            "AND area = '" + advertisement.getArea() + "' " +
                                            "AND floor = '" + advertisement.getFloor() + "' ");

        Notification notification = new Notification();
        notification.setAdvertisementId(advertisement.getId());
        notification.setDescription("A new advertisement meets your preferences has been added. Advertisement Id:" + advertisement.getId());
        for(int i = 0; i < preferences.size(); i++){
            notification.setUserId(preferences.get(i).getUser().getId());
            notificationModel.insert(notification);
        }

        return true;
    }
}
