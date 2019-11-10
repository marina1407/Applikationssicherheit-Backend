package ch.common.model.dto;

import ch.common.controller.adapter.LocalDateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * DataTransferObject Note.
 * 
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
	private int id;
	private Double note;
	private Double gewichtung;
	private FachDto fach;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate datum;
	private String notiz;
}
