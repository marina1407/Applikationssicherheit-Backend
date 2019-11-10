package ch.common.controller;

import ch.common.business.FachDAO;
import ch.common.controller.model.*;
import ch.common.exception.FachExistsException;
import ch.common.exception.NoEntityFoundException;
import ch.common.exception.SemesterNotFoundException;
import ch.common.model.dto.FachDto;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest-Schnittstelle für alle Methoden im zusammenhang mit dem Fach. 
 * @author Marina
 *
 */
@Path("/fach")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FachController {

    @EJB
    private FachDAO fachDAO;

    @PUT
    public Response addFach(AddFachDto fach) {
        try {
            FachDto persistedFach = fachDAO.addFach(
                    fach.getBezeichnung(), fach.getGewichtung(), fach.getNotizen(), fach.getSemesterId());

            return Response.ok(persistedFach).build();
        } catch(FachExistsException | SemesterNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getFach(@PathParam("id")int id) {
        try {
            return Response.ok(fachDAO.getFach(id)).build();
        } catch (NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/semester/{id}")
    public Response getFachBySemester(@PathParam("id")int id) {
        return Response.ok(new FachListDto(fachDAO.getFachBySemesterId(id))).build();
    }

    @POST
    public Response updateFach(UpdateFachDto fach) {
        try {
            return Response.ok(fachDAO.updateFach(fach)).build();
        } catch (NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SemesterNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFach(@PathParam("id") int id) {
        try{
            fachDAO.removeFach(id);
            return Response.noContent().build();
        } catch(NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
