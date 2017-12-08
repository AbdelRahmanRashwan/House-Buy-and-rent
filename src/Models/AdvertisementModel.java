package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entities.Advertisement;
import Entities.Comment;
import Entities.User;

public class AdvertisementModel extends Model<Advertisement> {

	@Override
	Advertisement select(int id) {
		Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM advertisement WHERE Id=?;");
            p.setInt(1,id);
            ResultSet res = p.executeQuery();
            if(!res.next()){
                System.out.println("No Records Found");
                return null;
            }
            return adParser(res);

        } catch (SQLException e) {
            System.out.println("Error connecting to DB select (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
	List<Advertisement> selectAll() {
        Connection c =DBConnection.getConn();
        try {
            ResultSet res  =c.prepareStatement("SELECT * FROM advertisement;").executeQuery();

            ArrayList<Advertisement> ads=new ArrayList<>();

            while(res.next())
                ads.add(adParser(res));

            return ads;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectAll (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
	List<Advertisement> selectWhere(List<String> columns, String where) {
        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM advertisement WHERE ?;");
            p.setString(1,where);
            ResultSet res = p.executeQuery();

            ArrayList<Advertisement> ads=new ArrayList<>();

            while(res.next())
                ads.add(adParser(res));

            return ads;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere(AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
	}

	@Override
	boolean update(Advertisement advertisement) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("UPDATE advertisement SET size=?,Floor=?,Description=?,Longitude=?,Latitude=?,Status=?,Area=?,Type=?,rate=?,suspend=?,adOwnerId=? WHERE Id = ?;");
            p.setInt(1,advertisement.getSize());
            p.setInt(2,advertisement.getFloor());
            p.setString(3,advertisement.getDescription());
            p.setDouble(4,advertisement.getLongitude());
            p.setDouble(5,advertisement.getLatitude());
            p.setString(6,advertisement.getStatus());
            p.setString(7,advertisement.getArea());
            p.setString(8,advertisement.getType());
            p.setDouble(9,advertisement.getRate());
            p.setInt(10,advertisement.isSuspended()?1:0);
            p.setInt(11,advertisement.getUser().getId());
            p.setInt(12,advertisement.getId());

            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB update(AdvertisementModel)");
            e.printStackTrace();
        }
        return false;
    }

	@Override
	boolean delete(int id) {
	    Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("DELETE FROM advertisement WHERE Id=?;");
            p.setInt(1,id);
            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete(AdvertisementModel)");
            e.printStackTrace();
        }
        return false;
	}

	@Override
	boolean insert(Advertisement advertisement) {
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("INSERT into advertisement ( size, Floor, Description, Longitude, Latitude, Status, Area, Type, rate, suspend, adOwnerId) values (?,?,?,?,?,?,?,?,?,?,?);");
            p.setInt(1,advertisement.getSize());
            p.setInt(2,advertisement.getFloor());
            p.setString(3,advertisement.getDescription());
            p.setDouble(4,advertisement.getLongitude());
            p.setDouble(5,advertisement.getLatitude());
            p.setString(6,advertisement.getStatus());
            p.setString(7,advertisement.getArea());
            p.setString(8,advertisement.getType());
            p.setDouble(9,advertisement.getRate());
            p.setInt(10,advertisement.isSuspended()?1:0);
            p.setInt(11,advertisement.getUser().getId());
            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert(AdvertisementModel)");
            e.printStackTrace();
        }		return false;
	}

	boolean comment(int adID,int userID,String comment){
        Connection c = DBConnection.getConn();
        try {
            PreparedStatement p = c.prepareStatement("INSERT into user_advertisement (userId,AdvertisementId,comment) values (?,?,?);");
            p.setInt(1,userID);
            p.setInt(2,adID);
            p.setString(3,comment);
            return p.executeUpdate()>0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB comment(AdvertisementModel)");
            e.printStackTrace();
        }
	    return false;
    }

    List<Comment> adComments(int adId){
        Connection c =DBConnection.getConn();
        try {
            PreparedStatement p =c.prepareStatement("SELECT * FROM user_advertisement WHERE AdvertisementId=?;");
            p.setInt(1,adId);
            ResultSet res = p.executeQuery();

            ArrayList<Comment> comments = new ArrayList<>();
            while (res.next()) {
                Comment comment = new Comment(res.getInt(0),res.getInt(1),res.getString(2));
                comments.add(comment);
            }
            return comments;

        } catch (SQLException e) {
            System.out.println("Error connecting to DB adComments(AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
    }

	private Advertisement adParser(ResultSet res){

        try {
            Advertisement ad = new Advertisement();
            ad.setId(res.getInt("Id"));
            ad.setSize(res.getInt("size"));
            ad.setFloor(res.getInt("Floor"));
            ad.setDescription(res.getString("Description"));
            ad.setLongitude(res.getDouble("Longitude"));
            ad.setLatitude(res.getDouble("Latitude"));
            ad.setStatus(res.getString("Status"));
            ad.setArea(res.getString("Area"));
            ad.setType(res.getString("Type"));
            ad.setRate(res.getFloat("rate"));
            ad.setSuspended(res.getInt("suspend") > 0);
            UserModel userModel = new UserModel();
            User user=userModel.select(res.getInt("adOwnerId"));
            ad.setUser(user);
            return ad;
        } catch (SQLException e) {
            System.out.println("Error not valid column adParser (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
    }

}
