package Models;

import Entities.Notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationModel extends Model<Notification> {

    @Override
    public Notification select(int id) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM notification WHERE id=?");
            p.setInt(1, id);
            ResultSet res = p.executeQuery();
            if (!res.next()) {
                System.out.println("No Records Found");
                return null;
            }
            return parse(res);
        } catch (SQLException e) {
            System.out.println("Error connecting to DB select (NotificationModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notification> selectAll() {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM notification");
            ResultSet res = p.executeQuery();
            ArrayList<Notification> notifications = new ArrayList<>();
            while (res.next()) {
                notifications.add(parse(res));
            }
            return notifications;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectAll (NotificationModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Notification> selectWhere(String columns, String where) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT " + columns + " FROM notification WHERE " + where + ";");
            p.setString(1, where);
            ResultSet res = p.executeQuery();
            ArrayList<Notification> notifications = new ArrayList<>();
            while (res.next()) {
                notifications.add(parse(res));
            }
            return notifications;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere (NotificationModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Notification notification) {
        try {
            PreparedStatement p = conn.prepareStatement("UPDATE notification SET description=?, showed=? WHERE id=?");
            p.setString(1, notification.getDescription());
            p.setBoolean(2, notification.isShowed());
            p.setInt(3, notification.getId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB update (NotificationModel)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement p = conn.prepareStatement("DELETE FROM notification WHERE id=?;");
            p.setInt(1, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete (NotificationModel)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Notification notification) {
        try {
            PreparedStatement p = conn.prepareStatement("INSERT INTO notification(user_id, description, advertisement_id) VALUES (?,?,?)");
            p.setInt(1, notification.getUserId());
            p.setString(2, notification.getDescription());
            p.setInt(3, notification.getAdvertisementId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert (NotificationModel)");
            e.printStackTrace();
        }
        return false;
    }


    Notification parse(ResultSet res) {
        try {
            Notification notification = new Notification();
            notification.setId(res.getInt("id"));
            notification.setUserId(res.getInt("user_id"));
            notification.setDescription(res.getString("description"));
            notification.setAdvertisementId(res.getInt("advertisement_id"));
            notification.setShowed(res.getBoolean("showed"));
        } catch (SQLException e) {
            System.out.println("Error not valid column parse (NotificationModel)");
            e.printStackTrace();
        }
        return null;
    }
}
