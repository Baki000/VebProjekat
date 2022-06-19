package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.SportsCenter;
import dao.SportsCenterDAO;

@Path("/sportsCenters")
public class SportsCenterService {

	@Context
	ServletContext ctx;
	
	public SportsCenterService() {}
	
	@PostConstruct
	public void init() {
		
		if (ctx.getAttribute("sportsCenterDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("sportsCenterDAO", new SportsCenterDAO(contextPath));
		}
		
	}
	
	private SportsCenterDAO getSportsCenterDAO() {
		return (SportsCenterDAO) ctx.getAttribute("sportsCenterDAO");
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<SportsCenter> getAllSportsCenters(){
		System.out.println("DOBIO SAM ZAHTEV");
		return getSportsCenterDAO().getAllSportsCenters();
	}
}
