package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.SportsCenter;
import beans.Training;
import beans.UserCommon;
import dao.TrainingDAO;
import dao.UserCommonDAO;
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
	public Collection<TrainingDTO> getGroupTrainingsForTrainer(@QueryParam("userID") int userID) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		ArrayList<Training> grupniTreninzi = dao.getGroupTrainingsForTrainer(userID);
		ArrayList<TrainingDTO> grupniTreninziDTO = new ArrayList<TrainingDTO>();
		for(Training t : grupniTreninzi) {
			grupniTreninziDTO.add(new TrainingDTO(t));
		}
		return grupniTreninziDTO;
	}
	
	@PUT
	@Path("/cancelTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public Response OtkaziTrening(int id) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		
		return null;
	}
	
	@PUT
	@Path("/updateTraining")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void changeOne(TrainingDTO treningDTO) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		Training trening = new Training();
		trening.setIntId(treningDTO.getId());
		trening.setName(treningDTO.getName());
		trening.setTrainingType(treningDTO.getTrainingType());
		trening.setSportsCenter(treningDTO.getSportsCenter());
		trening.setDuration(treningDTO.getDuration());
		trening.setDescription(treningDTO.getDescription());
		trening.setPictureURL(treningDTO.getPictureURL());
		UserCommon trener = UserCommonDAO.getInstance().getById(treningDTO.getTrainerID());
		trening.setTrainer(trener);
		
		dao.update(trening);
	}
	
	@POST
	@Path("/setSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSelected(TrainingDTO trainingDTO, @Context HttpServletRequest request) {
		Training trening = TrainingDAO.getInstance().getById(trainingDTO.getId());
		request.getSession().setAttribute("selectedTraining", trening);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TrainingDTO getSelected( @Context HttpServletRequest request) {
		Training trening = (Training)request.getSession().getAttribute("selectedTraining");
		return new TrainingDTO(trening);
	}
	
	@POST
	@Path("/addTraining")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(TrainingDTO treningDTO, @Context HttpServletRequest request) {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		if(dao.exists(treningDTO.getName())) {
			return Response.status(400).entity("This name already exists!").build();
		} else {
			Training trening = new Training();
			trening.setIntId(treningDTO.getId());
			trening.setName(treningDTO.getName());
			trening.setTrainingType(treningDTO.getTrainingType());
			UserCommon menadzer = (UserCommon)request.getSession().getAttribute("user");
			SportsCenter sc = menadzer.getSportsCenter(); 
			trening.setSportsCenter(sc);
			trening.setDuration(treningDTO.getDuration());
			trening.setDescription(treningDTO.getDescription());
			trening.setPictureURL(treningDTO.getPictureURL());
			UserCommon trener = UserCommonDAO.getInstance().getById(treningDTO.getTrainerID());
			trening.setTrainer(trener);
			
			dao.save(trening);
			return Response.status(200).build();
		}	
	}
	
	
	

}
