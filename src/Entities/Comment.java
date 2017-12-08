package Entities;

public class Comment {
    private int userId;
    private int advertisementId;
    private String comment;

    public Comment(int userId, int advertisementId, String comment) {
        this.userId = userId;
        this.advertisementId = advertisementId;
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
