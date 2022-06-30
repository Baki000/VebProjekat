package dao;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
import beans.UserCommon;
import dao.LocationDAO;
import services.LocationService;

public class SportsCenterDAO {
	
	private HashMap<Integer, SportsCenter> centers = new HashMap<Integer, SportsCenter>();
	public LocationService ls = new LocationService();
	private static String contextPath = "";
	
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
			centers.put((s.getId()), s);
		}
	}

	public ArrayList<SportsCenter> pretrazi(String tekst){
		String[] parts = tekst.split(",");
		System.out.println(parts[1]);
		System.out.println("PRETRAZI DAO");
		ArrayList<SportsCenter> returnList = new ArrayList<SportsCenter>();
		switch(parts[1]) {
		case "name":
			for (SportsCenter l : centers.values()) {
				if (l.containsName(parts[0])) {
					returnList.add(l);
				}
			}
			break;
		case "type":
			for (SportsCenter l : centers.values()) {
				if (l.containsType(parts[0])) {
					returnList.add(l);
				}
			}
			break;
		case "location":
			for (SportsCenter l : centers.values()) {
				if (l.containsLocation(parts[0])) {
					returnList.add(l);
				}
			}
			break;
		case "average":
			for (SportsCenter l : centers.values()) {
				if (l.containsAverage(parts[0])) {
					returnList.add(l);
				}
			}
			break;
		}
		
		
		
		
		return returnList;
	}
	
	public SportsCenter save(SportsCenter user) {
		System.out.println("SAVEEEEECntr");
		if(!exists(user)) {
			Integer maxId = -1;
			for (int id : centers.keySet()) {
				if (id > maxId) {
					maxId = id;
				}
			}
			maxId++;
			user.setId(maxId);
			centers.put(user.getId(), user);
			saveUsers();
			return user;
		}else {
			return null;
		}
		
	}
	
	public boolean exists(SportsCenter user) {
		for(SportsCenter u : centers.values()) {
			System.out.println(u.getLocation().getStreet());
			if((u.getLocation().getStreet()).equals(user.getLocation().getStreet())) {
				return true;
			}
		}
		
		return false;
	}
	
	//zameniti sa pisanjem u .json file ako posalje Glajbara
	public void saveUsers() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "users.txt");
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for(SportsCenter user : centers.values()) {
				String s = user.getId() + ";" + user.getUserName() + ";" + user.getPassword() + ";" + user.getName() + ";" + user.getSurname() + ";" + user.getSex() + ";" + user.getBirthDate() + ";" + user.getRole() + ";" + user.getFee() + ";" + user.getPoints() + "\n";
				
				out.write(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}

	}
	
/*
	public void connectSCandLocation() {
		ArrayList<Location> lokacije = new ArrayList<Location>(ls.getLocationDAO().getAllLocations());
		for(SportsCenter sportskiObjekat : centers.values()) {
			int idTrazeni = sportskiObjekat.getLocation().getId();
			
			for(Location lokacija : lokacije) {
				if(lokacija.getId() == idTrazeni) {
					sportskiObjekat.setLocation(lokacija);
					break;
				}
			}
		}
	}*/
	
}
