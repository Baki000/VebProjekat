package dto;

import beans.SportsCenter;
import beans.Training;

public class TrainingDTO {
	private int id;
	private String name;
	private String trainigType;
	private SportsCenter sportsCenter;
	private double duration;
	private String description;
	private String pictureURL;
	private String trainer;
	private int trainerID;
	
	

	public TrainingDTO(Training training) {
		super();
		this.id = training.getIntId();
		this.name = training.getName();
		this.trainigType = training.getTrainingType();
		this.sportsCenter = training.getSportsCenter();
		this.duration = training.getDuration();
		this.description = training.getDescription();
		this.pictureURL = training.getPictureURL();
		this.trainer = (training.getTrainer()==null)?null:(training.getTrainer().getName() + " " + training.getTrainer().getSurname());
		this.trainerID = (training.getTrainer()==null)?-1:(training.getTrainer().getId());
	}
	
	public TrainingDTO(int id) {
		this.id = id;
	}
	
	public TrainingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIntID() {
		return id;
	}
	public void setIntID(int intID) {
		this.id = intID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrainigType() {
		return trainigType;
	}
	public void setTrainigType(String trainigType) {
		this.trainigType = trainigType;
	}
	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}
	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public String getTrainer() {
		return trainer;
	}
	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}
	public int getTrainerID() {
		return trainerID;
	}
	public void setTrainerID(int trainerID) {
		this.trainerID = trainerID;
	}
	
	

}
