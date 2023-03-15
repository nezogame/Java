package edu.dnu.fpm.pz;

import java.util.Date;

/**
 * This is an exception class InvalidDataException that extends the Exception
 *
 *  @version 1.0 13 Mar 2023
 *  @author Denys Hudymov
 */
public class InvalidDataException extends Exception {

    public InvalidDataException(String message) {
        super("Date: "+java.time.LocalDateTime.now()+", Source: "+message);
    }

}
