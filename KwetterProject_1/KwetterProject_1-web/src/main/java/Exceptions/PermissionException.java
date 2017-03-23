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
public class PermissionException extends Exception {

    /**
     * Creates a new instance of <code>PermissionException</code> without detail
     * message.
     */
    public PermissionException() {
    }

    /**
     * Constructs an instance of <code>PermissionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PermissionException(String msg) {
        super(msg);
    }
}
