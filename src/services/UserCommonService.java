package services;

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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Product;
import beans.User;
import formats.TimeFormat;
import formats.DateFormat;
import formats.DateTimeFormat;
import beans.UserCommon;
import dao.ProductDAO;
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
	
	
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<UserCommon> getAllUsers(){
		System.out.println("DOBIO SAM ZAHTEV");
		return UserCommonDAO.getInstance().getAllUsers();
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
	public UserCommon login(@Context HttpServletRequest request) {
		return (UserCommon) request.getSession().getAttribute("user");
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
	
	@PUT
	@Path("/checkIn")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkIn(@Context HttpServletRequest request) {
		UserCommonDAO dao = (UserCommonDAO) ctx.getAttribute("userCommonDAO");
		UserCommon loged = (UserCommon) request.getSession().getAttribute("user");
		if(dao.checkIn(loged.getId())) {        
			return Response.status(200).build();
		} else {
			return Response.status(400).build();
		}
		
	}
	
}
