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
	private String birthDate;
	private int role;
	//private CustomerType customerType;
	//customer
	private float fee;
	private int points;
	//private ArrayList<SportsCenter> visitedCenters;
	
	public UserCommon(int id, String userName, String password, String name, String surname, String sex,
			String birthDate, int role, float fee, int points) {
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
	}

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

	/*public ArrayList<SportsCenter> getVisitedCenters() {
		return visitedCenters;
	}

	public void setVisitedCenters(ArrayList<SportsCenter> visitedCenters) {
		this.visitedCenters = visitedCenters;
	}
	*/

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

	
	public UserCommon() {}
	


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
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
//	public CustomerType getCustomerType() {
//		return customerType;
//	}
//	public void setCustomerType(CustomerType customerType) {
//		this.customerType = customerType;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
