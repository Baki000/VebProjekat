package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.CustomerType;
import beans.Fee;
import beans.Product;
import beans.SportsCenter;
import beans.TrainingHistory;
import beans.User;
import beans.UserCommon;
import enums.Role;
import formats.DateFormat;

public class UserCommonDAO {
	
	private static UserCommonDAO userInstance = null;
	private HashMap<Integer, UserCommon> users = new HashMap<Integer, UserCommon>();
	private static String contextPath = "";
	
	public UserCommonDAO() {}
	
	public UserCommonDAO(String contextPath) {
		try {
			loadUsers2(contextPath);
		} catch(Exception e){
			return;
		}
		
	}
	
	public static UserCommonDAO getInstance() {
		if(userInstance == null) {
			userInstance = new UserCommonDAO();
		}
		return userInstance;
	}
	
	public Collection<UserCommon> getAllUsers(){
		
		return users.values();
	}
	
	public UserCommon getById(String id)
	{
		return users.get(id);
	}
	
	
	//ODLICAN
	public UserCommon find(String username, String password) {

		System.out.println("DAO: upao u find");
		UserCommon pronadjen = null;
		for(int intId : users.keySet()) {
			UserCommon korisnik = users.get(intId);
			if(korisnik.getUserName().equals(username)) {
				pronadjen  = korisnik;
				System.out.println("PRONADJEN");
			}
		}
		if(pronadjen != null) {
			if(!pronadjen.getPassword().equals(password)) {
				System.out.println("NIJE PRONADJEN");
				pronadjen = null;
			}
		}
		//System.out.println(pronadjen.getUserName());
		//System.out.println(pronadjen.getPassword());
		return pronadjen;
	}
	
	// ODLICAN
	public UserCommon save(UserCommon user) {
		System.out.println("SAVEEEEE");
		if(!exists(user)) {
			Integer maxId = -1;
			for (int id : users.keySet()) {
				if (id > maxId) {
					maxId = id;
				}
			}
			maxId++;
			user.setId(maxId);
			users.put(user.getId(), user);
			saveUsers();
			return user;
		}else {
			return null;
		}
		
	}
	
	//ODLiCAN
	public boolean exists(UserCommon user) {
		for(UserCommon u : users.values()) {
			System.out.println(u.getUserName());
			if((u.getUserName()).equals(user.getUserName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public void saveUsers() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "users.txt");
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));
			//output
			for(UserCommon user : users.values()) {
				String s = user.getId() + ";" + user.getUserName() + ";" + user.getPassword() + ";" + user.getName() + ";" + user.getSurname() + ";" + user.getSex() + ";" + DateFormat.stringToDate(user.getBirthDate()) + ";" + user.getRole() + ";" + ((user.getFee() == null)?-1:user.getFee().getId()) + ";" + user.getPoints() + ";" + ((user.getCustomerType() == null)?-1:user.getCustomerType().getId()) + ";" + ((user.getSportsCenter() == null)?-1:user.getSportsCenter().getId()) + "\n";                            
				
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
	
	public ArrayList<UserCommon> getFreeManagers() {
		ArrayList<UserCommon> freeManagers = new ArrayList<UserCommon>();

		for (UserCommon u : users.values()) {
			if (u.getRole().equals(Role.MANAGER)) {
				if (u.getSportsCenter() == null) {
					freeManagers.add(u);
				}
			}
		}
		return freeManagers;

	}
	
	
	
	public UserCommon update(UserCommon korisnik) {
		/*
		if (korisnik.getSportskiObjekat() != null) { 
			int id = korisnik.getSportskiObjekat().getIntId();
			
			SportskiObjekat sportskiObjekat = SportskiObjekatDAO.getInstance().findObjekat(id);
			korisnik.setSportskiObjekat(sportskiObjekat);
		}
		if (korisnik.getClanarina() != null) {
			int id = korisnik.getClanarina().getIntId();
			Clanarina clanarina = ClanarinaDAO.getInstance().findClanarina(id);
			korisnik.setClanarina(clanarina);
		}*/
		users.put(korisnik.getId(), korisnik);
		saveUsers();
		return korisnik;
	}

	public void delete(int id) {
		this.users.remove(id);
	}
	
	
	
	public void loadUsers2(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
			in = new BufferedReader(new FileReader(file));
			String line;
			//LocalDate birthDate = LocalDate.now();
			int id = -1;
			StringTokenizer st;
			
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					String userName = st.nextToken().trim();
					String password = st.nextToken().trim();
					String name = st.nextToken().trim();
					String surname = st.nextToken().trim();
					String sex = st.nextToken().trim();
					LocalDate birthDate = DateFormat.stringToDate(st.nextToken().trim());
					String role = st.nextToken().trim();
					
					Fee fee = null;
					int feeID = Integer.parseInt(st.nextToken().trim());
					if(feeID != -1) {
						fee = new Fee(feeID);
					}
					
					double points = Double.parseDouble(st.nextToken().trim());
					
					CustomerType customerType = null;
					int customerID = Integer.parseInt(st.nextToken().trim());
					if(customerID != -1) {
						customerType = new CustomerType(customerID);
					}
					
					SportsCenter sc = null;
					int scID = Integer.parseInt(st.nextToken().trim());
					if(scID != -1) {
						sc = new SportsCenter(scID);
					}
					
					List<TrainingHistory> trainingHistory = new ArrayList<TrainingHistory>();
					
					List<SportsCenter> visitedCenters = new ArrayList<SportsCenter>();
					
					
					
					users.put(id, new UserCommon(id, userName, password, name, surname, sex, birthDate, role, fee, points, visitedCenters, customerType,  trainingHistory,  sc));
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
