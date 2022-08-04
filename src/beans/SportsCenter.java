package beans;

import java.util.ArrayList;

import enums.CenterType;
import enums.CenterType2;

public class SportsCenter {
	private int id;
	private String name;
	private String centerType;
	private UserCommon manager;
	private String content;
	private boolean status;
	private Location location;
	private String imagePath;
	private float averageGrade;
	private String workingHours;
	
	public SportsCenter() {}
	public SportsCenter(int id, String name, String centerType, UserCommon manager, String content, boolean status,
			Location location, String imagePath, float averageGrade, String workingHours) {
		super();
		this.setId(id);
		this.name = name;
		this.centerType = centerType;
		this.manager = manager;
		this.content = content;
		this.status = status;
		this.location = location;
		this.imagePath = imagePath;
		this.averageGrade = averageGrade;
		this.workingHours = workingHours;
	}
	public UserCommon getManager() {
		return manager;
	}
	public void setManager(UserCommon manager) {
		this.manager = manager;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCenterType() {
		return centerType;
	}
	public void setCenterType(String centerType) {
		this.centerType = centerType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean getStatus() {
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean containsName(String tekst) {
		return this.name.toLowerCase().contains(tekst.toLowerCase());
	}
	
	public boolean containsType(String tekst) {
		return (""+this.centerType).toLowerCase().contains(tekst.toLowerCase());
	}
	
	public boolean containsLocation(String tekst) {
		return (this.location.getStreet()).toLowerCase().contains(tekst.toLowerCase());
	}
	
	public boolean containsAverage(String tekst) {
		return (""+this.averageGrade).toLowerCase().contains(tekst.toLowerCase());
	}
	
}
