package ch.common.controller.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hinzufügen vom Benutzer.
 *
 * @version 1.0
 * @date 17.09.2019
 * @author Marina Stucki
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBenutzerDto {
    @NotNull
    private String vorname;
    @NotNull
    private String nachname;
    @NotNull
    private String email;
    @NotNull
    private String passwort;
}
