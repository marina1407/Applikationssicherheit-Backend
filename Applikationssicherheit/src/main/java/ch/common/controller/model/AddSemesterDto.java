package ch.common.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Hinzufügen vom Semester.
 *
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSemesterDto {
    @NotNull
    private String bezeichnung;
    @NotNull
    private String schultyp;
    @NotNull
    private int benutzerId;
}
