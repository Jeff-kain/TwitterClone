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
public class KweetException extends Exception {

    /**
     * Creates a new instance of <code>KweetException</code> without detail
     * message.
     */
    public KweetException() {
    }

    /**
     * Constructs an instance of <code>KweetException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public KweetException(String msg) {
        super(msg);
    }

    public KweetException(String message, Exception e) {
        super(message, e);
    }
}
