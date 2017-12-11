package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entities.Advertisement;
import Entities.Comment;
import Entities.Picture;
import Entities.User;

public class AdvertisementModel extends Model<Advertisement> {

    @Override
    public Advertisement select(int id) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM advertisement WHERE id=?;");
            p.setInt(1, id);
            ResultSet res = p.executeQuery();
            if (!res.next()) {
                System.out.println("No Records Found");
                return null;
            }
            return parse(res);
        } catch (SQLException e) {
            System.out.println("Error connecting to DB select (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Advertisement> selectAll() {
        try {
            ResultSet res = conn.prepareStatement("SELECT * FROM advertisement;").executeQuery();
            ArrayList<Advertisement> ads = new ArrayList<>();
            while (res.next()) {
                ads.add(parse(res));
            }
            return ads;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectAll (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Advertisement> selectWhere(String columns, String where) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT "+ columns +" FROM advertisement WHERE " + where + ";");
            ResultSet res = p.executeQuery();
            ArrayList<Advertisement> ads = new ArrayList<>();
            while (res.next()) {
                ads.add(parse(res));
            }
            return ads;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB selectWhere (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Advertisement advertisement) {
        try {
            PreparedStatement p = conn.prepareStatement("UPDATE advertisement SET size=?,floor=?,description=?,longitude=?,latitude=?,status=?,area=?,type=?,rate=?,suspend=? WHERE id = ?;");
            p.setInt(1, advertisement.getSize());
            p.setInt(2, advertisement.getFloor());
            p.setString(3, advertisement.getDescription());
            p.setDouble(4, advertisement.getLongitude());
            p.setDouble(5, advertisement.getLatitude());
            p.setString(6, advertisement.getStatus());
            p.setString(7, advertisement.getArea());
            p.setString(8, advertisement.getType());
            p.setDouble(9, advertisement.getRate());
            p.setInt(10, advertisement.isSuspend() ? 1 : 0);
            p.setInt(11, advertisement.getId());
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB update (AdvertisementModel)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement p = conn.prepareStatement("DELETE FROM advertisement WHERE id=?;");
            p.setInt(1, id);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB delete (AdvertisementModel)");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean insert(Advertisement advertisement) {
        try {
            PreparedStatement p = conn.prepareStatement("INSERT into advertisement ( size, floor, description, longitude, latitude, status, area, type, rate, suspend, user_id) values (?,?,?,?,?,?,?,?,?,?,?);");
            p.setInt(1, advertisement.getSize());
            p.setInt(2, advertisement.getFloor());
            p.setString(3, advertisement.getDescription());
            p.setDouble(4, advertisement.getLongitude());
            p.setDouble(5, advertisement.getLatitude());
            p.setString(6, advertisement.getStatus());
            p.setString(7, advertisement.getArea());
            p.setString(8, advertisement.getType());
            p.setDouble(9, advertisement.getRate());
            p.setInt(10, advertisement.isSuspend() ? 1 : 0);
            p.setInt(11, advertisement.getUser().getId());

            int affected_rows = p.executeUpdate();

            List<Advertisement> ads_by_user = selectWhere("*","user_id = "+advertisement.getUser().getId());
            int latest_ad_id = ads_by_user.get(ads_by_user.size()-1).getId();

            insertImages(advertisement.getPictures(), latest_ad_id);

            return affected_rows > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB insert (AdvertisementModel)");
            e.printStackTrace();
        }
        return false;
    }

    private void insertImages(List<Picture> pictures, int ad_id) throws SQLException {
        for(Picture picture : pictures){
            PreparedStatement p = conn.prepareStatement("INSERT into picture (path, advertisement_id) values (?,?);");
            p.setString(1,picture.path);
            p.setInt(2,ad_id);
            p.executeUpdate();
        }
    }

    private List<Picture> getImages(int ad_id) throws SQLException {
        List<Picture> pictures = new ArrayList<>();
        PreparedStatement p = conn.prepareStatement("SELECT path FROM picture WHERE advertisement_id = ?;");
        p.setInt(1,ad_id);
        ResultSet res = p.executeQuery();
        while (res.next()) {
            pictures.add(new Picture(res.getString(1), null));
        }

        return pictures;
    }

    public boolean comment(int advertisementId, int userId, String comment) {
        try {
            PreparedStatement p = conn.prepareStatement("INSERT into user_advertisement (user_id,advertisement_id,comment) values (?,?,?);");
            p.setInt(1, userId);
            p.setInt(2, advertisementId);
            p.setString(3, comment);
            return p.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB comment (AdvertisementModel)");
            e.printStackTrace();
        }
        return false;
    }

    public List<Comment> adComments(int adId) {
        try {
            PreparedStatement p = conn.prepareStatement("SELECT * FROM user_advertisement WHERE advertisement_id=?;");
            p.setInt(1, adId);
            ResultSet res = p.executeQuery();
            ArrayList<Comment> comments = new ArrayList<>();
            while (res.next()) {
                Comment comment = new Comment(res.getInt(0), res.getInt(1), res.getString(2));
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            System.out.println("Error connecting to DB adComments (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
    }

    Advertisement parse(ResultSet res) {
        try {
            Advertisement ad = new Advertisement();
            ad.setId(res.getInt("id"));
            ad.setSize(res.getInt("size"));
            ad.setFloor(res.getInt("floor"));
            ad.setDescription(res.getString("description"));
            ad.setLongitude(res.getDouble("longitude"));
            ad.setLatitude(res.getDouble("latitude"));
            ad.setStatus(res.getString("status"));
            ad.setArea(res.getString("area"));
            ad.setType(res.getString("type"));
            ad.setRate(res.getFloat("rate"));
            ad.setSuspend(res.getInt("suspend") > 0);
            ad.setPictures(getImages(ad.getId()));
            UserModel userModel = new UserModel();
//            User user = userModel.select(res.getInt("user_id"));
//            ad.setUser(user);

            System.out.println(ad.getType()+" "+ad.getId()+" "+ad.getRate());
            return ad;
        } catch (SQLException e) {
            System.out.println("Error not valid column adParser (AdvertisementModel)");
            e.printStackTrace();
        }
        return null;
    }

}
