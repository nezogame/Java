package edu.dnu.fpm.pz;

import java.util.Arrays;

public class SingleList <T> implements List<T>{

    public class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }

    private int size;
    Node<T> head;

    @Override
    public int getSize() {
        return size;
    }

    private Node<T> getNode(int index){
        if(index==-1){
            return null;
        }
        var node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public SingleList() {
        head =null;
        size=0;
    }

    @Override
    public void add(T value) throws InvalidDataException {
        insert(size,value,"class: SingleList, method: add");
    }

    public void insert(int index, T value, String source) throws InvalidDataException {

        DataValidator.validateNotNull(value,source);
        DataValidator.validateIndex(index, size+1,source);
        if (index == 0) {
            var newNode = new Node<>(value);
            newNode.next = head;
            head = newNode;
        }else{
            var current = getNode(index - 1);
            var newNode = new Node<>(value);
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }
    @Override
    public void insert(int index, T value) throws InvalidDataException {
        insert(index,value,"class: SingleList, method: insert");
    }


    @Override
    public void print() {
        if(isEmpty()){
            System.out.println("SingleList is empty");
            return;
        }
        System.out.println("SingleList elements: ");
        for (int i = 0; i <size ; i++) {
            System.out.println(getNode(i).value + " ");
        }
    }

    @Override
    public T get(int index) throws InvalidDataException {
        DataValidator.validateIndex(index,size,"class: SingleList, method: get");
        return getNode(index).value;
    }

    @Override
    public T replace(int index, T value) throws InvalidDataException {
        DataValidator.validateIndex(index,size+1,"class: SingleList, method: replace");
        var removed=getNode(index).value;
        insert(index,value,"class: SingleList, method: replace");
        size--;
        return removed;
    }

    @Override
    public T remove(int index) throws InvalidDataException {

        DataValidator.validateIndex(index,size+1,"class: SingleList, method: remove");
        var removed = getNode(index).value;
        if (index == 0) {
            head = head.next;
        } else {
            var prev = getNode(index - 1);
            var current = prev.next;
            prev.next = current.next;
        }
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
