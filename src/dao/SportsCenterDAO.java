package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Location;
import beans.SportsCenter;
import beans.UserCommon;
import dao.LocationDAO;
import enums.CenterType;
import services.LocationService;

public class SportsCenterDAO {

	private HashMap<Integer, SportsCenter> centers = new HashMap<Integer, SportsCenter>();
	private static String contextPath = "";
	private static SportsCenterDAO scInstance = null;
	

	public SportsCenterDAO() {
	}

	public SportsCenterDAO(String contextPath) {
		try {
			loadCenters(contextPath);
			
		} catch (Exception e) {
			System.out.println("okeej");
			return;
		}

	}

	public static SportsCenterDAO getInstance() {
		if (scInstance == null) {
			scInstance = new SportsCenterDAO();
		}

		return scInstance;
	}
	

	public Collection<SportsCenter> getAllSportsCenters() {
		
		return centers.values();
	}

	public SportsCenter getById(String id) {
		return centers.get(id);
	}

	// SVE METODE KOJE VRSE UPITE NAD PODACIMA

	

	public ArrayList<SportsCenter> pretrazi(String tekst) {
		String[] parts = tekst.split(",");
		System.out.println(parts[1]);
		System.out.println("PRETRAZI DAO");
		ArrayList<SportsCenter> returnList = new ArrayList<SportsCenter>();
		switch (parts[1]) {
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
		if (!exists(user)) {
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
		} else {
			return null;
		}

	}

	public boolean exists(SportsCenter user) {
		for (SportsCenter u : centers.values()) {
			System.out.println(u.getLocation().getStreet());
			if ((u.getLocation().getStreet()).equals(user.getLocation().getStreet())) {
				return true;
			}
		}

		return false;
	}

	// zameniti sa pisanjem u .json file ako posalje Glajbara
	public void saveUsers() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "sports_centers.txt");
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for (SportsCenter center : centers.values()) {
				String s = center.getId() + ";" + center.getName() + ";" + center.getCenterType() + ";"
						+ center.getContent() + ";" + center.getStatus() + ";" + center.getLocation().getId() + ";"
						+ center.getLocation().getStreet() + ";" + center.getLocation().getCity() + ";"
						+ center.getLocation().getPostalCode() + ";" + center.getLocation().getLongitudeLength() + ";"
						+ center.getLocation().getLatitudeWidth() + ";" + center.getImagePath() + ";"
						+ center.getAverageGrade() + ";" + center.getWorkingHours();
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

	public void loadCenters(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/sports_centers.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					int id = Integer.parseInt(st.nextToken().trim());
					String name = st.nextToken().trim();

					int ct = Integer.parseInt(st.nextToken().trim());
					UserCommon u = new UserCommon();
					String content = st.nextToken().trim();
					boolean status = Boolean.parseBoolean(st.nextToken().trim());
					// location:
					int locID = Integer.parseInt(st.nextToken().trim());
					//
					Location loc = new Location(locID);

					String imgPath = st.nextToken().trim();

					float avg = Float.parseFloat(st.nextToken().trim());
					String hours = st.nextToken().trim();
					//Location l = new Location(locID, street, city, pc, longitude, latitude);

					centers.put(id, new SportsCenter(id, name, CenterType.values()[ct], u, content, status, loc, imgPath,
							avg, hours));
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public void connectSCandLocation() {
		System.out.println("UPAO U CONNECT");
		ArrayList<Location> lokacije = new ArrayList<Location>(LocationDAO.getInstance().getAllLocations());
		for (SportsCenter sportskiObjekat : centers.values()) {
			int idTrazeni = sportskiObjekat.getLocation().getId();

			for (Location lokacija : lokacije) {
				if (lokacija.getId() == idTrazeni) {
					sportskiObjekat.setLocation(lokacija);
					break;
				}
			}
		}
		for(Integer id : centers.keySet()) {
			String key = id.toString();
			String value = centers.get(id).getLocation().getStreet();
			System.out.println(key + " " + value);
		}
	}

}
