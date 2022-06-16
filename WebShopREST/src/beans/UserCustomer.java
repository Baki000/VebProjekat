package beans;

import java.util.ArrayList;
import java.util.Date;

import enums.Role;

public class UserCustomer extends UserCommon {
	private float fee;
	private int points;
	private ArrayList<SportsCenter> visitedCenters;
	
	public UserCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserCustomer(String userName, String password, String name, String surname, String sex, Date birthDate,
			Role role, CustomerType customerType) {
		super(userName, password, name, surname, sex, birthDate, role, customerType);
		// TODO Auto-generated constructor stub
	}
	public UserCustomer(float fee, int points, ArrayList<SportsCenter> visitedCenters) {
		super();
		this.fee = fee;
		this.points = points;
		this.visitedCenters = visitedCenters;
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
	public ArrayList<SportsCenter> getVisitedCenters() {
		return visitedCenters;
	}
	public void setVisitedCenters(ArrayList<SportsCenter> visitedCenters) {
		this.visitedCenters = visitedCenters;
	}
}
