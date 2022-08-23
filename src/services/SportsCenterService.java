package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.SportsCenter;
import beans.UserCommon;
import dao.SportsCenterDAO;
import dao.UserCommonDAO;
import startup.OnStartUp;

@Path("/sportsCenters")
public class SportsCenterService {

	@Context
	ServletContext ctx;
	
	public SportsCenterService() {}
	
	@PostConstruct
	public void init() {
		
		if (ctx.getAttribute("sportsCenterDAO") == null) {
			String contextPath = ctx.getRealPath("");
			OnStartUp.getInstance(contextPath);
			ctx.setAttribute("sportsCenterDAO", SportsCenterDAO.getInstance());
			
		}
		
	}
	
	public SportsCenterDAO getSportsCenterDAO() {
		return (SportsCenterDAO) ctx.getAttribute("sportsCenterDAO");
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportsCenter> getAllSportsCenters(){
		System.out.println("DOBIO SAM ZAHTEV getAll");
		return getSportsCenterDAO().getAllSportsCenters();
	}
	
	@GET
	@Path("/search/{tekst}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<SportsCenter> pretrazi(@PathParam("tekst") String tekst){
		System.out.println("Search usao Service");
		return getSportsCenterDAO().pretrazi(tekst);
	}
	
	@POST
	@Path("/addCenter")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SportsCenter newCenter(SportsCenter user) {
		System.out.println("UPAOOOO");
		SportsCenterDAO dao = (SportsCenterDAO) ctx.getAttribute("sportsCenterDAO");
		return dao.save(user);
	}
}
