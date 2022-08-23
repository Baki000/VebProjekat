package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Location;
import beans.UserCommon;
import formats.DateFormat;

public class LocationDAO {

	
	private HashMap<Integer, Location> locations = new HashMap<Integer, Location>();
	private static String contextPath = "";
	private static LocationDAO locInstance = null;
	
	public LocationDAO() {}
	
	public LocationDAO(String contextPath) {
		try {
			loadLocations(contextPath);
		} catch(Exception e){
			return;
		}
		
	}
	
	public static LocationDAO getInstance()
    {
        if (locInstance == null) {
        	locInstance = new LocationDAO();
        }
 
        return locInstance;
    }
	
	public Collection<Location> getAllLocations(){
		
		return locations.values();
	}
	
	public Location getById(int id)
	{
		return locations.get(id);
	}
	

	
	
	
	// ODLICAN
		public Location save(Location loc) {
			System.out.println("SAVEEEEE");
			if(!exists(loc)) {
				Integer maxId = -1;
				for (int id : locations.keySet()) {
					if (id > maxId) {
						maxId = id;
					}
				}
				maxId++;
				loc.setId(maxId);
				locations.put(loc.getId(), loc);
				saveLocations();
				return loc;
			}else {
				return null;
			}
			
		}
		
		//ODLiCAN
		public boolean exists(Location loc) {
			for(Location l : locations.values()) {
				System.out.println(l.getStreet());
				if((l.getStreet()).equals(loc.getStreet()) && (l.getCity()).equals(loc.getCity())) {
					return true;
				}
				if(l.getLatitudeWidth() == loc.getLatitudeWidth() && l.getLongitudeLength() == loc.getLongitudeLength()) {
					return true;
				}
			}
			
			return false;
		}
		
		public void saveLocations() {
			BufferedWriter out = null;
			try {
				File file = new File(contextPath + "users.txt");
				System.out.println(file.getCanonicalPath());
				out = new BufferedWriter(new FileWriter(file));
				//output
				for(Location loc : locations.values()) {
					String s = loc.getId() + ";" + loc.getStreet() + "l" + loc.getCity() + ";" + loc.getPostalCode() + ";" + loc.getLongitudeLength() + ";" + loc.getLatitudeWidth() + "\n";                            
					
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
		
		public Location update(Location loc) {
			locations.put(loc.getId(), loc);
			return loc;
		}
		// DA LI TREBA ID DA BUDE STRING?????
		public void delete(Integer id) {
			this.locations.remove(id);
		}
	
	
	
	
	
	public void loadLocations(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "/locations.txt");
			System.out.println("UPAO U LOAD LOC");
			System.out.println(contextPath);
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
			for(Integer id : locations.keySet()) {
				String key = id.toString();
				String value = locations.get(id).getStreet();
				System.out.println(key + " " + value);
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
