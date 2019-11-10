package ch.common.exception;

/**
 * Semester konnte nicht gefunden werden. 
 * @author Marina
 *
 */
public class SemesterNotFoundException extends Exception {
    public SemesterNotFoundException(String message) {
        super(message);
    }
}
