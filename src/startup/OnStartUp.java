package startup;
import dao.LocationDAO;
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



SportsCenterDAO.getInstance().connectSCandLocation();

}

public static OnStartUp getInstance(String contextPath) {
if (pokretanjeInstance == null) {
pokretanjeInstance = new OnStartUp(contextPath);
}

return pokretanjeInstance;
}
}
