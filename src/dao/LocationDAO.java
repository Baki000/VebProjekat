package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Location;
import beans.UserCommon;

public class LocationDAO {

	
	private HashMap<Integer, Location> locations = new HashMap<Integer, Location>();
	private static String contextPath = "";
	
	public LocationDAO() {}
	
	public LocationDAO(String contextPath) {
		try {
			loadLocations(contextPath);
		} catch(Exception e){
			return;
		}
		
	}
	
	public Collection<Location> getAllLocations(){
		
		return locations.values();
	}
	
	public Location getById(int id)
	{
		return locations.get(id);
	}
	
//	public UserCommon find(String username, String password) {
////		if (!users.containsKey(username)) {
////			return null;
////		}
////		UserCommon user = users.get(username);
////		if (!user.getPassword().equals(password)) {
////			return null;
////		}
////		return user;
//		Location pronadjen = null;
//		for(int intId : locations.keySet()) {
//			Location korisnik = locations.get(intId);
//			if(korisnik.getUserName().equals(username)) {
//				pronadjen  = korisnik;
//			}
//		}
//		if(pronadjen != null) {
//			if(!pronadjen.getPassword().equals(password)) {
//				pronadjen = null;
//			}
//		}
//		return pronadjen;
//	}
	
	
//	public UserCommon save(UserCommon user) {
//		System.out.println("SAVEEEEE");
//		Integer maxId = -1;
//		for (int id : users.keySet()) {
//			if (id > maxId) {
//				maxId = id;
//			}
//		}
//		maxId++;
//		user.setId(maxId);
//		users.put(user.getId(), user);
//		saveUsers();
//		return user;
//	}
	
//	public void saveUsers() {
//		BufferedWriter out = null;
//		try {
//			File file = new File(contextPath + "users.txt");
//			System.out.println(file.getCanonicalPath());
//			out = new BufferedWriter(new FileWriter(file));
//
//			for(UserCommon user : users.values()) {
//				String s = user.getId() + ";" + user.getUserName() + ";" + user.getPassword() + ";" + user.getName() + ";" + user.getSurname() + ";" + user.getSex() + ";" + user.getBirthDate() + ";" + user.getRole() + ";" + user.getFee() + ";" + user.getPoints() + "\n";
//				
//				out.write(s);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (Exception e) {
//				}
//			}
//		}
//
//	}
	
	/*
	public UserCommon updateCena(UserCommon letovanje) {
		UserCommon updateLetovanje = getById(letovanje.getId());
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
	
	public ArrayList<UserCommon> pretrazi(String tekst){
		ArrayList<UserCommon> returnList = new ArrayList<UserCommon>();
		for (UserCommon u : users.values()) {
			if (u.sadrziTekst(tekst)) {
				returnList.add(u);
			}
		}
		
		return returnList;
	}*/
	
	// SVE METODE KOJE VRSE UPITE NAD PODACIMA
	
	
	
	public void loadLocations(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/locations.txt");
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
					String street = st.nextToken().trim();
					String city = st.nextToken().trim();
					int postal = Integer.parseInt(st.nextToken().trim());
					String length = st.nextToken().trim();
					String width = st.nextToken().trim();
					
					locations.put(id, new Location(id, street, city, postal, length, width));
				}
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
	}
	
}
