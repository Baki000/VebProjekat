package dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import beans.CustomerType;
import beans.SportsCenter;
import beans.UserCommon;
import formats.DateFormat;

public class UserCommonDTO {
	private int id;
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String sex;
	private LocalDate birthDate;
	private String role;
	
	//customer
	private double points;
	private List<SportsCenter> visitedCenters;
	private CustomerType customerType;
	
	
	//manager
	private SportsCenter sportsCenter;
	
	public UserCommonDTO() {
		super();
		this.visitedCenters = new ArrayList<SportsCenter>();
		
	}
	
	
	public UserCommonDTO(int id) {
		super();
		this.id = id;
	}


	public UserCommonDTO(UserCommon user) {
		super();
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.sex = user.getSex();
		this.birthDate = DateFormat.stringToDate(user.getBirthDate());
		this.role = user.getRole();
		this.points = user.getPoints();
		this.visitedCenters = user.getVisitedCenters();
		this.customerType = user.getCustomerType();
		this.sportsCenter = user.getSportsCenter();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getBirthDate() {
		return DateFormat.dateToString(birthDate);
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = DateFormat.stringToDate(birthDate);
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public double getPoints() {
		return points;
	}


	public void setPoints(double points) {
		this.points = points;
	}


	public List<SportsCenter> getVisitedCenters() {
		return visitedCenters;
	}


	public void setVisitedCenters(List<SportsCenter> visitedCenters) {
		this.visitedCenters = visitedCenters;
	}


	public CustomerType getCustomerType() {
		return customerType;
	}


	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}


	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}


	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}

}
