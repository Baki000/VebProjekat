package startup;

import dao.LocationDAO;
import dao.SportsCenterDAO;
import dao.TrainingDAO;
import dao.UserCommonDAO;

public class OnStartUp {

	private static OnStartUp pokretanjeInstance = null;

	private OnStartUp(String contextPath) {

		UserCommonDAO.getInstance().loadUsers2(contextPath);
		LocationDAO.getInstance().loadLocations(contextPath);
		SportsCenterDAO.getInstance().loadCenters(contextPath);
		TrainingDAO.getInstance().loadTrainings(contextPath);

		SportsCenterDAO.getInstance().connectSCandLocation();
		UserCommonDAO.getInstance().connectUserandSC();
		TrainingDAO.getInstance().connectTrainingSportsCenter();
		TrainingDAO.getInstance().connectTrainingTrener();

	}

	public static OnStartUp getInstance(String contextPath) {
		if (pokretanjeInstance == null) {
			pokretanjeInstance = new OnStartUp(contextPath);
		}

		return pokretanjeInstance;
	}
}
