package ch.common.business;

import ch.common.controller.model.UpdateFachDto;
import ch.common.exception.*;
import ch.common.model.dto.FachDto;
import ch.common.model.po.FachPo;
import ch.common.model.po.SemesterPo;
import ch.common.persistence.dao.DataAccessObject;
import ch.common.util.PoDtoMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Persistenz-Schicht des Faches.
 * @author Marina
 *
 */
@Stateless
public class FachDAO {

    @EJB
    private DataAccessObject dataAccessObject;

    @EJB
    private SemesterDAO semesterDAO;

    public FachDto addFach(
            String bezeichnung, Double gewichtung, String notizen, int semesterId)
            throws FachExistsException, SemesterNotFoundException {

        FachPo persistedFach = dataAccessObject.<FachPo>getEntityByBezeichnung(bezeichnung, FachPo.class);
        if (persistedFach != null) {
            throw new FachExistsException("Es existiert bereits ein Fach mit dieser Bezeichnung");
        }

        try{
            SemesterPo semesterPo = semesterDAO.getSemesterEntity(semesterId);
            FachPo newFach = new FachPo();
            newFach.setBezeichnung(bezeichnung);
            newFach.setGewichtung(gewichtung);
            newFach.setNotizen(notizen);
            newFach.setSemester(semesterPo);

            return PoDtoMapper.fachMapper(dataAccessObject.persist(newFach));

        } catch(NoEntityFoundException e) {
            throw new SemesterNotFoundException(e.getMessage());
        }
    }

    public FachDto getFach(int id) throws NoEntityFoundException {
        return PoDtoMapper.fachMapper(getFachEntity(id));
    }

    public FachPo getFachEntity(int id) throws NoEntityFoundException {

        FachPo fach = dataAccessObject.get(id, FachPo.class);
        if(fach == null) {
            throw new NoEntityFoundException("Es existiert kein Fach mit dieser Id");
        }
        return fach;
    }


    public FachDto updateFach(UpdateFachDto fach) throws NoEntityFoundException, SemesterNotFoundException {
        FachPo persistedFach = dataAccessObject.get(fach.getId(), FachPo.class);
        if(persistedFach == null) {
            throw new NoEntityFoundException("Es existiert kein Fach mit dieser Id");
        }

        if(fach.getBezeichnung() != null) {
            persistedFach.setBezeichnung(fach.getBezeichnung());
        }
        if(fach.getGewichtung() != null) {
            persistedFach.setGewichtung(fach.getGewichtung());
        }
        if(fach.getNotizen() != null) {
            persistedFach.setNotizen(fach.getNotizen());
        }
        if(fach.getSemesterId() != null) {
            try{
                SemesterPo semesterPo = semesterDAO.getSemesterEntity(fach.getSemesterId());
                persistedFach.setSemester(semesterPo);
            } catch(NoEntityFoundException e) {
                throw new SemesterNotFoundException(e.getMessage());
            }
        }

        dataAccessObject.persist(persistedFach);
        return PoDtoMapper.fachMapper(persistedFach);
    }

    public void removeFach(int id) throws NoEntityFoundException {
        FachPo fach = dataAccessObject.get(id, FachPo.class);
        if(fach == null) {
            throw new NoEntityFoundException("Es existier kein Fach mit dieser Id.");
        }
        dataAccessObject.remove(fach);
    }

    public List<FachDto> getFachBySemesterId(int semesterId) {

        List<FachPo> subjectEntitites = dataAccessObject.findSubjectsBySemesterId(semesterId);
        return subjectEntitites.stream().map(PoDtoMapper::fachMapper).collect(Collectors.toList());
    }
}
