package beans;

import java.util.ArrayList;
import java.util.Date;

import enums.Role;

public class UserTrainer extends UserCommon {
	private ArrayList<TrainingHistory> trainingHistory;


	public UserTrainer(String userName, String password, String name, String surname, String sex, Date birthDate,
			Role role, CustomerType customerType) {
		super(userName, password, name, surname, sex, birthDate, role, customerType);
		// TODO Auto-generated constructor stub
	}

	public UserTrainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTrainer(ArrayList<TrainingHistory> trainingHistory) {
		super();
		this.trainingHistory = trainingHistory;
	}

	public ArrayList<TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(ArrayList<TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	

}
