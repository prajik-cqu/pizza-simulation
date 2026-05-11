package cqu.pizza.lifecycle;

/**
 * Runtime exception thrown when an error occurs saving the simulation report to a file.
 *
 * @author student
 */
public class ReportException extends RuntimeException {

    /**
     * Constructs a ReportException with the given error message.
     *
     * @param errorMessage a description of the error
     */
    public ReportException(String errorMessage) {
        super(errorMessage);
    }
}
