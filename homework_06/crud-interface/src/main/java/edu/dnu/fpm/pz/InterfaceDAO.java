package edu.dnu.fpm.pz;

import java.util.List;

public interface InterfaceDAO<T> {
    void create(T entity) throws MyException;

    void create(List<T> entities) throws MyException;

    List<T> read() throws MyException;

    void update(T entity) throws MyException;

    void update(List<T> entities) throws MyException;

    void delete(int entityId) throws MyException;
}
