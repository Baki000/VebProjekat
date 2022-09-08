package startup;

import dao.BookedTrainingDAO;
import dao.CustomerTypeDAO;
import dao.FeeDAO;
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
		BookedTrainingDAO.getInstance().loadBookedTrainings(contextPath);
		FeeDAO.getInstance().loadFees(contextPath);
		CustomerTypeDAO.getInstance().loadCustomerTypes(contextPath);


		SportsCenterDAO.getInstance().connectSCandLocation();
		UserCommonDAO.getInstance().connectUserandSC();
		UserCommonDAO.getInstance().connectUserandCT();
		UserCommonDAO.getInstance().connectUserandFee();
		UserCommonDAO.getInstance().connectUserVisitedCenters(contextPath);
		TrainingDAO.getInstance().connectTrainingSportsCenter();
		TrainingDAO.getInstance().connectTrainingTrener();
		TrainingHistoryDAO.getInstance().connectTHandCustomer();
		TrainingHistoryDAO.getInstance().connectTHandTrainer();
		TrainingHistoryDAO.getInstance().connectTHandTraining();
		BookedTrainingDAO.getInstance().connectBookedTrainingTrainer();
		BookedTrainingDAO.getInstance().connectBookedTrainingCustomer();
		BookedTrainingDAO.getInstance().connectBookedTrainingandSC();

	}

	public static OnStartUp getInstance(String contextPath) {
		if (startuUpInstance == null) {
			startuUpInstance = new OnStartUp(contextPath);
		}


		return startuUpInstance;
	}
}
