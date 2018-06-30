package se.consys.silverkissen.controllers;

import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import se.consys.silverkissen.dataaccess.user.UserHibernateImpl;
import se.consys.silverkissen.dataaccess.user.iUserDao;
import se.consys.silverkissen.entities.Log;
import se.consys.silverkissen.entities.TokenObject;
import se.consys.silverkissen.entities.User;
import se.consys.silverkissen.utilities.TokenIssuer;
import se.consys.silverkissen.viewmodels.UserViewModel;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
	
	private iUserDao dao = new UserHibernateImpl();
	private TokenIssuer tokenIssuer = new TokenIssuer();
	
	@POST
	public Response Login(User user) {
		boolean valid = dao.find(user.getEmail(), user.getPassword());
		System.out.println("valid: " + valid);
		if (valid) {
			String token = tokenIssuer.issueToken(user.getEmail());
			System.out.println("token: " + token);
			TokenObject obj = new TokenObject(token);
			System.out.println("obj: " + obj);
			return Response.ok(obj).build();
		} else {
			Log log = new Log(Log.Messages.UNAUTHORIZED);
			dao.saveLog(log);
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
