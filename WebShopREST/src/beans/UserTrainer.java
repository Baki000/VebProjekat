package beans;

import java.util.ArrayList;

public class UserTrainer {
	private ArrayList<TrainingHistory> trainingHistory;

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
