package Entities;

public class Advertisement {
	private int id;
	private House house;
	private int userID;
	private String type;
	private boolean isSuspended;
	private double rate;

	public Advertisement(int id, House house, int userID, String type) {
		this.id = id;
		this.house = house;
		this.userID = userID;
		this.type = type;
	}

	public Advertisement(){
		this.house = new House();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public int getUser() {
		return userID;
	}

	public void setUser(int userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean suspended) {
		isSuspended = suspended;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
