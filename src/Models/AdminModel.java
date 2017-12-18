package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entities.Admin;

public class AdminModel extends Model<Admin> {

	@Override
	public Admin select(int id) {
		try {
			PreparedStatement p = conn.prepareStatement("SELECT * FROM admin WHERE id=?;");
			p.setInt(1, id);
			ResultSet res = p.executeQuery();
			if (!res.next()) {
				System.out.println("No Records Found");
				return null;
			}
			return parse(res);
		} catch (SQLException e) {
			System.out.println("Error connecting to DB select (AdminModel)");
			e.printStackTrace();
		}
		return null;
	}

	public Admin selectByUsernameAndPassword(String username, String password) {
		try {
			System.out.println(username + " " + password);
			PreparedStatement p = conn.prepareStatement("SELECT * FROM admin WHERE username=? and password=?");
			p.setString(1, username);
			p.setString(2, password);
			ResultSet res = p.executeQuery();
			if (res.next()) {

				return parse(res);
			}
			System.out.println("No Records Found admin");
			return null;

		} catch (SQLException e) {
			System.out.println("Error connecting to DB select (AdminModel)");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Admin> selectAll() {
		try {
			PreparedStatement p = conn.prepareStatement("SELECT * FROM admin");
			ResultSet res = p.executeQuery();
			ArrayList<Admin> admins = new ArrayList<>();
			while (res.next()) {
				admins.add(parse(res));
			}
			return admins;
		} catch (SQLException e) {
			System.out.println("Error connecting to DB select (AdminModel)");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Admin> selectWhere(String columns, String where) {
		try {
			PreparedStatement p = conn.prepareStatement("SELECT " + columns + " FROM admin WHERE " + where + ";");
			ResultSet res = p.executeQuery();
			ArrayList<Admin> admins = new ArrayList<>();
			while (res.next()) {
				admins.add(parse(res));
			}
			return admins;
		} catch (SQLException e) {
			System.out.println("Error connecting to DB selectWhere (AdminModel)");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Admin admin) {
		try {
			PreparedStatement p = conn.prepareStatement("UPDATE admin SET username=?,password=?,email=? WHERE id=?");
			p.setString(1, admin.getUsername());
			p.setString(2, admin.getPassword());
			p.setString(3, admin.getEmail());
			p.setInt(4, admin.getId());
			return p.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error connecting to DB update (AdminModel)");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			PreparedStatement p = conn.prepareStatement("DELETE FROM admin WHERE id=?;");
			p.setInt(1, id);
			return p.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error connecting to DB delete (AdminModel)");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insert(Admin admin) {
		try {
			PreparedStatement p = conn.prepareStatement("INSERT INTO admin(username, password, email) VALUES (?,?,?)");
			p.setString(1, admin.getUsername());
			p.setString(2, admin.getPassword());
			p.setString(3, admin.getEmail());
			return p.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error connecting to DB insert (AdminModel)");
			e.printStackTrace();
		}
		return false;
	}

	Admin parse(ResultSet res) {
		try {
			Admin admin = new Admin();
			admin.setId(res.getInt("id"));
			admin.setUsername(res.getString("username"));
			admin.setEmail(res.getString("email"));
			admin.setPassword(res.getString("password"));
			return admin;
		} catch (SQLException e) {
			System.out.println("Error not valid column parse (AdminModel)");
			e.printStackTrace();
		}
		return null;
	}
}
