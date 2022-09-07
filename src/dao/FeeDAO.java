package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.Fee;
import beans.UserCommon;
import enums.FeeStatus;
import enums.FeeType;
import formats.DateFormat;

public class FeeDAO {
	
	
	private static FeeDAO feeInstance = null;
	private static String contextPath = "";
	
	public HashMap<Integer, Fee> fees = new HashMap<Integer, Fee>();
	
	private FeeDAO() {
		
	}
	
	
	private FeeDAO(String contextPath) {
		loadFees(contextPath);
	}
	
	public static FeeDAO getInstance() {
		if(feeInstance == null) {
			feeInstance = new FeeDAO();
		}
		
		return feeInstance;
	}
	
	public Collection<Fee> getAllFees(){
		return fees.values();
	}
	
	public Fee getById(int id) {
		return fees.get(id);
	}
	
	public Fee update(Fee fee) {
		fees.put(fee.getId(), fee);
		return fee;
	}
	
	public Fee save(Fee fee) {
		Integer maxId = -1;
		for (int id : fees.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		fee.setId(maxId);
		fees.put(fee.getId(), fee);
		return fee;
	}
	
	public void loadFees(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "fees.txt"); 
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, feeType = "", status = "";
			LocalDate payDay = LocalDate.now();
			LocalDate dateStart = LocalDate.now();
			LocalDate dateEnd = LocalDate.now();
			double price = -1;
			int entries = -1;
			int id = -1;
			UserCommon customer = null;
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					
					feeType = st.nextToken().trim();
					payDay = DateFormat.stringToDate(st.nextToken().trim());
					dateStart = DateFormat.stringToDate(st.nextToken().trim());
					dateEnd = DateFormat.stringToDate(st.nextToken().trim());
					price = Double.parseDouble(st.nextToken().trim());
					int customerId = Integer.parseInt(st.nextToken().trim());
					if(customerId != -1) {
						customer = new UserCommon(customerId);
					}
					status = st.nextToken().trim();
					entries = Integer.parseInt(st.nextToken().trim());
				}
				fees.put(id, new Fee(id, feeType,payDay, dateStart, dateEnd, price, customer, status, entries)); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		
	}
	
	public Fee newFee(Fee fee) {
		if(fee.getFeeType().equals(FeeType.DAILY)) {
			fee.setDateEnd(DateFormat.dateToString( DateFormat.stringToDate(fee.getDateStart()).plusDays(1)));
		}else if(fee.getFeeType().equals(FeeType.MONTHLY)) {
			fee.setDateEnd(DateFormat.dateToString( DateFormat.stringToDate(fee.getDateStart()).plusMonths(1)));
		}else {
			fee.setDateEnd(DateFormat.dateToString( DateFormat.stringToDate(fee.getDateStart()).plusYears(1)));
		}
		
		fee = save(fee);
		saveFees();
		
		return fee;
		
	}
	
	
	
	public void delete(int id) {
		this.fees.remove(id);
		
	}
	
	public void feeExpired() {
		for(Fee fee : fees.values()) {
			if(fee.getStatus() == FeeStatus.EXPIRED) {
				continue;
			}
			LocalDate currentDate = LocalDate.now();
			
			int entries = 0;
			if(fee.getFeeType() == FeeType.DAILY) {
				entries = 1;
			}else if(fee.getFeeType() == FeeType.MONTHLY) {
				entries = 30;
			}else {
				entries = 360;
			}
			if(DateFormat.stringToDate(fee.getDateEnd()).isBefore(currentDate)){
				int broj_iskoristenih_termina = entries - fee.getEntries();
				double broj_bodova = (fee.getPrice() / 1000) * (broj_iskoristenih_termina);
				double broj_izgubljenih_bodova = 0;
				if(broj_iskoristenih_termina <= entries / 3) {
					broj_izgubljenih_bodova = (fee.getPrice() / 1000) * (133 * 4);
				}
				double poeni = fee.getCustomer().getCustomerType().getPointsNeeded();
				poeni += (broj_bodova - broj_izgubljenih_bodova);
				fee.getCustomer().getCustomerType().setPointsNeeded(poeni);
				fee.setStatus(FeeStatus.EXPIRED);
			}
			
			
		}
	}
	
	public void saveFees() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "fees.txt"); 
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));
			
			for(Fee fee : fees.values()) {
				out.write(fee.convertToString() + '\n');
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
	
	public void lowerEntries(int id) {
		for(Fee f: fees.values()) {
			if(f.getId() == id) {
				f.setEntries(f.getEntries() - 1);
				saveFees();
			}
		}
	}
	
	
	
}
