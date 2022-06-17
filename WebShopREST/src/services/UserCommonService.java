package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import beans.Letovanje;
import beans.Product;
import beans.UserCommon;
import dao.LetovanjeDAO;
import dao.ProductDAO;

@Path("/userCommon")
public class UserCommonService {
	@Context
	ServletContext ctx;
	
	public UserCommonService() {
		
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira vi�e puta u toku rada aplikacije
		// Inicijalizacija treba da se obavi samo jednom
		if (ctx.getAttribute("userCommonDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
			ctx.setAttribute("userCommonDAO", new UserCommonDAO(contextPath));
		}
	}
	
	private UserCommonDAO getUserCommonDAO() {
		return (UserCommonDAO) ctx.getAttribute("userCommonDAO");
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserCommon> getAllUsers(){
		System.out.println("DOBIO SAM ZAHTEV");
		return getUserCommonDAO().getAllUsers();
	}
}
