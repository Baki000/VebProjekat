package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Product;
import beans.User;
import beans.UserCommon;
import dao.ProductDAO;
import dao.UserCommonDAO;
import dao.UserDAO;

@Path("/userCommon")
public class UserCommonService {
	@Context
	ServletContext ctx;
	
	public UserCommonService() {
		
	}
	
	@PostConstruct
	// ctx polje je null u konstruktoru, mora se pozvati nakon konstruktora (@PostConstruct anotacija)
	public void init() {
		// Ovaj objekat se instancira više puta u toku rada aplikacije
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
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserCommon user, @Context HttpServletRequest request) {
		UserCommonDAO userDao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		UserCommon loggedUser = userDao.find(user.getUserName(), user.getPassword());
		if (loggedUser != null) {
			return Response.status(400).entity("Invalid username and/or password").build();
		}
		request.getSession().setAttribute("user", loggedUser);
		return Response.status(200).build();
	}
	
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request) {
		request.getSession().invalidate();
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserCommon login(@Context HttpServletRequest request) {
		return (UserCommon) request.getSession().getAttribute("user");
	}
}
