package ch.common.exception;

/**
 * Das Fach wurde bereits angelegt. 
 * @author Marina
 *
 */
public class FachExistsException extends Exception {
    public FachExistsException(String message) {
        super(message);
    }
}
