package ch.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataTransferObject Fach.
 * 
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FachDto {
	private int id;
	private String bezeichnung;
	private Double gewichtung;
	private String notizen;
	private SemesterDto semester;
}
