package ch.common.util;

import ch.common.model.dto.BenutzerDto;
import ch.common.model.dto.FachDto;
import ch.common.model.dto.NoteDto;
import ch.common.model.dto.SemesterDto;
import ch.common.model.po.BenutzerPo;
import ch.common.model.po.FachPo;
import ch.common.model.po.NotePo;
import ch.common.model.po.SemesterPo;

/**
 * Mapper-Klasse um das Dto ins Po und umgekehrt zu mappen.
 * 
 * @date 22.10.2019
 * @author Marina
 */
public class PoDtoMapper {

	public static BenutzerPo benutzerMapper(BenutzerDto benutzerDto) {
		BenutzerPo benutzerPo = new BenutzerPo();
		benutzerPo.setId(benutzerDto.getId());
		benutzerPo.setVorname(benutzerDto.getVorname());
		benutzerPo.setNachname(benutzerDto.getNachname());
		benutzerPo.setEmail(benutzerDto.getEmail());
		return benutzerPo;
	}

	public static BenutzerDto benutzerMapper(BenutzerPo benutzerPo) {
		BenutzerDto benutzerDto = new BenutzerDto();
		benutzerDto.setId(benutzerPo.getId());
		benutzerDto.setVorname(benutzerPo.getVorname());
		benutzerDto.setNachname(benutzerPo.getNachname());
		benutzerDto.setEmail(benutzerPo.getEmail());
		return benutzerDto;
	}

	public static FachPo fachMapper(FachDto fachDto) {
		FachPo fachPo = new FachPo();
		fachPo.setId(fachDto.getId());
		fachPo.setBezeichnung(fachDto.getBezeichnung());
		fachPo.setGewichtung(fachDto.getGewichtung());
		fachPo.setNotizen(fachDto.getNotizen());
		SemesterPo semester = semesterMapper(fachDto.getSemester());
		fachPo.setSemester(semester);
		return fachPo;
	}

	public static FachDto fachMapper(FachPo fachPo) {
		FachDto fachDto = new FachDto();
		fachDto.setId(fachPo.getId());
		fachDto.setBezeichnung(fachPo.getBezeichnung());
		fachDto.setGewichtung(fachPo.getGewichtung());
		fachDto.setNotizen(fachPo.getNotizen());
		SemesterDto semester = semesterMapper(fachPo.getSemester());
		fachDto.setSemester(semester);
		return fachDto;
	}

	public static SemesterPo semesterMapper(SemesterDto semesterDto) {
		SemesterPo semesterPo = new SemesterPo();
		semesterPo.setId(semesterDto.getId());
		semesterPo.setBezeichnung(semesterDto.getBezeichnung());
		semesterPo.setSchultyp(semesterDto.getSchultyp());
		BenutzerPo benutzer = benutzerMapper(semesterDto.getBenutzer());
		semesterPo.setBenutzer(benutzer);
		return semesterPo;
	}

	public static SemesterDto semesterMapper(SemesterPo semesterPo) {
		SemesterDto semesterDto = new SemesterDto();
		semesterDto.setId(semesterPo.getId());
		semesterDto.setBezeichnung(semesterPo.getBezeichnung());
		semesterDto.setSchultyp(semesterPo.getSchultyp());
		BenutzerDto benutzer = benutzerMapper(semesterPo.getBenutzer());
		semesterDto.setBenutzer(benutzer);
		return semesterDto;
	}

	public static NotePo noteMapper(NoteDto noteDto) {
		NotePo notePo = new NotePo();
		notePo.setId(noteDto.getId());
		notePo.setNote(noteDto.getNote());
		FachPo fach = fachMapper(noteDto.getFach());
		notePo.setFach(fach);
		notePo.setGewichtung(noteDto.getGewichtung());
		notePo.setDatum(noteDto.getDatum());
		notePo.setNotiz(noteDto.getNotiz());
		return notePo;
	}

	public static NoteDto noteMapper(NotePo notePo) {
		NoteDto noteDto = new NoteDto();
		noteDto.setId(notePo.getId());
		noteDto.setNote(notePo.getNote());
		FachDto fach = fachMapper(notePo.getFach());
		noteDto.setFach(fach);
		noteDto.setGewichtung(notePo.getGewichtung());
		noteDto.setDatum(notePo.getDatum());
		noteDto.setNotiz(notePo.getNotiz());
		return noteDto;
	}
}
