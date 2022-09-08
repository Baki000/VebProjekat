package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Training;
import beans.UserCommon;
import dao.TrainingDAO;
import dto.TrainingDTO;
import dto.UserCommonDTO;
import startup.OnStartUp;

@Path("/training")
public class TrainingService {
	
	@Context
	ServletContext ctx;
	

	public TrainingService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("trainingDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	OnStartUp.getInstance(contextPath);
			ctx.setAttribute("trainingDAO", TrainingDAO.getInstance());
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getTreninzi() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		return dao.findAll();
	}
	
	
	@GET
	@Path("/getTrainers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserCommonDTO> getTreneriZaSportskiObjekat(@QueryParam("scID") int scID) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		Collection<UserCommon> treneri = dao.getTrainersForSC(scID);
		ArrayList<UserCommonDTO> treneriDTO = new ArrayList<UserCommonDTO>();
		for(UserCommon t : treneri) {
			treneriDTO.add(new UserCommonDTO(t));
		}
		return treneriDTO;
	}
	
	@GET
	@Path("/getAllTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingDTO> getTrainingsForObject(@QueryParam("scID") int scID) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		ArrayList<Training> trainings = dao.getTrainingsForSC(scID);
		ArrayList<TrainingDTO> treninziDTO = new ArrayList<TrainingDTO>();
		for(Training t : trainings) {
			treninziDTO.add(new TrainingDTO(t));
		}
		return treninziDTO;
	}
	
	@GET
	@Path("/getGroupTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingDTO> getGroupTrainingsForTrainer(@QueryParam("idKorisnika") int idKorisnika) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		ArrayList<Training> grupniTreninzi = dao.getGroupTrainingsForTrainer(idKorisnika);
		ArrayList<TrainingDTO> grupniTreninziDTO = new ArrayList<TrainingDTO>();
		for(Training t : grupniTreninzi) {
			grupniTreninziDTO.add(new TrainingDTO(t));
		}
		return grupniTreninziDTO;
	}
	
	
	

}
