package beans;

import java.util.Date;

import beans.CustomerType;
import enums.Role;

public class UserCommon {
	private String userName;
	private String password;
	private String name;
	private String surname;
	private String sex;
	private Date birthDate;
	private Role role;
	private CustomerType customerType;
	
	public UserCommon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserCommon(String userName, String password, String name, String surname, String sex, Date birthDate,
			Role role, CustomerType customerType) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.birthDate = birthDate;
		this.role = role;
		this.customerType = customerType;
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

}
