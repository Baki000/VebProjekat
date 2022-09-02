package startup;

import dao.LocationDAO;
import dao.TrainingHistoryDAO;
import dao.SportsCenterDAO;
import dao.TrainingDAO;
import dao.UserCommonDAO;

public class OnStartUp {

	private static OnStartUp startuUpInstance = null;

	private OnStartUp(String contextPath) {

		UserCommonDAO.getInstance().loadUsers2(contextPath);
		LocationDAO.getInstance().loadLocations(contextPath);
		SportsCenterDAO.getInstance().loadCenters(contextPath);
		TrainingDAO.getInstance().loadTrainings(contextPath);
		TrainingHistoryDAO.getInstance().loadTrainingHistories(contextPath);


		SportsCenterDAO.getInstance().connectSCandLocation();
		UserCommonDAO.getInstance().connectUserandSC();
		TrainingDAO.getInstance().connectTrainingSportsCenter();
		TrainingDAO.getInstance().connectTrainingTrener();
		TrainingHistoryDAO.getInstance().connectTHandCustomer();
		TrainingHistoryDAO.getInstance().connectTHandTrainer();

	}

	public static OnStartUp getInstance(String contextPath) {
		if (startuUpInstance == null) {
			startuUpInstance = new OnStartUp(contextPath);
		}


		return startuUpInstance;
	}
}
