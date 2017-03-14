/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author jeffrey
 */
public class UserException extends Exception {

    /**
     * Creates a new instance of <code>KwetterException</code> without detail
     * message.
     */
    public UserException() {
    }

    /**
     * Constructs an instance of <code>KwetterException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserException(String msg) {
        super(msg);
    }

    public UserException(String message, Exception e) {
        super(message, e);
    }
}
