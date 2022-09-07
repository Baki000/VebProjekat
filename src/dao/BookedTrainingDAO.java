package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.BookedTraining;
import beans.SportsCenter;
import beans.UserCommon;
import formats.DateTimeFormat;

public class BookedTrainingDAO {
	private static BookedTrainingDAO bookedTrainingInstance = null;
	private static String contextPath = "";

	public HashMap<Integer, BookedTraining> bookedTrainings = new HashMap<Integer, BookedTraining>();

	BookedTrainingDAO() {

	}

	private BookedTrainingDAO(String contextPath) {
		loadBookedTrainings(contextPath);
	}

	public static BookedTrainingDAO getInstance() {
		if (bookedTrainingInstance == null) {
			bookedTrainingInstance = new BookedTrainingDAO();
		}

		return bookedTrainingInstance;
	}

	public Collection<BookedTraining> findAll() {
		return bookedTrainings.values();
	}

	public BookedTraining findBookedTraining(int id) {
		return bookedTrainings.containsKey(id) ? bookedTrainings.get(id) : null;
	}

	public BookedTraining save(BookedTraining bookedTraining) {
		Integer maxId = -1;
		for (Integer id : bookedTrainings.keySet()) {
			int idNum = id;
			if (idNum > maxId) {
				maxId = idNum;
			}
		}
		maxId++;
		bookedTraining.setId(maxId);
		bookedTrainings.put(bookedTraining.getId(), bookedTraining);
		sacuvajZakazaneTreninge();
		return bookedTraining;
	}

	public BookedTraining update(BookedTraining bookedTraining) {

		if (bookedTraining.getCustomer() != null) {
			int id = bookedTraining.getCustomer().getId();
			UserCommon kupac = UserCommonDAO.getInstance().getById(id);
			bookedTraining.setCustomer(kupac);
		}

		if (bookedTraining.getTrainer() != null) {
			int id = bookedTraining.getTrainer().getId();
			UserCommon trener = UserCommonDAO.getInstance().getById( id);
			bookedTraining.setTrainer(trener);
		}

		if (bookedTraining.getCenterWhereExists() != null) {
			int id = bookedTraining.getCenterWhereExists().getId();
			SportsCenter obejkat = SportsCenterDAO.getInstance().getById(id);
			bookedTraining.setCenterWhereExists(obejkat);
		}

		bookedTrainings.put(bookedTraining.getId(), bookedTraining);
		return bookedTraining;
	}

	public boolean delete(int id) {
		if (LocalDateTime.now().plusDays(2)
				.isBefore(DateTimeFormat.stringToDateTime(bookedTrainings.get(id).getTrainingDate()))) {
			this.bookedTrainings.remove(id);
			sacuvajZakazaneTreninge();
			return true;
		} else {
			return false;
		}
	}

	public void loadBookedTrainings(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;
		try {
			File file = new File(contextPath + "bookedTrainings.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, statusTreninga = "";

			int id = -1;
			LocalDateTime terminTreninga = LocalDateTime.now();
			StringTokenizer st;

			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");

				while (st.hasMoreTokens()) {
					UserCommon kupac = null;
					UserCommon trener = null;
					SportsCenter objekat = null;
					id = Integer.parseInt(st.nextToken().trim());
					terminTreninga = DateTimeFormat.stringToDateTime(st.nextToken().trim());
					statusTreninga = st.nextToken().trim();
					kupac = new UserCommon(Integer.parseInt(st.nextToken().trim()));
					trener = new UserCommon(Integer.parseInt(st.nextToken().trim()));
					objekat = new SportsCenter(Integer.parseInt(st.nextToken().trim()));
					bookedTrainings.put(id,
							new BookedTraining(id, terminTreninga, statusTreninga, kupac, trener, objekat));
				}

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

	public void sacuvajZakazaneTreninge() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "bookedTrainings.txt");
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for (BookedTraining bookedTraining : bookedTrainings.values()) {
				out.write(bookedTraining.convertToString() + '\n');
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

	public void connectBookedTrainingCustomer() {
		ArrayList<UserCommon> kupci = new ArrayList<UserCommon>(UserCommonDAO.getInstance().getAllUsers());
		for (BookedTraining bookedTraining : bookedTrainings.values()) {
			int idTrazeni = bookedTraining.getCustomer().getId();

			for (UserCommon kupac : kupci) {
				if (kupac.getId() == idTrazeni) {
					bookedTraining.setCustomer(kupac);
					break;
				}
			}
		}
	}

	public void connectBookedTrainingTrainer() {
		ArrayList<UserCommon> treneri = new ArrayList<UserCommon>(UserCommonDAO.getInstance().getAllUsers());
		for (BookedTraining bookedTraining : bookedTrainings.values()) {
			int idTrazeni = bookedTraining.getTrainer().getId();
			System.out.println("Trazeni trener"  + idTrazeni);

			for (UserCommon trener : treneri) {
				if (trener.getId() == idTrazeni) {
					bookedTraining.setTrainer(trener);
					break;
				}
			}
		}
	}

	public void connectBookedTrainingCenterWhereExists() {
		ArrayList<SportsCenter> objekti = new ArrayList<SportsCenter>(SportsCenterDAO.getInstance().getAllSportsCenters());
		for (BookedTraining bookedTraining : bookedTrainings.values()) {
			int idTrazeni = bookedTraining.getCenterWhereExists().getId();

			for (SportsCenter objekat : objekti) {
				if (objekat.getId() == idTrazeni) {
					bookedTraining.setCenterWhereExists(objekat);
					break;
				}
			}
		}
	}

	public ArrayList<BookedTraining> getPersonalTrainingsForTrainer(int idUserCommon) {
		ArrayList<BookedTraining> personalTrsForTrainer = new ArrayList<BookedTraining>();
		for (BookedTraining training : bookedTrainings.values()) {
			if (training.getTrainer() != null) {
				if (training.getTrainer().getId() == idUserCommon) {
					personalTrsForTrainer.add(training);
				}
			}
		}
		return personalTrsForTrainer;
	}

}
