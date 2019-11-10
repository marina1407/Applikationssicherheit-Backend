package ch.common.controller;

import ch.common.business.NoteDAO;
import ch.common.controller.model.*;
import ch.common.exception.DateInFutureException;
import ch.common.exception.FachNotFoundException;
import ch.common.exception.NoEntityFoundException;
import ch.common.model.dto.NoteDto;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest-Schnittstelle für alle Methoden im zusammenhang mit der Note. 
 * @author Marina
 *
 */
@Path("/note")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NoteController {

    @EJB
    private NoteDAO noteDAO;

    @PUT
    public Response AddNote(AddNoteDto note) {
        try {
            NoteDto persistedNote = noteDAO.addNote(note.getNote(), note.getGewichtung(), note.getDatum(),
                    note.getNotiz(), note.getFachId());

            return Response.ok(persistedNote).build();
        } catch(FachNotFoundException | DateInFutureException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getNote(@PathParam("id")int id) {
        try {
            return Response.ok(noteDAO.getNote(id)).build();
        } catch (NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/fach/{id}")
    public Response getNoteByFach(@PathParam("id")int id) {
        return Response.ok(new NoteListDto(noteDAO.getNoteByFach(id))).build();
    }

    @POST
    public Response updateNote(UpdateNoteDto note) {
        try {
            return Response.ok(noteDAO.updateNote(note)).build();
        } catch (NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (FachNotFoundException | DateInFutureException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deteNote(@PathParam("id") int id) {
        try{
             noteDAO.removeNote(id);
            return Response.noContent().build();
        } catch(NoEntityFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
