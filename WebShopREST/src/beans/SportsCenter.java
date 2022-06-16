package beans;

import java.util.ArrayList;

import enums.CenterType;

public class SportsCenter {
	private String name;
	private CenterType centerType;
	private ArrayList<String> content;
	private boolean status;
	private Location location;
	private String imagePath;
	private float averageGrade;
	private String workingHours;
	
	public SportsCenter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SportsCenter(String name, CenterType centerType, ArrayList<String> content, boolean status,
			Location location, String imagePath, float averageGrade, String workingHours) {
		super();
		this.name = name;
		this.centerType = centerType;
		this.content = content;
		this.status = status;
		this.location = location;
		this.imagePath = imagePath;
		this.averageGrade = averageGrade;
		this.workingHours = workingHours;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CenterType getCenterType() {
		return centerType;
	}
	public void setCenterType(CenterType centerType) {
		this.centerType = centerType;
	}
	public ArrayList<String> getContent() {
		return content;
	}
	public void setContent(ArrayList<String> content) {
		this.content = content;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public float getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(float averageGrade) {
		this.averageGrade = averageGrade;
	}
	public String getWorkingHours() {
		return workingHours;
	}
	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}
}
