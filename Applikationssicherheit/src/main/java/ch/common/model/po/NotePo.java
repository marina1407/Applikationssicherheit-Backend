package ch.common.model.po;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Hibernate-Klasse Note. 
 * @author Marina
 *
 */
@Entity
@Table(name = "Note")
public class NotePo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Double note;
	private Double gewichtung;
	private LocalDate datum;
	private String notiz;

	@ManyToOne
	@JoinColumn(name = "fach")
	private FachPo fach;

	/**
	 * @return the fach
	 */
	public FachPo getFach() {
		return fach;
	}

	/**
	 * @param fach the fach to set
	 */
	public void setFach(FachPo fach) {
		this.fach = fach;
	}

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
	 * @return the note
	 */
	public Double getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(Double note) {
		this.note = note;
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
	 * @return the datum
	 */
	public LocalDate getDatum() {
		return datum;
	}

	/**
	 * @param datum the datum to set
	 */
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	/**
	 * @return the notiz
	 */
	public String getNotiz() {
		return notiz;
	}

	/**
	 * @param notiz the notiz to set
	 */
	public void setNotiz(String notiz) {
		this.notiz = notiz;
	}

}
