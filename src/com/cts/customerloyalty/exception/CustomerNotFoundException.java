
package com.cts.customerloyalty.exception;
@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception {

    // Constructor that accepts a custom error message
    public CustomerNotFoundException(String message) {
        super(message);
    }

    // Constructor that accepts a custom error message and a cause
    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
















