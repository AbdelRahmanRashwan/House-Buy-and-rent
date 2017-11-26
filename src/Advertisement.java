public class Advertisement {

    private House house;
    private String type; // rent or sell
    private double rate;
    private int numOfRating;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getNumOfRating() {
        return numOfRating;
    }

    public void setNumOfRating(int numOfRating) {
        this.numOfRating = numOfRating;
    }
}
