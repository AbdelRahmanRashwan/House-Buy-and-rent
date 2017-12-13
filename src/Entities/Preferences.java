package Entities;

public class Preferences {
	private int id;
	private int userID;
	private int size;
	private int floor;
	private String type;
	private String area;

	public Preferences(){}

	public Preferences(int id, int userID, int size, int floor, String type, String area) {
		this.id = id;
		this.userID = userID;
		this.size = size;
		this.floor = floor;
		this.type = type;
		this.area = area;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
