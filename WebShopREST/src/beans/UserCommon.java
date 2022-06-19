package beans;

import java.util.ArrayList;
import java.util.Date;

import beans.CustomerType;
import enums.Role;

public class UserCommon {
	
	private int id;
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String sex;
	private Date birthDate;
	private Role role;
	private CustomerType customerType;
	//customer
	private float fee;
	private int points;
	private ArrayList<SportsCenter> visitedCenters;
	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public ArrayList<SportsCenter> getVisitedCenters() {
		return visitedCenters;
	}

	public void setVisitedCenters(ArrayList<SportsCenter> visitedCenters) {
		this.visitedCenters = visitedCenters;
	}

	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}

	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}

	public ArrayList<TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(ArrayList<TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	//manager
	private SportsCenter sportsCenter;
	//trainer
	private ArrayList<TrainingHistory> trainingHistory;

	
	public UserCommon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserCommon(int id, String userName, String password, String name, String surname, String sex, Date birthDate,
			Role role, CustomerType customerType, float fee, int points, ArrayList<SportsCenter> visitedCenters,
			SportsCenter sportsCenter, ArrayList<TrainingHistory> trainingHistory) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.birthDate = birthDate;
		this.role = role;
		this.customerType = customerType;
		this.fee = fee;
		this.points = points;
		this.visitedCenters = visitedCenters;
		this.sportsCenter = sportsCenter;
		this.trainingHistory = trainingHistory;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
