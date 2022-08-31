package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Training;
import dao.TrainingDAO;
import dto.TrainingDTO;
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
	@Path("/getAllTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingDTO> getTrainingsForObject(@QueryParam("idSportskogObjekta") int idSC) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		ArrayList<Training> trainings = dao.getTrainingsForObject(idSC);
		ArrayList<TrainingDTO> treninziDTO = new ArrayList<TrainingDTO>();
		for(Training t : trainings) {
			treninziDTO.add(new TrainingDTO(t));
		}
		return treninziDTO;
	}
	

}
