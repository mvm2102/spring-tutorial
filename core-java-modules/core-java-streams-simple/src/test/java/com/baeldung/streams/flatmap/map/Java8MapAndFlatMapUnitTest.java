package com.baeldung.streams.flatmap.map;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class Java8MapAndFlatMapUnitTest {

    @Test
    void givenStream_whenCalledMap_thenProduceList() {
        List<String> myList = Stream.of("a", "b")
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        assertEquals(asList("A", "B"), myList);
    }

    @Test
    void givenStream_whenCalledFlatMap_thenProduceFlattenedList() {
        List<List<String>> list = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
        System.out.println(list);

        System.out.println(list.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList()));
    }

    @Test
    void givenOptional_whenCalledMap_thenProduceOptional() {
        Optional<String> s = Optional.of("test");
        assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
    }

    @Test
    void givenOptional_whenCalledFlatMap_thenProduceFlattenedOptional() {
        assertEquals(Optional.of(Optional.of("STRING")), Optional.of("string")
            .map(s -> Optional.of("STRING")));

        assertEquals(Optional.of("STRING"), Optional.of("string")
            .flatMap(s -> Optional.of("STRING")));
    }

    @Test
    void givenMaps_whenCalledFlatMap_thenMultipleMapsMergedIntoOneMap() {

        Map<String, Integer> playerMap1 = new HashMap<String, Integer>() {{
            put("Kai", 92);
            put("Liam", 100);
        }};
        Map<String, Integer> playerMap2 = new HashMap<String, Integer>() {{
            put("Eric", 42);
            put("Kevin", 77);
        }};
        Map<String, Integer> playerMap3 = new HashMap<String, Integer>() {{
            put("Saajan", 35);
        }};

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("Saajan", 35);
            put("Liam", 100);
            put("Kai", 92);
            put("Eric", 42);
            put("Kevin", 77);
        }};

        Map<String, Integer> mergedMap = Stream.of(playerMap1, playerMap2, playerMap3)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        assertEquals(expectedMap, mergedMap);
    }

    @Test
    void givenMapsWithDuplicateKeys_whenCalledFlatMap_thenMultipleMapsMergedIntoOneMap() {

        Map<String, Integer> playerMap1 = new HashMap<String, Integer>() {{
            put("Kai", 92);
            put("Liam", 100);
        }};
        Map<String, Integer> playerMap2 = new HashMap<String, Integer>() {{
            put("Eric", 42);
            put("Kevin", 77);
        }};
        Map<String, Integer> playerMap3 = new HashMap<String, Integer>() {{
            put("Saajan", 35);
        }};
        Map<String, Integer> playerMap4 = new HashMap<String, Integer>() {{
            put("Kai", 76);
        }};

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("Saajan", 35);
            put("Liam", 100);
            put("Kai", 76);
            put("Eric", 42);
            put("Kevin", 77);
        }};

        assertThrows(IllegalStateException.class, () -> Stream.of(playerMap1, playerMap2, playerMap3, playerMap4)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), "Duplicate key Kai (attempted merging values 92 and 76)");

        Map<String, Integer> mergedMap = Stream.of(playerMap1, playerMap2, playerMap3, playerMap4)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, newValue) -> newValue));

        assertEquals(expectedMap, mergedMap);
    }

    @Test
    void givenMapsWithDuplicateKeysAndNullValues_whenCalledFlatMap_thenMultipleMapsMergedIntoOneMap() {

        Map<String, Integer> playerMap1 = new HashMap<String, Integer>() {{
            put("Kai", 92);
            put("Liam", 100);
        }};
        Map<String, Integer> playerMap2 = new HashMap<String, Integer>() {{
            put("Eric", 42);
            put("Kevin", 77);
        }};
        Map<String, Integer> playerMap3 = new HashMap<String, Integer>() {{
            put("Saajan", 35);
        }};
        Map<String, Integer> playerMap4 = new HashMap<String, Integer>() {{
            put("Kai", 76);
        }};
        Map<String, Integer> playerMap5 = new HashMap<String, Integer>() {{
            put("Kai", null);
            put("Jerry", null);
        }};

        Map<String, Integer> expectedMap = new HashMap<String, Integer>() {{
            put("Saajan", 35);
            put("Liam", 100);
            put("Kai", 92);
            put("Eric", 42);
            put("Kevin", 77);
            put("Jerry", null);
        }};

        assertThrows(NullPointerException.class, () -> Stream.of(playerMap1, playerMap2, playerMap3, playerMap4, playerMap5)
            .flatMap(map -> map.entrySet()
                .stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (existing, newValue) -> newValue)));

        Map<String, Integer> mergedMap = new HashMap<>();
        Stream.of(playerMap1, playerMap2, playerMap3, playerMap4, playerMap5)
            .flatMap(map -> map.entrySet()
                .stream())
            .forEach(entry -> {
                String k = entry.getKey();
                Integer v = entry.getValue();
                if (mergedMap.containsKey(k)) {
                    mergedMap.put(k, maxInteger(mergedMap.get(k), v));
                } else {
                    mergedMap.put(k, v);
                }
            });
        assertEquals(expectedMap, mergedMap);
    }

    private Integer maxInteger(Integer int1, Integer int2) {
        if (int1 == null) {
            return int2;
        }
        if (int2 == null) {
            return int1;
        }
        return max(int1, int2);
    }

}