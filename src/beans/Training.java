package beans;



public class Training {
	private int intId;
	private String name;
	private String trainingType;
	private SportsCenter sportsCenter;
	private double duration;
	private UserCommon trainer;
	private String description;
	private String pictureURL;
	
	
	
	public Training() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Training(int intId) {
		super();
		this.intId = intId;
	}


	

	public Training(int intId, String name, String trainingType, SportsCenter sportsCenter, double duration,
			UserCommon trainer, String description, String pictureURL) {
		super();
		this.intId = intId;
		this.name = name;
		this.trainingType = trainingType;
		this.sportsCenter = sportsCenter;
		this.duration = duration;
		this.trainer = trainer;
		this.description = description;
		this.pictureURL = pictureURL;
	}






	public int getIntId() {
		return intId;
	}




	public void setIntId(int intId) {
		this.intId = intId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
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

	public UserCommon getTrainer() {
		return trainer;
	}

	public void setTrainer(UserCommon trainer) {
		this.trainer = trainer;
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

	
	public String convertToString() {
		return intId + ";" + name + ";" + trainingType + ";" + sportsCenter.getId() + ";" + duration + ";" + ((trainer == null)?-1:trainer.getId()) + ";" + description + ";" 
				+ pictureURL;
	}

//
//	public String getNaziv() {
//		return naziv;
//	}
//	public void setNaziv(String naziv) {
//		this.naziv = naziv;
//	}
//	public String getTipTreninga() {
//		return tipTreninga;
//	}
//	public void setTipTreninga(String tipTreninga) {
//		this.tipTreninga = tipTreninga;
//	}
//	public SportskiObjekat getObjekatGdePripada() {
//		return objekatGdePripada;
//	}
//	public void setObjekatGdePripada(SportskiObjekat objekatGdePripada) {
//		this.objekatGdePripada = objekatGdePripada;
//	}
//	public double getTrajanje() {
//		return trajanje;
//	}
//	public void setTrajanje(double trajanje) {
//		this.trajanje = trajanje;
//	}
//	public Korisnik getTrener() {
//		return trener;
//	}
//	public void setTrener(Korisnik trener) {
//		this.trener = trener;
//	}
//	public String getOpis() {
//		return opis;
//	}
//	public void setOpis(String opis) {
//		this.opis = opis;
//	}
//	public String getSlika() {
//		return slika;
//	}
//	public void setSlika(String slika) {
//		this.slika = slika;
//	}
//	
//	public String convertToString() {
//		return intId + ";" + naziv + ";" + tipTreninga + ";" + objekatGdePripada.getIntId() + ";" + trajanje + ";" + ((trener == null)?-1:trener.getIntId()) + ";" + opis + ";" 
//				+ slika;
//	}

}
