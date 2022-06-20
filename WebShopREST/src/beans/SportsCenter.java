package beans;

import java.util.ArrayList;

import enums.CenterType;

public class SportsCenter {
	private int id;
	private String name;
	private CenterType centerType;
	private String content;
	private boolean status;
	private int location;
	private String imagePath;
	private float averageGrade;
	private String workingHours;
	
	public SportsCenter() {}
	public SportsCenter(int id, String name, CenterType centerType, String content, boolean status,
			int location, String imagePath, float averageGrade, String workingHours) {
		super();
		this.setId(id);
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
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
	
	public boolean sadrziTekst(String tekst) {
		return this.name.toLowerCase().contains(tekst.toLowerCase());
	}
	
}
