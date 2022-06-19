package dao;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Location;
import beans.SportsCenter;

import dao.LocationDAO;
import services.LocationService;

public class SportsCenterDAO {
	
	private HashMap<String, SportsCenter> centers = new HashMap<String, SportsCenter>();
	public LocationService ls = new LocationService();
	
	public SportsCenterDAO() {}
	
	public SportsCenterDAO(String contextPath) {
		try {
			loadSportsCenters(contextPath);
		} catch(Exception e){
			System.out.println("okeej");
			return;
		}
		
	}
	
	public Collection<SportsCenter> getAllSportsCenters(){
		return centers.values();
	}
	
	public SportsCenter getById(String id)
	{
		return centers.get(id);
	}
	/*
	public SportsCenter updateCena(SportsCenter letovanje) {
		SportsCenter updateLetovanje = getById(letovanje.getId());
		updateLetovanje.setCena(letovanje.getCena());
		return updateLetovanje;
	}
	
	public Letovanje rezervisi(String id) {
		Letovanje updateLetovanje = letovanja.get(id);
		if (updateLetovanje == null) {
			return null;
		}
		
		updateLetovanje.rezervisi();
		return updateLetovanje;
		
	}
	
	public Letovanje otkazi(String id) {
		Letovanje updateLetovanje = letovanja.get(id);
		if (updateLetovanje == null) {
			return null;
		}
		
		updateLetovanje.odustani();
		return updateLetovanje;
		
	}
	
	public ArrayList<Letovanje> pretrazi(String tekst){
		ArrayList<Letovanje> returnList = new ArrayList<Letovanje>();
		for (Letovanje l : letovanja.values()) {
			if (l.sadrziTekst(tekst)) {
				returnList.add(l);
			}
		}
		
		return returnList;
	}
	*/
	// SVE METODE KOJE VRSE UPITE NAD PODACIMA
	
	private void loadSportsCenters(String contextPath) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<SportsCenter> centersList = Arrays.asList(mapper.readValue(new File(contextPath + "/sports_centers.json"), SportsCenter[].class));
		
		for (SportsCenter s : centersList) {
			centers.put((""+s.getId()), s);
		}
	}
	
//	public void connectSportskiObjekatLokacija() {
//		ArrayList<Location> lokacije = new ArrayList<Location>(ls.getLocationDAO().getAllLocations());
//		for(SportsCenter sportskiObjekat : centers.values()) {
//			int idTrazeni = sportskiObjekat.getLocation().getId();
//			
//			for(Location lokacija : lokacije) {
//				if(lokacija.getId() == idTrazeni) {
//					sportskiObjekat.setLocation(lokacija);
//					break;
//				}
//			}
//		}
//	}
	
}
