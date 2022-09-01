package startup;
import dao.LocationDAO;
import dao.TrainingHistoryDAO;
import dao.SportsCenterDAO;
import dao.UserCommonDAO;
import services.LocationService;
import services.SportsCenterService;
import services.UserCommonService;

public class OnStartUp {

private static OnStartUp pokretanjeInstance = null;


private OnStartUp(String contextPath) {
	

UserCommonDAO.getInstance().loadUsers2(contextPath);
LocationDAO.getInstance().loadLocations(contextPath);
SportsCenterDAO.getInstance().loadCenters(contextPath);
TrainingHistoryDAO.getInstance().loadTrainingHistories(contextPath);



SportsCenterDAO.getInstance().connectSCandLocation();
UserCommonDAO.getInstance().connectUserandSC();


TrainingHistoryDAO.getInstance().connectTHandCustomer();
TrainingHistoryDAO.getInstance().connectTHandTrainer();


}

public static OnStartUp getInstance(String contextPath) {
if (pokretanjeInstance == null) {
pokretanjeInstance = new OnStartUp(contextPath);
}

return pokretanjeInstance;
}
}
