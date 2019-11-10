package ch.common.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Update Fach.
 * @author Marina
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFachDto {
    @NotNull
    private int id;
    private String bezeichnung;
    private Double gewichtung;
    private String notizen;
    private Integer semesterId;
}
