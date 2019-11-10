package ch.common.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Update Semester.
 * @author Marina
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSemesterDto {
    private int id;
    private String bezeichnung;
    private String schultyp;
    private Integer benutzerId;
}
