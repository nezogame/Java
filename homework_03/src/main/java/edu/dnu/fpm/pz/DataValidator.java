package edu.dnu.fpm.pz;


/**
 * This is a class DataValidator
 *
 *  @version 1.0 13 Mar 2023
 *  @author Denys Hudymov
 */
public class DataValidator {

    public static void validateIndex(int index, int capacity,String source ) throws InvalidDataException {
        if (index < 0 || index >= capacity) {
            throw new InvalidDataException(source+", Error type: Index out of bounds");
        }
    }

    public static <T> void validateNotNull(T item,String source) throws InvalidDataException {
        if (item == null) {
            throw new InvalidDataException(source+", Error type: Item cannot be null");
        }
    }
}
