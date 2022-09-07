package dto;

import java.time.LocalDateTime;

import beans.BookedTraining;
import formats.DateTimeFormat;

public class BookedTrainingDTO {
	private int id;
	private LocalDateTime trainingDate;
	private String trainingStatus;
	private int userId;
	private int trainerId;
	private int centerId;
	private String customerName;
	private String centerName;

	public BookedTrainingDTO() {
		
	}

	public BookedTrainingDTO(int id) {
		this.id = id;
	}

	public BookedTrainingDTO(BookedTraining bookedTraining) {
		this.id = bookedTraining.getId();
		this.trainingDate = DateTimeFormat.stringToDateTime(bookedTraining.getTrainingDate());
		this.trainingStatus = bookedTraining.getTrainingStatus();
		this.userId = bookedTraining.getCustomer().getId();
		this.trainerId = bookedTraining.getTrainer().getId();
		this.centerId = bookedTraining.getCenterWhereExists().getId();
		this.customerName = bookedTraining.getCustomer().getName() + bookedTraining.getCustomer().getSurname();
		this.centerName = bookedTraining.getCenterWhereExists().getName();
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
}
