package ch.common.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Hinzufügen vom Fach.
 *
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFachDto {
    @NotNull
    private String bezeichnung;
    @NotNull
    private Double gewichtung;
    private String notizen;
    @NotNull
    private int semesterId;
}
