package ch.common.exception;

/**
 * Das Datum liegt in der Zukunft. 
 * @author Marina
 *
 */
public class DateInFutureException extends Exception {
    public DateInFutureException(String message) {
        super(message);
    }
}
