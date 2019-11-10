package ch.common.exception;

/**
 * Falscher Benutzername oder falsches Passwort. 
 * @author Marina
 *
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
