package edu.dnu.fpm.pz;

import java.time.LocalDateTime;
public class MyException extends Exception {
    MyException(String message, Throwable e) {
        super(message, e);
    }
    MyException(String message) {
        super(message);
    }
    void toLog() {
        String message = "Date: " + LocalDateTime.now() +
                " Class and method: " + getStackTrace()[getStackTrace().length - 1] +
                " Type: " + getClass() +
                " Text: " + getMessage();
        System.out.println(message);
    }
}
