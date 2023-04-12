package edu.dnu.fpm.pz;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.min;

public class App {

    public static String formatInt(List<Integer> numbers){
        return  numbers.stream()
                .map(x -> (x % 2 ==0 ?"e" + x:"o" + x))
                .collect(Collectors.joining(","));
    }

    public static Map<String, City> getLargestCityPerState(Collection<City> cities){
        return cities.stream()
                .sorted((c1, c2) -> Long.compare(c2.getPopulation(), c1.getPopulation()))
                .collect(Collectors.toMap(
                        City::getState,
                        city -> city,
                        (c1,c2)->c1.getPopulation() >= c2.getPopulation() ? c1:c2
                        )
                );
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> firstList = first.toList();
        List<T> secondList = second.toList();
        var min_size = min(firstList.size(), secondList.size());
        return IntStream.range(0, min_size)
                .mapToObj(index -> List.of(firstList.get(index), secondList.get(index)))
                .flatMap(List::stream);
    }

    public static void main(String[] args) {



    }
}
