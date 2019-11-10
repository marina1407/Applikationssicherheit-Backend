package ch.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataTransferObject Benutzer.
 * 
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenutzerDto {
	private int id;
	private String vorname;
	private String nachname;
	private String email;
}
