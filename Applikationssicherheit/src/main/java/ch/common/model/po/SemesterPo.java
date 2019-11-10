package ch.common.model.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ch.common.model.dto.BenutzerDto;

/**
 * Hibernate-Klasse Semester.
 * @author Marina
 *
 */
@Entity
@Table(name = "Semester")
public class SemesterPo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bezeichnung;
	private String schultyp;

	@ManyToOne
	@JoinColumn(name = "benutzer")
	private BenutzerPo benutzer;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the bezeichnung
	 */
	public String getBezeichnung() {
		return bezeichnung;
	}

	/**
	 * @param bezeichnung the bezeichnung to set
	 */
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	/**
	 * @return the schultyp
	 */
	public String getSchultyp() {
		return schultyp;
	}

	/**
	 * @param schultyp the schultyp to set
	 */
	public void setSchultyp(String schultyp) {
		this.schultyp = schultyp;
	}

	/**
	 * @return the benutzer
	 */
	public BenutzerPo getBenutzer() {
		return benutzer;
	}

	/**
	 * @param benutzer the benutzer to set
	 */
	public void setBenutzer(BenutzerPo benutzer) {
		this.benutzer = benutzer;
	}

}
