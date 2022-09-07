package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.CustomerType;

public class CustomerTypeDAO {

	
	private static CustomerTypeDAO customerTypeInstance = null;
	private static String contextPath = "";
	
	private HashMap<Integer, CustomerType> types = new HashMap<Integer, CustomerType>();

	private CustomerTypeDAO() {

	}

	private CustomerTypeDAO(String contextPath) {
		loadCustomerTypes(contextPath);
	}
	
	public static CustomerTypeDAO getInstance()
    {
        if (customerTypeInstance == null) {
        	customerTypeInstance = new CustomerTypeDAO();
        }
 
        return customerTypeInstance;
    }

	public Collection<CustomerType> getAllTypes() {
		return types.values();
	}

	public CustomerType save(CustomerType CustomerType) {
		Integer maxId = -1;
		for (int id : types.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		CustomerType.setId(maxId);
		types.put(CustomerType.getId(), CustomerType);
		saveCustomerTypes();
		return CustomerType;
	}

	public CustomerType update(CustomerType CustomerType) {
		types.put(CustomerType.getId(), CustomerType);
		return CustomerType;
	}

	public void delete(int id) {
		this.types.remove(id);
	}

	public void loadCustomerTypes(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;
		try {
			File file = new File(contextPath + "types.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, name = "";
			double discount = -1;
			double pointsNeeded = -1;
			int id = -1;

			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					name = st.nextToken().trim();
					discount = Double.parseDouble(st.nextToken().trim());
					pointsNeeded = Double.parseDouble(st.nextToken().trim());
				}
				types.put(id, new CustomerType(id, name, discount, pointsNeeded));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}

	}
	
	public void saveCustomerTypes() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "types.txt"); 
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for(CustomerType ct : types.values()) {
				
				String s = ct.getId() + ";" + ct.getTypeName() + ";" + ct.getDiscount() + ";" + ct.getPointsNeeded() + "\n";
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
	
}
