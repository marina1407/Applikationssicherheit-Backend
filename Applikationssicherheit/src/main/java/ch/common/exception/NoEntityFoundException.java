package ch.common.exception;

/**
 * Keine Entity gefunden. 
 * @author Marina
 *
 */
public class NoEntityFoundException extends Exception {
    public NoEntityFoundException(String message) {
        super(message);
    }
}
