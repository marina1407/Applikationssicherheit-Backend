package ch.common.controller;

import ch.common.business.SemesterDAO;
import ch.common.controller.model.*;
import ch.common.exception.*;
import ch.common.model.dto.SemesterDto;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest-Schnittstelle für alle Methoden im zusammenhang mit dem Semester. 
 * @author Marina
 *
 */
@Path("/semester")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SemesterController {

    @EJB
    private SemesterDAO semesterDAO;

    @PUT
    public Response addSemester(AddSemesterDto semester) {
        try {
            SemesterDto persistedSemester = semesterDAO.addSemester(
                    semester.getBezeichnung(), semester.getSchultyp(), semester.getBenutzerId());
            return Response.ok(persistedSemester).build();
        } catch(SemesterExistsException | BenutzerNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getSemester(@PathParam("id")int id) {
        try {
            return Response.ok(semesterDAO.getSemester(id)).build();
        } catch (NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/benutzer/{id}")
    public Response getSemestersByUser(@PathParam("id")int id) {
            return Response.ok(new SemesterListDto(semesterDAO.getSemestersByUser(id))).build();
    }

    @POST
    public Response updateSemester(UpdateSemesterDto semester) {
        try {
            return Response.ok(semesterDAO.updateSemester(semester)).build();
        } catch (NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (BenutzerNotFoundException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSemester(@PathParam("id") int id) {
        try{
            semesterDAO.removeSemester(id);
            return Response.noContent().build();
        } catch(NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
