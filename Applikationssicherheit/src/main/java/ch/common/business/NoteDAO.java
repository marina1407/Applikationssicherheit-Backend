package ch.common.business;

import ch.common.controller.model.UpdateNoteDto;
import ch.common.exception.*;
import ch.common.model.dto.FachDto;
import ch.common.model.dto.NoteDto;
import ch.common.model.po.FachPo;
import ch.common.model.po.NotePo;
import ch.common.persistence.dao.DataAccessObject;
import ch.common.util.PoDtoMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Persistenz-Schicht der Note.
 * @author Marina
 *
 */
@Stateless
public class NoteDAO {

    @EJB
    private DataAccessObject dataAccessObject;

    @EJB
    private FachDAO fachDAO;

    public NoteDto addNote(
            Double noteValue,
            Double gewichtung,
            LocalDate datum,
            String notiz,
            int fachId)
            throws FachNotFoundException, DateInFutureException {

        if(datum.isAfter(LocalDate.now())) {
            throw new DateInFutureException("Das Datum der Note darf nicht in der Zufunft liegen");
        }

        try {
            FachPo fach = fachDAO.getFachEntity(fachId);

            NotePo note = new NotePo();
            note.setNote(noteValue);
            note.setGewichtung(gewichtung);
            note.setDatum(datum);
            note.setNotiz(notiz);
            note.setFach(fach);

            return PoDtoMapper.noteMapper(dataAccessObject.persist(note));

        } catch(NoEntityFoundException e) {
            throw new FachNotFoundException(e.getMessage());
        }
    }

    public NoteDto getNote(int id) throws NoEntityFoundException {
        return PoDtoMapper.noteMapper(getNoteEntity(id));
    }

    public NotePo getNoteEntity(int id) throws NoEntityFoundException {

        NotePo note = dataAccessObject.get(id, NotePo.class);
        if(note == null) {
            throw new NoEntityFoundException("Es existiert keine Note mit dieser Id");
        }
        return note;
    }

    public NoteDto updateNote(UpdateNoteDto note) throws NoEntityFoundException, FachNotFoundException, DateInFutureException {

        if(note.getDatum().isAfter(LocalDate.now())) {
            throw new DateInFutureException("Das Datum der Note darf nicht in der Zufunft liegen");
        }

        NotePo persistedNote = dataAccessObject.get(note.getId(), NotePo.class);
        if(persistedNote == null) {
            throw new NoEntityFoundException("Es existiert keine Note mit dieser Id");
        }

        if(note.getNote() != null) {
            persistedNote.setNote(note.getNote());
        }
        if(note.getGewichtung() != null) {
            persistedNote.setGewichtung(note.getGewichtung());
        }
        if(note.getDatum() != null) {
            persistedNote.setDatum(note.getDatum());
        }
        if(note.getNotiz() != null) {
            persistedNote.setNotiz(note.getNotiz());
        }
        if(note.getFachId() != null) {
            try {
                FachPo fach = fachDAO.getFachEntity(note.getFachId());
                persistedNote.setFach(fach);
            } catch (NoEntityFoundException e) {
                throw new FachNotFoundException(e.getMessage());
            }
        }

        dataAccessObject.persist(persistedNote);
        return PoDtoMapper.noteMapper(persistedNote);
    }

    public void removeNote(int id) throws NoEntityFoundException {
        NotePo note = dataAccessObject.get(id, NotePo.class);
        if(note == null) {
            throw new NoEntityFoundException("Es existiert keine Note mit dieser Id.");
        }
        dataAccessObject.remove(note);
    }

    public List<NoteDto> getNoteByFach(int userId) {

        List<NotePo> semesterEntities = dataAccessObject.findMarksBySubjectId(userId);
        return semesterEntities.stream().map(PoDtoMapper::noteMapper).collect(Collectors.toList());
    }
}
