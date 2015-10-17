package cz.cvut.iss.exception;

/**
 * @author jakubchalupa
 * @since 08.10.15
 */
public class ItemUnavailableException extends Exception {

    public ItemUnavailableException() {
        super("Item is unavailable for given count or price.");
    }

}
