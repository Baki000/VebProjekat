package beans;

import java.time.LocalDateTime;

import formats.DateTimeFormat;

public class BookedTraining {
	private int id;
	private LocalDateTime trainingDate;
	private String trainingStatus;
	private UserCommon customer;
	private UserCommon trainer;
	private SportsCenter centerWhereExists;
	
	public BookedTraining() {
		
	}
	
	public BookedTraining(int id) {
		this.id = id;
	}

	public BookedTraining(int id, LocalDateTime trainingDate, String trainingStatus, UserCommon customer,
			UserCommon trainer, SportsCenter centerWhereExists) {
		super();
		this.id = id;
		this.trainingDate = trainingDate;
		this.trainingStatus = trainingStatus;
		this.customer = customer;
		this.trainer = trainer;
		this.centerWhereExists = centerWhereExists;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTrainingDate() {
		return DateTimeFormat.dateTimeToString(trainingDate);
	}

	public void setTrainingDate(String trainingDate) {
		this.trainingDate = DateTimeFormat.stringToDateTime(trainingDate);
	}

	public String getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}

	public UserCommon getCustomer() {
		return customer;
	}

	public void setCustomer(UserCommon customer) {
		this.customer = customer;
	}

	public UserCommon getTrainer() {
		return trainer;
	}

	public void setTrainer(UserCommon trainer) {
		this.trainer = trainer;
	}

	public SportsCenter getCenterWhereExists() {
		return centerWhereExists;
	}

	public void setCenterWhereExists(SportsCenter centerWhereExists) {
		this.centerWhereExists = centerWhereExists;
	}
	
	public String convertToString() {
		return id + ";" + DateTimeFormat.dateTimeToString(trainingDate) + ";" + trainingStatus + ";" + customer.getId() + ";" + trainer.getId() + ";" + centerWhereExists.getId();
	}

}
