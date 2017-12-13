package Entities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class House {
	private int id;
	private double size;
	private String description;
	private int floor;
	private String status;
	private String type;
	private List<File> images;
	private double longitude;
	private double latitude;
	private String area;

	public House(int id, double size, String description, int floor, String status, String type, String area) {
		this.id = id;
		this.size = size;
		this.description = description;
		this.floor = floor;
		this.status = status;
		this.type = type;
		this.area = area;
	}

	House() {
		this.setImages(new ArrayList<File>());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<File> getImages() {
		return images;
	}

	public void setImages(List<File> images) {
		this.images = images;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLangitude(double latitude) {
		this.latitude = latitude;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
