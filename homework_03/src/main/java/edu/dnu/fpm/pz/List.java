package edu.dnu.fpm.pz;
/**
 * This is a parameterized interface List
 *
 *  @version 1.0 13 Mar 2023
 *  @author Denys Hudymov
 */
public interface List<T> {
    void add(T value) throws InvalidDataException;

    void insert(int index, T value ) throws InvalidDataException;

    T get(int index) throws InvalidDataException;

    T replace(int index, T value) throws InvalidDataException;

    T remove(int index) throws InvalidDataException;

    boolean isEmpty();

    void print();

    int getSize();

}
