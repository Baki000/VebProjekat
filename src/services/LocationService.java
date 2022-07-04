package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import dao.LocationDAO;
import startup.OnStartUp;

@Path("/locations")
public class LocationService {
	@Context
	ServletContext ctx;
	
	public LocationService() {}
	
	@PostConstruct
	public void init() {
		
		if (ctx.getAttribute("locationDAO") == null) {
			String contextPath = ctx.getRealPath("");
			ctx.setAttribute("locationDAO", new LocationDAO(contextPath));
			
		}
		
	}
	
	public LocationDAO getLocationDAO() {
		return (LocationDAO) ctx.getAttribute("locationDAO");
	}
	
	

}
