package Entities;

public class Comment {
    private String userName;
    private int advertisementId;
    private String comment;

    public Comment(String userName, int advertisementId, String comment) {
        this.userName = userName;
        this.advertisementId = advertisementId;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
