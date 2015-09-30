package cz.cvut.iss.exception;

/**
 * @author jakubchalupa
 * @since 30.09.15
 */
public class NoSuchOrderException extends Exception {

    public NoSuchOrderException(long id) {
        super("No such order with id " + id);
    }

}
