package ch.common.exception;

/**
 * Das Fach konnte nicht gefunden werden. 
 * @author Marina
 *
 */
public class FachNotFoundException extends Exception {
    public FachNotFoundException(String message) {
        super(message);
    }
}
