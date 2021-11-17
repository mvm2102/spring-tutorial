package com.baeldung.getTestName;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JUnit4ParameterizedTestNameUnitTest {

    private final String input;
    private final String expected;
    @Rule
    public TestName name = new TestName();

    public JUnit4ParameterizedTestNameUnitTest(final String input, final String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> suppliedData() {
        return Arrays.asList(new Object[][] { { "abc", "abc" }, { "cba", "abc" }, { "onm", "mno" }, { "a", "a" }, { "zyx", "xyz" }, });
    }

    private static String sortCharacters(final String s) {
        final char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    @Test
    public void givenString_whenSort_thenVerifySortForString() {
        System.out.println("displayName = " + name.getMethodName());
        assertEquals(expected, sortCharacters(input));
    }
}
