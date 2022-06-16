package beans;

import java.util.Date;

import enums.Role;

public class UserManager extends UserCommon {
	private SportsCenter sportsCenter;

	public UserManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserManager(String userName, String password, String name, String surname, String sex, Date birthDate,
			Role role, CustomerType customerType) {
		super(userName, password, name, surname, sex, birthDate, role, customerType);
		// TODO Auto-generated constructor stub
	}

	public UserManager(SportsCenter sportsCenter) {
		super();
		this.sportsCenter = sportsCenter;
	}

	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}

	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}

}
