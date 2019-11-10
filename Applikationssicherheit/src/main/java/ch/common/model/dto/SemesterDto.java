package ch.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataTransferObject Semester.
 * 
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDto {
	private int id;
	private String bezeichnung;
	private String schultyp;
	private BenutzerDto benutzer;
}
