package services;

import java.time.LocalDate;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Fee;
import beans.UserCommon;
import dao.FeeDAO;
import dao.UserCommonDAO;
import enums.FeeStatus;
import formats.DateFormat;
import startup.OnStartUp;

@Path("/fees")
public class FeeService {
	
	
	@Context
	ServletContext ctx;
	

	public FeeService() {
	}
	
	@PostConstruct
	public void init() {
		if (ctx.getAttribute("clanarinaDAO") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	OnStartUp.getInstance(contextPath);
			ctx.setAttribute("feeDAO", FeeDAO.getInstance());
		}
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fee save(Fee fee,@Context HttpServletRequest request) {
		FeeDAO dao = (FeeDAO) ctx.getAttribute("feeDAO");
		UserCommon logged = (UserCommon) request.getSession().getAttribute("user");
		if(logged == null) {
			return null;
		}
		if(logged.getFee() != null) {
			logged.getFee().setStatus(FeeStatus.EXPIRED);
		}
		logged.setFee(fee);
		fee.setCustomer(logged);
		fee = dao.newFee(fee);
		UserCommonDAO.getInstance().update(logged);
		return fee;
	}
	
	@POST
	@Path("/setSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setSelected(Fee fee, @Context HttpServletRequest request) {
		request.getSession().setAttribute("chosenFee", fee);
		return Response.status(200).build();
	}
	
	@GET
	@Path("/getSelected")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Fee getSelected( @Context HttpServletRequest request) {
		Fee fee = (Fee)request.getSession().getAttribute("chosenFee");
		if(fee == null) {
			return null;
		}
		fee.setPayDay(DateFormat.dateToString(LocalDate.now()));
		return fee;
	}
	
	
}
