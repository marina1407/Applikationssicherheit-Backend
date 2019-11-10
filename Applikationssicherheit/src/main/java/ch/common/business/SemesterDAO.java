package ch.common.business;

import ch.common.controller.model.UpdateSemesterDto;
import ch.common.exception.*;
import ch.common.model.dto.SemesterDto;
import ch.common.model.po.BenutzerPo;
import ch.common.model.po.SemesterPo;
import ch.common.persistence.dao.DataAccessObject;
import ch.common.util.PoDtoMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Persistenz-Schicht des Semesters.
 * @author Marina
 *
 */
@Stateless
public class SemesterDAO {

    @EJB
    private DataAccessObject dataAccessObject;

    @EJB
    private BenutzerDAO benutzerDAO;

    public SemesterDto addSemester(
            String bezeichnung, String schulTyp, int benutzerId)
            throws SemesterExistsException, BenutzerNotFoundException {

        SemesterPo persistedSemester = dataAccessObject.getEntityByBezeichnung(bezeichnung, SemesterPo.class);
        if (persistedSemester != null) {
            throw new SemesterExistsException("Es existiert bereits ein Semester mit dieser Bezeichnung");
        }
        try {
            BenutzerPo benutzer = benutzerDAO.getBenutzerEntity(benutzerId);

            SemesterPo semester = new SemesterPo();
            semester.setBezeichnung(bezeichnung);
            semester.setSchultyp(schulTyp);
            semester.setBenutzer(benutzer);

            return PoDtoMapper.semesterMapper(dataAccessObject.persist(semester));

        } catch(NoEntityFoundException e) {
            throw new BenutzerNotFoundException(e.getMessage());
        }
    }

    public SemesterDto getSemester(int id) throws NoEntityFoundException {
        return PoDtoMapper.semesterMapper(getSemesterEntity(id));
    }

    public SemesterPo getSemesterEntity(int id) throws NoEntityFoundException {

        SemesterPo semester = dataAccessObject.get(id, SemesterPo.class);
        if(semester == null) {
            throw new NoEntityFoundException("Es existiert kein Semester mit dieser Id");
        }
        return semester;
    }

    public SemesterDto updateSemester(UpdateSemesterDto semester) throws NoEntityFoundException, BenutzerNotFoundException {
        SemesterPo persistedSemester = dataAccessObject.get(semester.getId(), SemesterPo.class);
        if(persistedSemester == null) {
            throw new NoEntityFoundException("Es existiert kein Semester mit dieser Id");
        }

        if(semester.getBezeichnung() != null) {
            persistedSemester.setBezeichnung(semester.getBezeichnung());
        }
        if(semester.getSchultyp() != null) {
            persistedSemester.setSchultyp(semester.getSchultyp());
        }
        if(semester.getBenutzerId() != null) {
            try {
                BenutzerPo benutzer = benutzerDAO.getBenutzerEntity(semester.getBenutzerId());
                persistedSemester.setBenutzer(benutzer);
            } catch (NoEntityFoundException e) {
                throw new BenutzerNotFoundException(e.getMessage());
            }
        }

        dataAccessObject.persist(persistedSemester);
        return PoDtoMapper.semesterMapper(persistedSemester);
    }

    public void removeSemester(int id) throws NoEntityFoundException {
        SemesterPo semester = dataAccessObject.get(id, SemesterPo.class);
        if(semester == null) {
            throw new NoEntityFoundException("Es existiert kein Semester mit dieser Id.");
        }
        dataAccessObject.remove(semester);
    }

    public List<SemesterDto> getSemestersByUser(int userId) {

        List<SemesterPo> semesterEntities = dataAccessObject.findSemestersByUserId(userId);
        return semesterEntities.stream().map(PoDtoMapper::semesterMapper).collect(Collectors.toList());
    }
}
