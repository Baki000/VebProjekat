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
	
/*	
ClanarinaDAO.getInstance().loadClanarine(contextPath);
IstorijaTreningaDAO.getInstance().loadIstorijeTreninga(contextPath);
KomentarDAO.getInstance().loadKomentari(contextPath);*/
//UserCommonService.getUserCommonDAO().loadUsers2(contextPath);
LocationDAO.getInstance().loadLocations(contextPath);
SportsCenterDAO.getInstance().loadCenters(contextPath);
/*
TipKupcaDAO.getInstance().loadTipoviKupca(contextPath);
TreningDAO.getInstance().loadTreninzi(contextPath);*/


SportsCenterDAO.getInstance().connectSCandLocation();
/*
KorisnikDAO.getInstance().connectKorisnikClanarina();
KorisnikDAO.getInstance().connectKorisnikSportskiObjekat();
KorisnikDAO.getInstance().connectKorisnikTipKupca();
KorisnikDAO.getInstance().connectKorisnikPoseceniObjekti(contextPath);

KomentarDAO.getInstance().connectKomentarSportskiObjekat();
KomentarDAO.getInstance().connectKomentarKupac();

SportskiObjekatDAO.getInstance().connectSportskiObjekatLokacija();
/*
TreningDAO.getInstance().connectTreningTrener();
TreningDAO.getInstance().connectTreningSportskiObjekat();

IstorijaTreningaDAO.getInstance().connectIstorijaTreningaKupac();
IstorijaTreningaDAO.getInstance().connectIstorijaTreningaKupacTrener();
IstorijaTreningaDAO.getInstance().connectIstorijaTreningaTrening();

*/
}

public static OnStartUp getInstance(String contextPath) {
if (pokretanjeInstance == null) {
pokretanjeInstance = new OnStartUp(contextPath);
}

return pokretanjeInstance;
}
}
