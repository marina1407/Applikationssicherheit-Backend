package ch.common.exception;

/**
 * Das Semester wurde bereits angelegt. 
 * @author Marina
 *
 */
public class SemesterExistsException extends Exception {
    public SemesterExistsException(String message) {
        super(message);
    }
}
