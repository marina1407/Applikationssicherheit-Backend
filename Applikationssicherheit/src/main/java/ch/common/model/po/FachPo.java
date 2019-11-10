package ch.common.model.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Hibernate-Klasse Fach.
 * @author Marina
 *
 */
@Entity
@Table(name = "Fach")
public class FachPo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String bezeichnung;
	private Double gewichtung;
	private String notizen;

	@ManyToOne
	@JoinColumn(name = "semester")
	private SemesterPo semester;

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
	 * @return the gewichtung
	 */
	public Double getGewichtung() {
		return gewichtung;
	}

	/**
	 * @param gewichtung the gewichtung to set
	 */
	public void setGewichtung(Double gewichtung) {
		this.gewichtung = gewichtung;
	}

	/**
	 * @return the notizen
	 */
	public String getNotizen() {
		return notizen;
	}

	/**
	 * @param notizen the notizen to set
	 */
	public void setNotizen(String notizen) {
		this.notizen = notizen;
	}

	/**
	 * @return the semester
	 */
	public SemesterPo getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(SemesterPo semester) {
		this.semester = semester;
	}

}
