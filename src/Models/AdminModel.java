package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Admin;

public class AdminModel extends Model<Admin> {

	@Override
	Admin select(int id) {
		Connection c = DBConnection.getConn();
		try {
			PreparedStatement p = c.prepareStatement("SELECT * FROM admin WHERE Id=?;");
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

	private Admin parse(ResultSet res) {
		try {
			Admin admin = new Admin();
			admin.setEmail(res.getString("email"));
			admin.setId(res.getInt("Id"));
			admin.setPassword(res.getString("password"));
			admin.setUsername(res.getString("username"));
		} catch (SQLException e) {
			System.out.println("Error not valid column adParser (AdvertisementModel)");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	List<Admin> selectAll() {
		Connection c = DBConnection.getConn();
		try {
			PreparedStatement p = c.prepareStatement("SELECT * FROM admin ");
			ResultSet res = p.executeQuery();
			ArrayList<Admin> admins = new ArrayList<>();
			while (res.next()) {
				admins.add(parse(res));
				}
			return admins;

		} catch (SQLException e) {
			System.out.println("Error connecting to DB select (PreferencesModel)");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	List<Admin> selectWhere(List<String> columns, String where) {
        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM admin WHERE ?;");
            p.setString(1,where);
            ResultSet res = p.executeQuery();
            ArrayList<Admin> admins=new ArrayList<>();

            while(res.next())
                admins.add(parse(res));

            return admins;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere(UserModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
	boolean update(Admin admin) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("UPDATE admin SET username=?,password=?,email=? WHERE Id=?");
            p.setString(1,admin.getUsername());
            p.setString(2,admin.getPassword());
            p.setString(3,admin.getEmail());
            p.setInt(4,admin.getId());

            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB update(UserModel)");
            e.printStackTrace();
        }
        return false;
	}

	@Override
	boolean delete(int id) {
		Connection c = DBConnection.getConn();
		try {
			PreparedStatement p = c.prepareStatement("DELETE FROM admin WHERE Id=?;");
			p.setInt(1,id);
			return p.executeUpdate()>0;
		} catch (SQLException e) {
			System.out.println("Error connecting to DB delete(UserModel)");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	boolean insert(Admin admin) {
		Connection c = DBConnection.getConn();
		try {
			PreparedStatement p = c.prepareStatement("INSERT INTO `admin`( `username`, `password`, `email`) VALUES (?,?,?)");
			p.setString(1,admin.getUsername());
			p.setString(2,admin.getPassword());
			p.setString(3,admin.getEmail());
			return p.executeUpdate()>0;
		} catch (SQLException e) {
			System.out.println("Error connecting to DB insert(UserModel)");
			e.printStackTrace();
		}		return false;
	}

}
