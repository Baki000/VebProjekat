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

import beans.Product;
import beans.User;
import beans.UserCommon;
import enums.Role;

public class UserCommonDAO {
	
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
	
	public Collection<UserCommon> getAllUsers(){
		
		return users.values();
	}
	
	public UserCommon getById(String id)
	{
		return users.get(id);
	}
	
	public UserCommon find(String username, String password) {
//		if (!users.containsKey(username)) {
//			return null;
//		}
//		UserCommon user = users.get(username);
//		if (!user.getPassword().equals(password)) {
//			return null;
//		}
//		return user;
		UserCommon pronadjen = null;
		for(int intId : users.keySet()) {
			UserCommon korisnik = users.get(intId);
			if(korisnik.getUserName().equals(username)) {
				pronadjen  = korisnik;
			}
		}
		if(pronadjen != null) {
			if(!pronadjen.getPassword().equals(password)) {
				pronadjen = null;
			}
		}
		return pronadjen;
	}
	
	
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

			for(UserCommon user : users.values()) {
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
	
	
	
	private void loadUsers2(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/users.txt");
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
					String userName = st.nextToken().trim();
					String password = st.nextToken().trim();
					String name = st.nextToken().trim();
					String surname = st.nextToken().trim();
					String sex = st.nextToken().trim();
					String birthDate = st.nextToken().trim();
					int role = Integer.parseInt(st.nextToken().trim());
					float fee = Float.parseFloat(st.nextToken().trim());
					int points = Integer.parseInt(st.nextToken().trim());
					users.put(id, new UserCommon(id, userName, password, name, surname, sex, birthDate, role, fee, points));
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
