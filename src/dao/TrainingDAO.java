package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import beans.SportsCenter;
import beans.Training;
import beans.UserCommon;

public class TrainingDAO {

	private static TrainingDAO trainingInstance = null;
	private static String contextPath = "";

	public HashMap<Integer, Training> trainings = new HashMap<Integer, Training>();

	private TrainingDAO() {

	}

	private TrainingDAO(String contextPath) {
		loadTrainings(contextPath);
	}

	public static TrainingDAO getInstance() {
		if (trainingInstance == null) {
			trainingInstance = new TrainingDAO();
		}

		return trainingInstance;
	}

	public Collection<Training> findAll() {
		return trainings.values();
	}
	
	
	public Training findTraining(int id) {
		return trainings.containsKey(id) ? trainings.get(id) : null;
	}
	
	public boolean postojiNaziv(String naziv) {
		for (Training t : trainings.values()) {
			if (t.getName().equals(naziv)) {
				return true;
			}
		}
		return false;
	}
	
	public void loadTrainings(String contextPath) {
		BufferedReader in = null;
		this.contextPath = contextPath;

		try {
			File file = new File(contextPath + "trainings.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line, name = "", trainingType = "", description = "", picture = "";
			double duration = 0;
			int id = -1;
			UserCommon trainer = null;
			SportsCenter object = new SportsCenter();
			StringTokenizer st;

			// SportsCenterDAO sod = new SportsCenterDAO();
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					name = st.nextToken().trim();
					trainingType = st.nextToken().trim();

					// SportsCenter = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					object = new SportsCenter(Integer.parseInt(st.nextToken().trim()));

					// SportsCenter = sod.findObjekat(Integer.parseInt(st.nextToken().trim()));
					// SportsCenter obj = new
					// SportsCenter(Integer.parseInt(st.nextToken().trim()));
					duration = Double.parseDouble(st.nextToken().trim());
					int trainerId = Integer.parseInt(st.nextToken().trim());

					if (trainerId != -1) {
						trainer = new UserCommon(trainerId);
					}
					description = st.nextToken().trim();
					picture = st.nextToken().trim();
				}
				trainings.put(id, new Training(id, name, trainingType, object, duration, trainer, description, picture));
				
				for (int t : this.trainings.keySet()) {
				    System.out.println(trainings.get(t).convertToString());
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
	
	public ArrayList<Training> getTrainingsForObject(int idSC) {
		System.out.println("trDAO upao u getTRforOBJ");
		ArrayList<Training> TrainingsForObject = new ArrayList<Training>();
		int i = 0;
		int j = 0;
		for (Training tr : trainings.values()) {
			System.out.println("I je " + i);
			System.out.println("idSC je " + idSC);
			System.out.println("ID TRENUTNI JE " + tr.getSportsCenter().getId());
			if (tr.getSportsCenter().getId() == idSC) {
				System.out.println("J je: " + j);
				TrainingsForObject.add(tr);
			}
		}
		return TrainingsForObject;
	}
	
	public void connectTrainingTrener() {
		ArrayList<UserCommon> users = new ArrayList<UserCommon>(UserCommonDAO.getInstance().getAllUsers());
		for (Training Training : trainings.values()) {
			if (Training.getTrainer() == null) {
				continue;
			}
			int idTrazeni = Training.getTrainer().getId();

			for (UserCommon UserCommon : users) {
				if (UserCommon.getId() == idTrazeni) {
					Training.setTrainer(UserCommon);
					break;
				}
			}
		}
	}

	
	public void connectTrainingSportsCenter() {
		System.out.println("UPAO U KONEKT TRENING SC");
		ArrayList<SportsCenter> sportsCenters = new ArrayList<SportsCenter>(
				SportsCenterDAO.getInstance().getAllSportsCenters());
		for (Training Training : trainings.values()) {
			int idSCForTraining = Training.getSportsCenter().getId();

			for (SportsCenter SportsCenter : sportsCenters) {
				if (SportsCenter.getId() == idSCForTraining) {
					Training.setSportsCenter(SportsCenter);
					break;
				}
			}
		}
		for(Integer id2 : trainings.keySet()) {
			String key = id2.toString();
			String value = trainings.get(id2).getSportsCenter().getName();
			Integer value2 = trainings.get(id2).getSportsCenter().getId();
			System.out.println(key + " " + value + " " + value2);
		}
		
	}
	/*
	for(Integer id2 : centers.keySet()) {
			String key = id2.toString();
			String value = centers.get(id2).getLocation().getStreet();
			System.out.println(key + " " + value);
		}
	*/
	
	public void saveTrainings() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "trainings.txt"); 
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for (Training training : trainings.values()) {
				out.write(training.convertToString() + '\n');
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
