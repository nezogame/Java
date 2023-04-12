package edu.dnu.fpm.pz;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookStoreTest {

    private BookStore bookStore =new BookStore();
    @Before
    public void setUp() throws Exception {
        Book b1=new Book("first","Bob","434");
        Book b2=new Book("second","Bob","124");
        Book b3=new Book("third","Jon","925");
        Book b4=new Book("fourth","Jon","2321");
        Book b5=new Book("fifth","Jon","1135");

        bookStore.addBook(b1);
        bookStore.addBook(b2);
        bookStore.addBook(b3);
        bookStore.addBook(b4);
        bookStore.addBook(b5);
    }

    @Test
    public void testAddBook() {
        //GIVE

        Book b1=new Book("six","Bob","434");
        Book b2=new Book("seven","Bob","124");
        Integer expectedFirst = 4;
        Integer expectedSecond = 3;

        //WHEN
        bookStore.addBook(b1);
        bookStore.addBook(b2);
        Integer actualFirst = bookStore.getAuthorBooks().get("Bob");
        Integer actualSecond = bookStore.getAuthorBooks().get("Jon");
        //THEN
        assertEquals(expectedFirst,actualFirst);
        assertEquals(expectedSecond,actualSecond);
    }

    @Test
    public void testRemoveBook() {
        //GIVE
        Book b4=new Book("fourth","Jon","2321");
        Book b5=new Book("fifth","Jon","1135");
        Integer expectedFirst = 2;
        Integer expectedSecond = 1;
        //WHEN
        bookStore.removeBook(b4);
        bookStore.removeBook(b5);
        Integer actualFirst = bookStore.getAuthorBooks().get("Bob");
        Integer actualSecond = bookStore.getAuthorBooks().get("Jon");
        //THEN
        assertEquals(expectedFirst,actualFirst);
        assertEquals(expectedSecond,actualSecond);
    }
}