package edu.dnu.fpm.pz;

import java.util.Arrays;

/**
 * This is a class ArrayList
 *
 *  @version 1.0 13 Mar 2023
 *  @author Denys Hudymov
 */
public class ArrayList <T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] list;

    @Override
    public int getSize() {
        return size;
    }

    public T[] getList() {
        return list;
    }

    public void setList(T[] list) {
        this.list = list;
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int  initialCapacity) {
        list = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) throws InvalidDataException {
        insert(size,value,"class: ArrayList, method: add");
    }

    public void insert(int index, T value, String source) throws InvalidDataException {

        DataValidator.validateNotNull(value,source);
        if (getSize() == list.length) {
            resize();
        }
        DataValidator.validateIndex(index, list.length,source);
        list[index] = value;
        size++;
    }
    @Override
    public void insert(int index, T value) throws InvalidDataException {
        insert(index,value,"class: ArrayList, method: insert");
    }
    private void resize(){
        list = Arrays.copyOf(list, getSize()*2);
    }

    @Override
    public void print() {
        if(isEmpty()){
            System.out.println("ArrayList is empty");
            return;
        }
        System.out.println("ArrayList elements");
        for (var l: list) {
            System.out.println(l + " ");
        }
    }

    @Override
    public T get(int index) throws InvalidDataException {
        DataValidator.validateIndex(index,list.length,"class: ArrayList, method: get");
        return list[index];
    }

    @Override
    public T replace(int index, T value) throws InvalidDataException {
        DataValidator.validateIndex(index,size,"class: ArrayList, method: replace");
        var removed=list[index];
        insert(index,value,"class: ArrayList, method: replace");
        size--;
        return removed;
    }

    @Override
    public T remove(int index) throws InvalidDataException {
        DataValidator.validateIndex(index,size,"class: ArrayList, method: remove");
        var removed = list[index];
        list[index]=null;
        T[] tmp =   (T[]) new Object[list.length];
        int tmpIndex =0;
        for(var item: list){
            if(item!=null){
                tmp[tmpIndex] = item;
                tmpIndex++;
            }
        }
        list = tmp;
        size--;
        return removed;
    }

    @Override
    public boolean isEmpty() {
        if (getSize() == 0){
           return true;
        }
        return false;
    }
}
