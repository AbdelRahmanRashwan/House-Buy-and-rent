package Entities;

public class Comment {
    private int userID;
    private int adID;
    private String comment;

    public Comment(int userID, int adID, String comment)
    {
        this.userID=userID;
        this.adID=adID;
        this.comment=comment;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAdID() {
        return adID;
    }

    public void setAdID(int adID) {
        this.adID = adID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
