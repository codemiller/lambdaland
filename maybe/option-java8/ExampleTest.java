package com.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.example.Example.readPositiveIntParam;
import static junit.framework.Assert.assertEquals;

/*
 * Option monad example in Java 8 from http://java.dzone.com/articles/no-more-excuses-use-null
 *
 * @author Mario Fusco
 */
public class ExampleTest {

    @Test
    public void testMap() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("a", "5");
        param.put("b", "true");
        param.put("c", "-3");

        // the value of the key "a" is a String representing a positive int so return it
        assertEquals(5, readPositiveIntParam(param, "a"));

        // returns zero since the value of the key "b" is not an int
        assertEquals(0, readPositiveIntParam(param, "b"));

        // returns zero since the value of the key "c" is an int but it is negative
        assertEquals(0, readPositiveIntParam(param, "c"));

        // returns zero since there is no key "d" in the map
        assertEquals(0, readPositiveIntParam(param, "d"));
    }
}
