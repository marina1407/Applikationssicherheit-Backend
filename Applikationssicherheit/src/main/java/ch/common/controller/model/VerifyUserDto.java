package ch.common.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Beutzer Authentifizierung.
 * @author Marina
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyUserDto {
    private String email;
    private String passwort;
}
