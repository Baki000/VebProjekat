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

import beans.SportsCenter;
import beans.Training;
import beans.UserCommon;
import dao.SportsCenterDAO;
import dao.TrainingDAO;
import dao.UserCommonDAO;
import startup.OnStartUp;

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
		TrainingDAO dao = (TrainingDAO) ctx.getAttribute("treningDAO");
		return dao.findAll();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(BookedTrainingDTO zakazanTrainingDTO, @Context HttpServletRequest request) {
		BookedTrainingDAO dao = (BookedTrainingDAO) ctx.getAttribute("bookedTrainingDAO");
		BookedTraining zakazanTraining = new BookedTraining();
		zakazanTraining.setIntID(zakazanTrainingDTO.getIntID());
		zakazanTraining.setTerminTraininga(zakazanTrainingDTO.getTerminTraininga());
		zakazanTraining.setStatusTraininga(zakazanTrainingDTO.getStatusTraininga());
		UserCommon kupac = (UserCommon)request.getSession().getAttribute("user");
		zakazanTraining.setKupac(kupac);
		UserCommon trener = UserCommonDAO.getInstance().find(zakazanTrainingDTO.getTrenerIntId());
		zakazanTraining.setTrener(trener);
		SportsCenter objekat = SportsCenterDAO.getInstance().findObjekat(zakazanTrainingDTO.getObjekatIntId());
		zakazanTraining.setObjekatGdePripada(objekat);
		
		dao.save(zakazanTraining);
			
		return Response.status(200).build();
	}	
	
	@GET
	@Path("/getPersonalTrainings")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<BookedTrainingDTO> getPersonalniTreninziZaTrenera(@QueryParam("idUserCommona") int idUserCommona) {
		BookedTrainingDAO dao = (BookedTrainingDAO) ctx.getAttribute("bookedTrainingDAO");
		ArrayList<BookedTraining> personalniTreninzi = dao.getPersonalniTreninziZaTrenera(idUserCommona);
		ArrayList<BookedTrainingDTO> personalniTreninziDTO = new ArrayList<BookedTrainingDTO>();
		for(BookedTraining t : personalniTreninzi) {
			personalniTreninziDTO.add(new BookedTrainingDTO(t));
		}
		return personalniTreninziDTO;
	}
	
	@DELETE
	@Path("/{id}")
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
