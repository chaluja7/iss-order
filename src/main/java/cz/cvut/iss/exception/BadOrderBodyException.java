package cz.cvut.iss.exception;

import cz.cvut.iss.model.Order;

/**
 * @author jakubchalupa
 * @since 30.09.15
 */
public class BadOrderBodyException extends Exception {

    public BadOrderBodyException(Order o) {
        super("Bad order body");
    }

}
