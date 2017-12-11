package Models;

import Entities.Preferences;
import Entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreferencesModel extends Model<Preferences> {

    @Override
    public Preferences select(int id) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM preferences WHERE id=?;");
            p.setInt(1, id);
            ResultSet res = p.executeQuery();
            if (!res.next()) {
                System.out.println("No Records Found");
                return null;
            }
            return parse(res);
        } catch (SQLException e) {
            System.out.println("Error connecting to DB select (PreferencesModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Preferences> selectAll() {
        try {
            ResultSet res = conn.prepareStatement("SELECT * FROM preferences;").executeQuery();
            ArrayList<Preferences> preferences = new ArrayList<>();
            while (res.next()) {
                preferences.add(parse(res));
            }
            return preferences;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectAll (PreferencesModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Preferences> selectWhere(String columns, String where) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT " + columns + " FROM preferences WHERE " + where + ";");
            ResultSet res = p.executeQuery();
            ArrayList<Preferences> preferences = new ArrayList<>();
            while (res.next()) {
                preferences.add(parse(res));
            }
            return preferences;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere (PreferencesModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Preferences preferences) {
        try {
            PreparedStatement p = conn.prepareStatement("UPDATE preferences SET type=?, size=?, area=?, floor=?, user_id=? WHERE id=?");
            p.setString(1, preferences.getType());
            p.setInt(2, preferences.getSize());
            p.setString(3, preferences.getArea());
            p.setInt(4, preferences.getFloor());
            p.setInt(5, preferences.getUser().getId());
            p.setInt(5, preferences.getId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert (PreferencesModel)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement p = conn.prepareStatement("DELETE FROM preferences WHERE id=?;");
            p.setInt(1, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete (PreferencesModel)");
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean insert(Preferences preferences) {
        try {
            PreparedStatement p = conn.prepareStatement("INSERT INTO preferences( type, size, area, floor, user_id) VALUES  (?,?,?,?,?)");
            p.setString(1, preferences.getType());
            p.setInt(2, preferences.getSize());
            p.setString(3, preferences.getArea());
            p.setInt(4, preferences.getFloor());
            p.setInt(5, preferences.getUser().getId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert (PreferencesModel)");
            e.printStackTrace();
        }
        return false;
    }

    Preferences parse(ResultSet res) {
        try {
            UserModel userModel = new UserModel();
            int Id = res.getInt("id");
            String Type = res.getString("type");
            String area = res.getString("area");
            int size = res.getInt("size");
            int Floor = res.getInt("floor");
            int userId = res.getInt("user_id");
            User user = userModel.select(userId);
            Preferences preferences = new Preferences();
            preferences.setArea(area);
            preferences.setFloor(Floor);
            preferences.setId(Id);
            preferences.setSize(size);
            preferences.setType(Type);
            preferences.setUser(user);
            return preferences;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}
