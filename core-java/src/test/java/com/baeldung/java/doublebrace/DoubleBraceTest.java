package com.baeldung.java.doublebrace;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DoubleBraceTest {

    @Test
    public void givenSet_whenWithoutDoubleBraces_getWorksSuccessfully() {
        final Set<String> countriesList = new HashSet<String>();
        countriesList.add("India");
        countriesList.add("USSR");
        countriesList.add("USA");
        assertTrue(countriesList.contains("India"));
    }

    @Test
    public void givenSet_whenDoubleBraces_getWorksSuccessfully() {
        final Set<String> countriesList = new HashSet<String>() {

            {
                add("India");
                add("USSR");
                add("USA");
            }
        };
        assertTrue(countriesList.contains("India"));
    }

    @Test
    public void givenUnmodifiableSet_whenWithoutDoubleBraces_getsSuccessfully() {
        final Set<String> countriesList = Collections.unmodifiableSet(Stream.of("India", "USSR", "USA").collect(toSet()));
        assertTrue(countriesList.contains("India"));
    }

}
