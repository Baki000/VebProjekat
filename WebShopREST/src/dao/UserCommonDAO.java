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

import beans.User;
import beans.UserCommon;

public class UserCommonDAO {
	
	private HashMap<String, UserCommon> users = new HashMap<String, UserCommon>();
	
	public UserCommonDAO() {}
	
	public UserCommonDAO(String contextPath) {
		try {
			loadUsers(contextPath);
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
		if (!users.containsKey(username)) {
			return null;
		}
		UserCommon user = users.get(username);
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
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
	
	private void loadUsers(String contextPath) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<UserCommon> userList = Arrays.asList(mapper.readValue(new File(contextPath + "/letovanja.json"), UserCommon[].class));
		
		for (UserCommon u : userList) {
			users.put((""+u.getId()), u);
		}
	}

}
