package beans;

import java.time.LocalDateTime;
import formats.DateTimeFormat;

public class TrainingHistory {
	
	private int id;
	private LocalDateTime date;
	private Training training;
	private UserCommon customer;
	private UserCommon trainer;
	
	public TrainingHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainingHistory(int id, LocalDateTime date, Training training, UserCommon customer, UserCommon trainer) {
		super();
		this.id = id;
		this.date = date;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
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

}
