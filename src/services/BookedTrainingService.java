package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.BookedTraining;
import beans.SportsCenter;
import beans.Training;
import beans.UserCommon;
import dao.BookedTrainingDAO;
import dao.SportsCenterDAO;
import dao.TrainingDAO;
import dao.UserCommonDAO;
import dto.BookedTrainingDTO;
import startup.OnStartUp;

@Path("/bookedTraining")
public class BookedTrainingService {

	@Context
	ServletContext ctx;
	

	public BookedTrainingService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("bookedTrainingDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	OnStartUp.getInstance(contextPath);
			ctx.setAttribute("bookedTrainingDAO", BookedTrainingDAO.getInstance());
		}
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Training> getTreninzi() {
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("trainingDAO");
		return dao.findAll();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(BookedTrainingDTO bookedTrainingDTO, @Context HttpServletRequest request) {
		BookedTrainingDAO dao = (BookedTrainingDAO) ctx.getAttribute("bookedTrainingDAO");
		BookedTraining bookedTraining = new BookedTraining();
		bookedTraining.setId(bookedTrainingDTO.getId());
		bookedTraining.setTrainingDate(bookedTrainingDTO.getTrainingDate());
		bookedTraining.setTrainingStatus(bookedTrainingDTO.getTrainingStatus());
		UserCommon customer = (UserCommon)request.getSession().getAttribute("user");
		bookedTraining.setCustomer(customer);
		UserCommon trainer = UserCommonDAO.getInstance().getById(bookedTrainingDTO.getTrainerId());
		bookedTraining.setTrainer(trainer);
		SportsCenter center = SportsCenterDAO.getInstance().getById(bookedTrainingDTO.getCenterId());
		bookedTraining.setCenterWhereExists(center);
		
		System.out.println("selected treenr iz DTO " + bookedTrainingDTO.getTrainerId() +" "+  trainer.getName());
		dao.save(bookedTraining);
			
		return Response.status(200).build();
	}	
	
	@GET
	@Path("/getPersonalTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BookedTrainingDTO> getPersonalTrainingsForTrainer(@QueryParam("userID") int userID) {
		BookedTrainingDAO dao = (BookedTrainingDAO) ctx.getAttribute("bookedTrainingDAO");
		ArrayList<BookedTraining> personalniTreninzi = dao.getPersonalTrainingsForTrainer(userID);
		ArrayList<BookedTrainingDTO> personalniTreninziDTO = new ArrayList<BookedTrainingDTO>();
		for(BookedTraining t : personalniTreninzi) {
			personalniTreninziDTO.add(new BookedTrainingDTO(t));
		}
		return personalniTreninziDTO;
	}
	
	@DELETE
	@Path("/cancelTraining/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) { //izmeni u int
		BookedTrainingDAO dao = (BookedTrainingDAO) ctx.getAttribute("bookedTrainingDAO");
		
		boolean uspesno = dao.delete(id);
		if(uspesno) {
			return Response.status(200).build();
		}else {
			return Response.status(400).build();
		}
	}

}
