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
	
	
//	private FeeDAO(String contextPath) {
//		loadClanarine(contextPath);
//	}
	
	public static FeeDAO getInstance() {
		if(feeInstance == null) {
			feeInstance = new FeeDAO();
		}
		
		return feeInstance;
	}
	
	public Collection<Fee> findAll(){
		return fees.values();
	}
	
	public Fee findFee(int id) {
		return fees.containsKey(id) ? fees.get(id) : null;
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
			File file = new File(contextPath + "/files/fees.txt"); //paket
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, ID = "", tipClanarine = "", status = "";
			LocalDate datumPlacanja = LocalDate.now();
			LocalDate pocetniDatumVazenja = LocalDate.now();
			LocalDate krajnjiDatumVazenja = LocalDate.now();
			double punaCena = -1;
			int brojTermina = -1;
			int intId = -1;
			UserCommon korisnik = null;
			//Korisnik korisnik = new Korisnik();
			
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					intId = Integer.parseInt(st.nextToken().trim());
					ID = st.nextToken().trim();
					tipClanarine = st.nextToken().trim();
					datumPlacanja = DateFormat.stringToDate(st.nextToken().trim());
					pocetniDatumVazenja = DateFormat.stringToDate(st.nextToken().trim());
					krajnjiDatumVazenja = DateFormat.stringToDate(st.nextToken().trim());
					punaCena = Double.parseDouble(st.nextToken().trim());
					int korisnikId = Integer.parseInt(st.nextToken().trim());
					if(korisnikId != -1) {
						korisnik = new UserCommon(korisnikId);
					}
					status = st.nextToken().trim();
					brojTermina = Integer.parseInt(st.nextToken().trim());
				}
				fees.put(intId, new Fee(intId, tipClanarine,datumPlacanja, pocetniDatumVazenja, krajnjiDatumVazenja, punaCena, korisnik, status, brojTermina)); //Drugi konstruktor
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
			fee.setDateEnd(DateFormat.dateToString( fee.getDateStart().plusDays(1)));
		}else if(fee.getFeeType().equals(FeeType.MONTHLY)) {
			fee.setDateEnd(DateFormat.dateToString( fee.getDateStart().plusMonths(1)));
		}else {
			fee.setDateEnd(DateFormat.dateToString( fee.getDateStart().plusYears(1)));
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
			LocalDate trenutniDatum = LocalDate.now();
			
			int brojTermina = 0;
			if(fee.getFeeType() == FeeType.DAILY) {
				brojTermina = 1;
			}else if(fee.getFeeType() == FeeType.MONTHLY) {
				brojTermina = 30;
			}else {
				brojTermina = 360;
			}
			if(fee.getDateEnd().isBefore(trenutniDatum)){
				int broj_iskoristenih_termina = brojTermina - fee.getEntries();
				double broj_bodova = (fee.getPrice() / 1000) * (broj_iskoristenih_termina);
				double broj_izgubljenih_bodova = 0;
				if(broj_iskoristenih_termina <= brojTermina / 3) {
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
			File file = new File(contextPath + "/files/fees.txt"); //proveri naziv fajla
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
	
	public void smanjiBrojTermina(int intId) {
		for(Fee clanarina: fees.values()) {
			if(clanarina.getId() == intId) {
				clanarina.setEntries(clanarina.getEntries() - 1);
				saveFees();
			}
		}
	}
	
	
	
}
