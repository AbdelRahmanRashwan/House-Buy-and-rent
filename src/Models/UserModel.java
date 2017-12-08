package Models;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.User;

public class UserModel extends Model<User> {

	public User selectByEmail(String email) {
        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM user WHERE Email=?;");
            p.setString(1,email);
            ResultSet res = p.executeQuery();
            if(!res.next()){
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

    public User selectByEmailAndPassword(String email,String Password) {

        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM user WHERE Email=? AND  password=?;");
            p.setString(1,email);
            p.setString(2,Password);
            ResultSet res = p.executeQuery();
            if(!res.next()){
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
    public User select(int id) {
        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM user WHERE Id=?;");
            p.setInt(1,id);
            ResultSet res = p.executeQuery();
            if(!res.next()){
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
        Connection c =DBConnection.getConn();
        try {
            ResultSet res  =c.prepareStatement("SELECT * FROM user;").executeQuery();

            ArrayList<User> users=new ArrayList<>();

            while(res.next())
                users.add(parse(res));

            return users;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectAll (UserModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
    public List<User> selectWhere(List<String> columns, String where) {
        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM user WHERE ?;");
            p.setString(1,where);
            ResultSet res = p.executeQuery();

            ArrayList<User> users=new ArrayList<>();

            while(res.next())
                users.add(parse(res));

            return users;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere(UserModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
    public boolean update(User user) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("UPDATE user SET `Name`=?,`Email`=?,`Phone`=?,`address`=?,`password`=?,`picPath`=? WHERE `Id`=?");
            p.setString(1,user.getName());
            p.setString(2,user.getEmail());
            p.setString(3,user.getPhone());
            p.setString(4,user.getAddress());
            p.setString(5,user.getPassword());
            p.setString(6,user.getPicture().getAbsolutePath());
            p.setInt(7,user.getId());

            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB update(UserModel)");
            e.printStackTrace();
        }
        return false;
	}

	@Override
    public boolean delete(int id) {

        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("DELETE FROM user WHERE Id=?;");
            p.setInt(1,id);
            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete(UserModel)");
            e.printStackTrace();
        }
        return false;
	}

    public boolean delete(String email) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("DELETE FROM user WHERE Email=?;");
            p.setString(1,email);
            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete(UserModel)");
            e.printStackTrace();
        }
        return false;
	}

	@Override
    public boolean insert(User user) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("INSERT INTO user(`Name`, `Email`, `Phone`, `address`, `password`, `picPath` ) VALUES (?,?,?,?,?,?)");
            p.setString(1,user.getName());
            p.setString(2,user.getEmail());
            p.setString(3,user.getPhone());
            p.setString(4,user.getAddress());
            p.setString(5,user.getPassword());
            p.setString(6,user.getPicture().getAbsolutePath());
            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert(UserModel)");
            e.printStackTrace();
        }		return false;
	}

	private User parse(ResultSet set)
    {
        try {
            User user = new User();
            int user_id =set.getInt("Id");
            user.setId(user_id);
            user.setName(set.getString("Name"));
            user.setAddress(set.getString("address"));
            user.setEmail(set.getString("Email"));
            user.setPassword(set.getString("password"));
            user.setPhone(set.getString("Phone"));
            user.setPicture(new File(set.getString("picPath")));
            AdvertisementModel advertisementModel = new AdvertisementModel();
            user.setAdvertisements(advertisementModel.selectWhere(null,"userId = "+user_id));
            return user;
        } catch (SQLException e) {
            System.out.println("Error not valid column Parse (UserModel)");
            e.printStackTrace();
        }
        return null;
    }

}
