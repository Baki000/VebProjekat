package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import formats.DateFormat;

import beans.CustomerType;

public class UserCommon {
	
	private int id;
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String sex;
	private LocalDate birthDate;
	private String role;
	
	//customer
	private Fee fee;
	private double points;
	private List<SportsCenter> visitedCenters;
	private CustomerType customerType;
	
	//coach
	private List<TrainingHistory> trainingHistory;
	
	//manager
	private SportsCenter sportsCenter;
	
	public UserCommon() {
		super();
		this.visitedCenters = new ArrayList<SportsCenter>();
		this.trainingHistory = new ArrayList<TrainingHistory>();
	}
	
	
	public UserCommon(int id) {
		super();
		this.id = id;
	}


	public UserCommon(int id, String userName, String password, String name, String surname, String sex,
			LocalDate birthDate, String role, Fee fee, double points, List<SportsCenter> visitedCenters,
			CustomerType customerType, List<TrainingHistory> trainingHistory, SportsCenter sportsCenter) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.birthDate = birthDate;
		this.role = role;
		this.fee = fee;
		this.points = points;
		this.visitedCenters = visitedCenters;
		this.customerType = customerType;
		this.trainingHistory = trainingHistory;
		this.sportsCenter = sportsCenter;
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


	public Fee getFee() {
		return fee;
	}


	public void setFee(Fee fee) {
		this.fee = fee;
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


	public List<TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}


	public void setTrainingHistory(List<TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
	}


	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}


	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}

	
	public boolean containsName(String tekst) {
		return this.name.toLowerCase().contains(tekst.toLowerCase());
	}
	
	public boolean containsSurname(String tekst) {
		return this.surname.toLowerCase().contains(tekst.toLowerCase());
	}
	
	public boolean containsUsername(String tekst) {
		return this.userName.toLowerCase().contains(tekst.toLowerCase());
	}
	
}
