package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Product;
import beans.SportsCenter;
import beans.User;
import formats.TimeFormat;
import formats.DateFormat;
import formats.DateTimeFormat;
import dto.UserCommonDTO;
import beans.UserCommon;
import dao.ProductDAO;
import dao.SportsCenterDAO;
import dao.UserCommonDAO;
import dao.UserDAO;
import startup.OnStartUp;

@Path("/users")
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
	    	OnStartUp.getInstance(contextPath);
			ctx.setAttribute("userCommonDAO", UserCommonDAO.getInstance());
			
		}
		
	}
	
	
	public UserCommonDAO getUserCommonDAO() {
		return (UserCommonDAO) ctx.getAttribute("userCommonDAO");
	}
	
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserCommonDTO> getAllUsers(){
		System.out.println("uc service: upao u getallUsers");
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		Collection<UserCommon> usersi = dao.getAllUsers();
		ArrayList<UserCommonDTO> usersDTO = new ArrayList<UserCommonDTO>();
		for(UserCommon u : usersi) {
			usersDTO.add(new UserCommonDTO(u));
		}
		return usersDTO;
		
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserCommonDTO getKorisnik(@PathParam("id") int id) { 
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		return new UserCommonDTO(dao.getById(id));
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserCommon user, @Context HttpServletRequest request) {
		System.out.println("service: upao u login");
		UserCommonDAO userDao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		UserCommon loggedUser = userDao.find(user.getUserName(), user.getPassword());
		if (loggedUser == null) {
			System.out.println("service: nall je");
			return Response.status(400).entity("Invalid username and/or password").build();
		}
		request.getSession().setAttribute("user", loggedUser);
		return Response.status(200).build();
	}
	
	
	@POST
	@Path("/registration")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserCommon newProduct(UserCommon user) {
		System.out.println("service: upao u registration");
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		return dao.save(user);
	}
	
	
	@GET
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserCommon user = (UserCommon) session.getAttribute("user");
		if(user != null) {
			session.invalidate();
			return Response.status(200).build();
		}else {
			return Response.status(400).entity("User already logged out!").build();
		}
		
	}
	
	@GET
	@Path("/currentUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserCommonDTO currentUser(@Context HttpServletRequest request) {
		
		UserCommon logovani = (UserCommon) request.getSession().getAttribute("user");
		if(logovani == null) {
			return null;
		} else {
			UserCommonDTO logovaniDTO = new UserCommonDTO(logovani);
			return logovaniDTO;
		}
	}
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserCommon changeOne(UserCommon user) {
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		return dao.update(user);
	}
	
	
	@GET
	@Path("/getFreeManagers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserCommon> getFreeManagers() {
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		return dao.getFreeManagers();
	}
	
	@GET
	@Path("/getCustomersforSC")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserCommon> getCustomersforSC(@QueryParam("scID") int scID) {
		System.out.println("Upao u servisu u getCusForSC id je " + scID);
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		return dao.getCustomersforSC(scID);
	}
	

	@PUT
	@Path("/checkIn/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkIn(@Context HttpServletRequest request, @PathParam("id") String id) {
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		UserCommon loged = (UserCommon) request.getSession().getAttribute("user");
		int id2 = Integer.parseInt(id);
		if(dao.checkIn(loged.getId(), id2)) {        
			return Response.status(200).build();
		} else {
			return Response.status(400).build();
		}
		
	}
	

	
	@GET
	@Path("/search/{tekst}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<UserCommon> pretrazi(@PathParam("tekst") String tekst){
		System.out.println("Search usao Service");
		return getUserCommonDAO().search(tekst);
	}
	
	@GET
	@Path("/getAllTrainers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserCommonDTO> getTrainers() {
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		Collection<UserCommon> treneri = dao.getTrainers();
		ArrayList<UserCommonDTO> treneriDTO = new ArrayList<UserCommonDTO>();
		for(UserCommon t : treneri) {
			treneriDTO.add(new UserCommonDTO(t));
		}
		return treneriDTO;
	}
}
