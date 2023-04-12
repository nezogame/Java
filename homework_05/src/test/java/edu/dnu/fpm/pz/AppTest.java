package edu.dnu.fpm.pz;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AppTest extends TestCase {

    public void testFormatInt() {
        //GIVE
        List<Integer> numbers= Arrays.asList(1, 3, 4, 12);
        String expected = "o1,o3,e4,e12";
        //WHEN
        String actual = App.formatInt(numbers);
        //THEN
        assertEquals(expected,actual);
    }

    public void testGetLargestCityPerState() {
        //GIVE
        Collection<City> cities = List.of(
                new City("New York City", "New York", 8623000),
                new City("Los Angeles", "California", 3990000),
                new City("Houston", "Texas", 2310000),
                new City("San Antonio", "Texas", 1460000),
                new City("San Diego", "California", 1430000)
        );
        String firstExpected = "New York City";
        String secondExpected = "Los Angeles";
        String thirdExpected = "Houston";
        //WHEN
        Map<String, City> actual =  App.getLargestCityPerState(cities);
        //THEN
        assertEquals(firstExpected,actual.get("New York").getName());
        assertEquals(secondExpected,actual.get("California").getName());
        assertEquals(thirdExpected,actual.get("Texas").getName());
    }

    public void testZip() {
        //GIVE
        Stream<Integer> first = IntStream.rangeClosed(1, 5).boxed();
        Stream<Integer> second = IntStream.iterate(10, i -> i + 10).limit(7).boxed();
        Stream<Integer> expected = IntStream.of(1,10,2,20,3,30,4,40,5,50 ).boxed();;
        //WHEN
        Stream<Integer> actual =  App.zip(first,second);
        //THEN
        assertEquals(expected.collect(Collectors.toList()),actual.collect(Collectors.toList()));
    }
}