package ch.common.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Update Benutzer.
 *
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBenutzerDto {
    @NotNull
    private int id;
    private String vorname;
    private String nachname;
    private String email;
    private String passwort;
}
