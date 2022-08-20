package beans;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import formats.TimeFormat;

public class SportsCenter {
	private int id;
	private String name;
	private String centerType;
	private List<String> content;
	private String status;
	private Location location;
	private String imagePath;
	private double averageGrade;
	private LocalTime opens;
	private LocalTime closes;
	
	public SportsCenter() {
		super();
		this.content = new ArrayList<String>();
	}
	
	public SportsCenter(int id) {
		super();
		this.id = id;
	}
	
	public SportsCenter(int id, String name, String centerType, String status, List<String> content,
			Location location, String imagePath, double averageGrade, LocalTime opens, LocalTime closes) {
		super();
		this.setId(id);
		this.name = name;
		this.centerType = centerType;
		
		this.content = content;
		this.status = status;
		this.location = location;
		this.imagePath = imagePath;
		this.averageGrade = averageGrade;
		this.opens = opens;
		this.closes = closes;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	
	public String getOpens() {
		return TimeFormat.timeToString(opens);
	}

	public void setOpens(String opens) {
		this.opens = TimeFormat.stringToTime(opens);
	}

	public String getCloses() {
		return TimeFormat.timeToString(closes);
	}

	public void setCloses(String closes) {
		this.closes = TimeFormat.stringToTime(closes);
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
