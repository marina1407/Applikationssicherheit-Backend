package ch.common.controller;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import ch.common.business.BenutzerDAO;
import ch.common.controller.model.AddBenutzerDto;
import ch.common.controller.model.UpdateBenutzerDto;
import ch.common.controller.model.VerifyUserDto;
import ch.common.exception.EmailExistsException;
import ch.common.exception.InvalidCredentialsException;
import ch.common.exception.NoEntityFoundException;
import ch.common.model.dto.BenutzerDto;

/**
 * Rest-Schnittstelle für alle Methoden im Zusammenhang mit dem Benutzer.
 * 
 * @author Marina
 *
 */
@Path("/benutzer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BenutzerController {

	static Logger logger = Logger.getLogger(BenutzerController.class);

	@EJB
	private BenutzerDAO benutzerDAO;

	/**
	 * Rest-Service um den Benutzer hinzuzufügen. 
	 * @param benutzer
	 * @return
	 */
	@PUT
	public Response addBenutzer(AddBenutzerDto benutzer) {
		try {
			logger.info("Benutzer " + benutzer.getEmail() + " wird versucht hinzuzufügen");
			BenutzerDto persistedBenutzer = benutzerDAO.addBenutzer(benutzer.getVorname(), benutzer.getNachname(),
					benutzer.getEmail(), benutzer.getPasswort());

			return Response.ok(persistedBenutzer).build();
		} catch (EmailExistsException e) {
			logger.error(e);
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	/**
	 * BEnutzername und Passwort überprüfen. 
	 * @param verifyUserInput
	 * @return
	 */
	@POST
	@Path("/verify")
	public Response verifyCredentials(VerifyUserDto verifyUserInput) {
		try {
			// Passwort wird verschlüsselt aus DB geholt. 
			BenutzerDto benutzer = benutzerDAO.verifyBenutzer(verifyUserInput.getEmail(),
					verifyUserInput.getPasswort());
			return Response.ok(benutzer).build();
		} catch (InvalidCredentialsException | NoEntityFoundException e) {
			logger.error(e);
			return Response.status(Response.Status.BAD_REQUEST).entity("Wrong user/password").build();
		}
	}

	/**
	 * Suche den Benutzer anhand der ID.
	 * @param id
	 * @return
	 */
	@GET
	@Path("/{id}")
	public Response getBenutzer(@PathParam("id") int id) {
		try {
			return Response.ok(benutzerDAO.getBenutzer(id)).build();
		} catch (NoEntityFoundException e) {
			logger.error(e);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	public Response updateBenutzer(UpdateBenutzerDto benutzer) {
		try {
			return Response.ok(benutzerDAO.updateBenutzer(benutzer)).build();
		} catch (NoEntityFoundException e) {
			logger.error(e);
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteBenutzer(@PathParam("id") int id) {
		try {
			benutzerDAO.removeBenutzer(id);
			return Response.noContent().build();
		} catch (NoEntityFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}


}
