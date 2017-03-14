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
public class KwetterException extends Exception {

    /**
     * Creates a new instance of <code>KwetterException</code> without detail
     * message.
     */
    public KwetterException() {
    }

    /**
     * Constructs an instance of <code>KwetterException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public KwetterException(String msg) {
        super(msg);
    }
}
