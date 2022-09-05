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

import beans.Training;
import beans.TrainingHistory;
import beans.UserCommon;
import formats.DateTimeFormat;

public class TrainingHistoryDAO {

	
	private static TrainingHistoryDAO trainingHistoryInstance = null;
	private static String contextPath = "";
	
	public HashMap<Integer, TrainingHistory> histories = new HashMap<Integer,TrainingHistory>();
	
	private TrainingHistoryDAO(){
		
	}
	
	private TrainingHistoryDAO(String contextPath){
		loadTrainingHistories(contextPath);
	}
	
	public static TrainingHistoryDAO getInstance() {
		if(trainingHistoryInstance == null) {
			trainingHistoryInstance = new TrainingHistoryDAO();
		}
		
		return trainingHistoryInstance;
	}
	
	public Collection<TrainingHistory> findAll(){
		return histories.values();
	}
	
	public TrainingHistory save(TrainingHistory th) {
		Integer maxId = -1;
		for (int id : histories.keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}
		maxId++;
		th.setId(maxId);
		histories.put(th.getId(), th);
		return th;
	}
	
	public TrainingHistory update(TrainingHistory th) {
		histories.put(th.getId(), th);
		return th;
	}
	
	public void delete(int id) {
		this.histories.remove(id);
	}
	
	public void loadTrainingHistories(String contextPath) {
		this.contextPath = contextPath;
		BufferedReader in = null;
		try {
			File file = new File(contextPath + "histories.txt"); 
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			String line;
			
			int id = -1;
			Training training = null;
			UserCommon customer = null;
			UserCommon trainer = null;
			
			StringTokenizer st;
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.equals("") || line.indexOf('#') == 0)
					continue;
				st = new StringTokenizer(line, ";");
				while (st.hasMoreTokens()) {
					id = Integer.parseInt(st.nextToken().trim());
					LocalDateTime date  = DateTimeFormat.stringToDateTime(st.nextToken().trim());
					int trainingID = Integer.parseInt(st.nextToken().trim());
					if(trainingID != -1) {
						training = new Training(trainingID);
					}
					
					int customerID = Integer.parseInt(st.nextToken().trim());
					if(customerID != -1) {
						customer = new UserCommon(customerID);
					}
					
					int trainerID = Integer.parseInt(st.nextToken().trim());
					if(trainerID != -1) {
						trainer = new UserCommon(trainerID);
					}
					histories.put(id, new TrainingHistory(id, date, training, customer, trainer));
				}
				
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
	
	public void saveTrainingHistories() {
		BufferedWriter out = null;
		try {
			File file = new File(contextPath + "histories.txt"); 
			System.out.println(file.getCanonicalPath());
			out = new BufferedWriter(new FileWriter(file));

			for(TrainingHistory th : histories.values()) {
				String s = th.getId() + ";" + DateTimeFormat.dateTimeToString(th.getDate()) + ";" + th.getTraining().getIntId() + ";" + th.getCustomer().getId() + ";" + ((th.getTrainer() == null) ? -1 : th.getTrainer().getId());
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
	
	
	
	public void connectTHandCustomer() {
		ArrayList<UserCommon> users = new ArrayList<UserCommon>(UserCommonDAO.getInstance().getAllUsers());
		for(TrainingHistory th : histories.values()) {
			
			if(th.getCustomer() == null) {
				continue;
			}
			
			int customerID = th.getCustomer().getId();
			for(UserCommon user : users) {
				if(user.getId() == customerID) {
					th.setCustomer(user);
				}
			}
		}
	}
	
	
	public void connectTHandTrainer() {
		ArrayList<UserCommon> users = new ArrayList<UserCommon>(UserCommonDAO.getInstance().getAllUsers());
		for(TrainingHistory th : histories.values()) {
			
			if(th.getTrainer() == null) {
				continue;
			}
			
			int trainerID = th.getTrainer().getId();
			for(UserCommon user : users) {
				if(user.getId() == trainerID) {
					th.setTrainer(user);
				}
			}
		}
	}
	
	
	public void connectTHandTraining() {
        ArrayList<Training> treninzi = new ArrayList<Training>(TrainingDAO.getInstance().findAll());
        for(TrainingHistory TrainingHistory : histories.values()) {
            if(TrainingHistory.getTraining() == null) {
                continue;
            }
            int idTrazeni = TrainingHistory.getTraining().getIntId();
            
            for(Training trening : treninzi) {
                if(trening.getIntId() == idTrazeni) {
                    TrainingHistory.setTraining(trening);
                    break;
                }
            }
        }
    }
	
	
}
