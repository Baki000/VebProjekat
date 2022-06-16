package beans;

import java.time.LocalDateTime;

public class TrainingHistory {
	private LocalDateTime date;
	private Training training;
	private UserCustomer customer;
	private UserTrainer trainer;
	
	public TrainingHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainingHistory(LocalDateTime date, Training training, UserCustomer customer, UserTrainer trainer) {
		super();
		this.date = date;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
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
	public UserCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(UserCustomer customer) {
		this.customer = customer;
	}
	public UserTrainer getTrainer() {
		return trainer;
	}
	public void setTrainer(UserTrainer trainer) {
		this.trainer = trainer;
	}

}
