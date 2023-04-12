package edu.dnu.fpm.pz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class SingleListTest {
    SingleList<Integer> list;

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    @Before
    public void setUp() throws Exception {
        list = new SingleList();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void getSize() throws Exception {
        //Given
        int expected = 3;
        //When
        int actual = list.getSize();
        //Then
        assertEquals(expected,actual);
    }

    @Test
    public void validInsert() throws InvalidDataException {
        //Given
        int expected = 10;
        //When
        list.insert(2,10);
        int actual = list.get(2);
        //Then
        assertEquals(expected,actual);
    }
    @Test
    public void invalidInsert()  throws InvalidDataException{
        //Given
        exception.expect(InvalidDataException.class);
        //When
        list.add(4);
        list.add(null);
    }

    @Test
    public void validGet() throws InvalidDataException {

        //Given
        int expected = 1;
        //When
        int actual = list.get(0);
        //Then
        assertEquals(expected,actual);
    }

    @Test
    public void invalidGet() throws InvalidDataException {
        //Given
        exception.expect(InvalidDataException.class);
        //When
        int actual = list.get(3);
    }

    @Test
    public void validReplace() throws InvalidDataException {
        //Given
        int expected = 3;
        //When
        int actual = list.replace(2,30);
        //Then
        assertEquals(expected,actual);
    }
    @Test
    public void invalidReplace() throws InvalidDataException {
        //Given
        exception.expect(InvalidDataException.class);
        //When
        int actual = list.replace(3, 40);
    }

    @Test
    public void validRemove() throws InvalidDataException {
        //Given
        int expected = 2;
        //When
        int actual = list.remove(1);
        //Then
        assertEquals(expected,actual);
    }

    @Test
    public void invalidRemove() throws InvalidDataException {
        //Given
        exception.expect(InvalidDataException.class);
        //When
        int actual = list.remove(3);
    }

    @Test
    public void isEmpty() throws InvalidDataException {
        //Given
        SingleList<Integer> list = new SingleList();
        boolean expected = true;
        //When
        boolean actual = list.isEmpty();
        //Then
        assertEquals(expected,actual);
    }
    @Test
    public void notEmpty() throws InvalidDataException {
        //Given
        boolean expected = false;
        //When
        boolean actual = list.isEmpty();
        //Then
        assertEquals(expected,actual);
    }
}