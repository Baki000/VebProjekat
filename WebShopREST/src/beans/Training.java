package beans;

import enums.TrainingType;

public class Training {
	private String name;
	private TrainingType trainingType;
	private SportsCenter sportsCenter;
	private int duration;
	private UserTrainer trainer;
	private String description;
	private String imagePath;
	
	public Training() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Training(String name, TrainingType trainingType, SportsCenter sportsCenter, int duration,
			UserTrainer trainer, String description, String imagePath) {
		super();
		this.name = name;
		this.trainingType = trainingType;
		this.sportsCenter = sportsCenter;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.imagePath = imagePath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TrainingType getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}
	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}
	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public UserTrainer getTrainer() {
		return trainer;
	}
	public void setTrainer(UserTrainer trainer) {
		this.trainer = trainer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
