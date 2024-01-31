package com.baeldung.firstnonrepeating;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstNonRepeatingElementUnitTest {

    private List<Integer> list;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList(1, 2, 3, 2, 1, 4, 5, 4);
    }

    @Test
    public void whenUsingForLoop_thenReturnFirstNonRepeatingElement() {
        int result = FirstNonRepeatingElement.findFirstNonRepeatingUsingForLoop(list);
        assertEquals(3, result);
    }

    @Test
    public void whenUsingIndexOf_thenReturnFirstNonRepeatingElement() {
        int result = FirstNonRepeatingElement.findFirstNonRepeatedElementUsingIndex(list);
        assertEquals(3, result);
    }

    @Test
    public void whenUsingHashMap_thenReturnFirstNonRepeatingElement() {
        int result = FirstNonRepeatingElement.findFirstNonRepeatingUsingHashMap(list);
        assertEquals(3, result);
    }

    @Test
    public void whenUsingArray_thenReturnFirstNonRepeatingElement() {
        int result = FirstNonRepeatingElement.findFirstNonRepeatingUsingArray(list);
        assertEquals(3, result);
    }
}
