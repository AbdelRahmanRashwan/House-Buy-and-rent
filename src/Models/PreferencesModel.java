package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Preferences;

public class PreferencesModel extends Model<Preferences> {

	@Override
	public Preferences select(int id) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("SELECT * FROM prefrence WHERE Id=?;");
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

    public Preferences selectByUserID(int id) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("SELECT * FROM prefrence WHERE userID=?;");
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
        Connection c =DBConnection.getConn();
        try {
            ResultSet res  =c.prepareStatement("SELECT * FROM prefrence;").executeQuery();
            ArrayList<Preferences> preferences=new ArrayList<>();
            while(res.next())
                preferences.add(parse(res));

            return preferences;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectAll (UserModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
    public List<Preferences> selectWhere(List<String> columns, String where) {
        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM prefrence WHERE ?;");
            p.setString(1,where);
            ResultSet res = p.executeQuery();

            ArrayList<Preferences> preferences=new ArrayList<>();

            while(res.next())
                preferences.add(parse(res));

            return preferences;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere(PreferencesModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
    public boolean update(Preferences preferences) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("UPDATE `prefrence` SET `Type`=?,`size`=?,`area`=?,`Floor`=? WHERE userID=?");
            p.setString(1,preferences.getType());
            p.setInt(2,preferences.getSize());
            p.setString(3,preferences.getArea());
            p.setInt(4,preferences.getFloor());
            p.setInt(5,preferences.getUserID());

            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert(PreferencesModel)");
            e.printStackTrace();
        }		return false;
	}

	@Override
    public boolean delete(int id) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("DELETE FROM prefrence WHERE Id=?;");
            p.setInt(1,id);
            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete(PreferencesModel)");
            e.printStackTrace();
        }
        return false;
	}

	@Override
    public boolean insert(Preferences preferences) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("INSERT INTO `prefrence`( `Type`, `size`, `area`, `Floor`, `userID`) VALUES  (?,?,?,?,?)");
            p.setString(1,preferences.getType());
            p.setInt(2,preferences.getSize());
            p.setString(3,preferences.getArea());
            p.setInt(4,preferences.getFloor());
            p.setInt(4,preferences.getUserID());

            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert(PreferencesModel)");
            e.printStackTrace();
        }		return false;
	}

    private Preferences parse(ResultSet res) {
        try {
            int Id = res.getInt("Id");
            String Type=res.getString("Type");
            String area = res.getString("area");
            int size = res.getInt("size");
            int Floor=res.getInt("Floor");
            Preferences preferences = new Preferences();
            preferences.setArea(area);
            preferences.setFloor(Floor);
            preferences.setId(Id);
            preferences.setSize(size);
            preferences.setType(Type);
            return  preferences;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;

    }

}
