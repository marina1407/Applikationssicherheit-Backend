package ch.common.exception;

/**
 * Für die Email ist bereits ein Benutzer registriert. 
 * @author Marina
 *
 */
public class EmailExistsException extends Exception {
    public EmailExistsException(String message) {
        super(message);
    }
}
