package Models;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Picture;
import Entities.User;

public class UserModel extends Model<User> {

    @Override
    public User select(int id) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM user WHERE id=?;");
            p.setInt(1, id);
            ResultSet res = p.executeQuery();
            if (!res.next()) {
                System.out.println("No Records Found");
                return null;
            }
            return parse(res);
        } catch (SQLException e) {
            System.out.println("Error connecting to DB select (UserModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> selectAll() {
        try {
            ResultSet res = conn.prepareStatement("SELECT * FROM user;").executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (res.next()) {
                users.add(parse(res));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectAll (UserModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> selectWhere(String columns, String where) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT " + columns + " FROM user WHERE " + where + ";");
            ResultSet res = p.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (res.next()) {
                users.add(parse(res));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere (UserModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(User user) {
        try {
            PreparedStatement p = conn.prepareStatement("UPDATE user SET name=?, email=?, phone=?, address=?, password=?, picture_path=? WHERE id=?");
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPhone());
            p.setString(4, user.getAddress());
            p.setString(5, user.getPassword());
            p.setString(6, user.getPicture().path);
            p.setInt(7, user.getId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB update (UserModel)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {

        try {
            PreparedStatement p = conn.prepareStatement("DELETE FROM user WHERE id=?;");
            p.setInt(1, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete (UserModel)");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String email) {
        try {
            PreparedStatement p = conn.prepareStatement("DELETE FROM user WHERE email=?;");
            p.setString(1, email);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete (UserModel)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(User user) {
        try {
            PreparedStatement p = conn.prepareStatement("INSERT INTO user(name, email, phone, address, password, picture_path) VALUES (?,?,?,?,?,?)");
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPhone());
            p.setString(4, user.getAddress());
            p.setString(5, user.getPassword());
            p.setString(6, user.getPicture().path);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert (UserModel)");
            e.printStackTrace();
        }
        return false;
    }

    User parse(ResultSet set) {
        try {
            User user = new User();
            int user_id = set.getInt("id");
            user.setId(user_id);
            user.setName(set.getString("name"));
            user.setAddress(set.getString("address"));
            user.setEmail(set.getString("email"));
            user.setPassword(set.getString("password"));
            user.setPhone(set.getString("phone"));
            user.setPicture(new Picture(set.getString("picture_path"),""));
            AdvertisementModel advertisementModel = new AdvertisementModel();
            user.setAdvertisements(advertisementModel.selectWhere("*", "user_id = " + user_id));
            return user;
        } catch (SQLException e) {
            System.out.println("Error not valid column Parse (UserModel)");
            e.printStackTrace();
        }
        return null;
    }

    public User selectByEmail(String email) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM user WHERE email=?;");
            p.setString(1, email);
            ResultSet res = p.executeQuery();
            if (!res.next()) {
                System.out.println("No Records Found");
                return null;
            }
            return parse(res);
        } catch (SQLException e) {
            System.out.println("Error connecting to DB select (UserModel)");
            e.printStackTrace();
        }
        return null;
    }

    public User selectByEmailAndPassword(String email, String Password) {

        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM user WHERE email=? AND password=?;");
            p.setString(1, email);
            p.setString(2, Password);
            ResultSet res = p.executeQuery();
            if (!res.next()) {
                System.out.println("No Records Found");
                return null;
            }
            return parse(res);
        } catch (SQLException e) {
            System.out.println("Error connecting to DB select (UserModel)");
            e.printStackTrace();
        }
        return null;
    }
}
