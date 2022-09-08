package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Training;
import beans.TrainingHistory;
import beans.UserCommon;
import dao.TrainingHistoryDAO;
import startup.OnStartUp;

@Path("/trainingHistory")
public class TrainingHistoryService {

	@Context
	ServletContext ctx;

	public TrainingHistoryService() {
	}

	@PostConstruct
	public void init() {
		if (ctx.getAttribute("trainingHistoryDAO") == null) {
			String contextPath = ctx.getRealPath("");
			OnStartUp.getInstance(contextPath);
			ctx.setAttribute("trainingHistoryDAO", TrainingHistoryDAO.getInstance());
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingHistory> findAll() {
		TrainingHistoryDAO dao = (TrainingHistoryDAO) ctx.getAttribute("trainingHistoryDAO");
		return dao.findAll();
	}

	@GET
	@Path("/getTHforUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<TrainingHistory> getTHforUser(@QueryParam("userID") int userID) {
		TrainingHistoryDAO dao = (TrainingHistoryDAO) ctx.getAttribute("trainingHistoryDAO");
		System.out.println("THservice, userid je " + userID);
		ArrayList<TrainingHistory> treninzi = (ArrayList<TrainingHistory>) dao.getTHforUser(userID);

		return treninzi;
	}

	@POST
	@Path("/checkIn")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TrainingHistory napravi(Training trening, @Context HttpServletRequest request) {
		UserCommon logovani = (UserCommon) request.getSession().getAttribute("user");
		TrainingHistoryDAO dao = (TrainingHistoryDAO) ctx.getAttribute("trainingHistoryDAO");

		return dao.check(trening, logovani);
	}

	/*
	 *
	 * 
	 * @GET
	 * 
	 * @Path("/search")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public ArrayList<TrainingHistory>
	 * search(@QueryParam("searchObjekat") String
	 * searchObjekat, @QueryParam("pocetno") String pocetno,
	 * 
	 * @QueryParam("krajnje") String krajnje, @Context HttpServletRequest request){
	 * 
	 * Korisnik logovani = (Korisnik) request.getSession().getAttribute("user");
	 * LocalDateTime p = DateTimeHelper.stringToDateTime(pocetno); LocalDateTime k =
	 * DateTimeHelper.stringToDateTime(krajnje);
	 * 
	 * trainingHistoryDAO dao = (trainingHistoryDAO)
	 * ctx.getAttribute("trainingHistoryDAO");
	 * 
	 * return dao.search(searchObjekat, p, k, logovani); }
	 */

}
