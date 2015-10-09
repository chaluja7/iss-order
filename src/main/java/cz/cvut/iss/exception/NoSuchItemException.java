package cz.cvut.iss.exception;

/**
 * @author jakubchalupa
 * @since 08.10.15
 */
public class NoSuchItemException extends Exception {

    public NoSuchItemException(String sku) {
        super("No such item with sku " + sku);
    }

}
