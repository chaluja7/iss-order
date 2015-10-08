package cz.cvut.iss.exception;

/**
 * @author jakubchalupa
 * @since 30.09.15
 */
public class AccountingResponseException extends Exception {

    public AccountingResponseException() {
        super("Error while contacting accounting.");
    }

}
