package com.baeldung.arraysort;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.Arrays;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Time taken by JUnit test cases can be seen in JUnit Runner
 * @author rchaudhary23
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortComparisonTest  {
    
    private int[]  sizeOfArrays  = { 1000, 10000, 100000, 1000000, 10000000 };
    
    private int[] _1000_elements_array;
    private int[] _10000_elements_array;
    private int[] _100000_elements_array;
    private int[] _1000000_elements_array;
    private int[] _10000000_elements_array;
    
    @Before
    public void setUp() throws Exception {
        
        _1000_elements_array = new int[sizeOfArrays[0]];
        _10000_elements_array = new int[sizeOfArrays[1]];
        _100000_elements_array = new int[sizeOfArrays[2]];
        _1000000_elements_array = new int[sizeOfArrays[3]];
        _10000000_elements_array = new int[sizeOfArrays[4]];
        
        Random random = new Random();
        for (int i = 0; i < sizeOfArrays[0]; i++) {
            _1000_elements_array[i] = random.nextInt(sizeOfArrays[0]) + random.nextInt(sizeOfArrays[0]);
        }
        
        for (int i = 0; i < sizeOfArrays[1]; i++) {
            _10000_elements_array[i] = random.nextInt(sizeOfArrays[1]) + random.nextInt(sizeOfArrays[1]);
        }
        
        for (int i = 0; i < sizeOfArrays[2]; i++) {
            _100000_elements_array[i] = random.nextInt(sizeOfArrays[2]) + random.nextInt(sizeOfArrays[2]);
        }
        
        for (int i = 0; i < sizeOfArrays[3]; i++) {
            _1000000_elements_array[i] = random.nextInt(sizeOfArrays[3]) + random.nextInt(sizeOfArrays[3]);
        }
        
        for (int i = 0; i < sizeOfArrays[4]; i++) {
            _10000000_elements_array[i] = random.nextInt(sizeOfArrays[4]) + random.nextInt(sizeOfArrays[4]);
        }
        
    }
    
    
    @Test
    public void testSort_fullArray() {
        
        int[] array = {10,4,6,2,1,9,7,8,3,5};
        int[] expected = {1,2,3,4,5,6,7,8,9,10};
        
        Arrays.sort(array);
        
        assertArrayEquals(expected, array);
        
    }
    
    @Test
    public void testSort_partialArray() {
        int[] array = {10,4,6,2,1,9,7,8,3,5};
        int[] expected = {10,4,1,2,6,7,8,9,3,5};
        
        Arrays.sort(array,2,8);
        
        assertArrayEquals(expected, array);
    }
    
    @Test
    public void testParallelSort_fullArray() {
        int[] array = {10,4,6,2,1,9,7,8,3,5};
        int[] expected = {1,2,3,4,5,6,7,8,9,10};
        
        Arrays.parallelSort(array);
        
        assertArrayEquals(expected, array);
    }
    
    @Test
    public void testParallelSort_partialArray() {
        int[] array = {10,4,6,2,1,9,7,8,3,5};
        int[] expected = {10,4,1,2,6,7,8,9,3,5};
        
        Arrays.parallelSort(array,2,8);
        
        assertArrayEquals(expected, array);
    }
    
    
    
    @Test
    public void test_1000_elements_sort() {
       
        int[] sequentialDataSet = Arrays.copyOf(_1000_elements_array, _1000_elements_array.length);
        System.out.println(sequentialDataSet.length);
        Arrays.sort(sequentialDataSet);
        
        assertNotNull(sequentialDataSet);
        assertNotSame(Arrays.copyOf(_1000_elements_array, _1000_elements_array.length), sequentialDataSet);
        
    }
    
    
    @Test
    public void test_1000_elements_parallelsort() {
        
        int[] parallelDataSet = Arrays.copyOf(_1000_elements_array, _1000_elements_array.length);
        System.out.println(parallelDataSet.length);
        Arrays.parallelSort(parallelDataSet);
        
        assertNotNull(parallelDataSet);
        assertNotSame(Arrays.copyOf(_1000_elements_array, _1000_elements_array.length), parallelDataSet);
        
    }
    
    
    @Test
    public void test_10000_elements_sort() {
        int[] sequentialDataSet = Arrays.copyOf(_10000_elements_array, _10000_elements_array.length);
        System.out.println(sequentialDataSet.length);
        Arrays.sort(sequentialDataSet);
        
        assertNotNull(sequentialDataSet);
        assertNotSame(Arrays.copyOf(_10000_elements_array, _10000_elements_array.length), sequentialDataSet);
    }
    
    
    @Test
    public void test_10000_elements_parallelsort() {
        int[] parallelDataSet = Arrays.copyOf(_10000_elements_array, _10000_elements_array.length);
        System.out.println(parallelDataSet.length);
        Arrays.parallelSort(parallelDataSet);
        
        assertNotNull(parallelDataSet);
        assertNotSame(Arrays.copyOf(_10000_elements_array, _10000_elements_array.length), parallelDataSet);
    }
    
    @Test
    public void test_100000_elements_sort() {
        int[] sequentialDataSet = Arrays.copyOf(_100000_elements_array, _100000_elements_array.length);
        System.out.println(sequentialDataSet.length);
        Arrays.sort(sequentialDataSet);
        
        assertNotNull(sequentialDataSet);
        assertNotSame(Arrays.copyOf(_100000_elements_array, _100000_elements_array.length), sequentialDataSet);
    }
    
    
    @Test
    public void test_100000_elements_parallelsort() {
        int[] parallelDataSet = Arrays.copyOf(_100000_elements_array, _100000_elements_array.length);
        System.out.println(parallelDataSet.length);
        Arrays.parallelSort(parallelDataSet);
        
        assertNotNull(parallelDataSet);
        assertNotSame(Arrays.copyOf(_100000_elements_array, _100000_elements_array.length), parallelDataSet);
    }

    
    @Test
    public void test_1000000_elements_sort() {
        int[] sequentialDataSet = Arrays.copyOf(_1000000_elements_array, _1000000_elements_array.length);
        System.out.println(sequentialDataSet.length);
        Arrays.sort(sequentialDataSet);
        
        assertNotNull(sequentialDataSet);
        assertNotSame(Arrays.copyOf(_1000000_elements_array, _1000000_elements_array.length), sequentialDataSet);
    }
    
    
    @Test
    public void test_1000000_elements_parallelsort() {
        int[] parallelDataSet = Arrays.copyOf(_1000000_elements_array, _1000000_elements_array.length);
        System.out.println(parallelDataSet.length);
        Arrays.parallelSort(parallelDataSet);
        
        assertNotNull(parallelDataSet);
        assertNotSame(Arrays.copyOf(_1000000_elements_array, _1000000_elements_array.length), parallelDataSet);
    }
    
    @Test
    public void test_10000000_elements_sort() {
        int[] sequentialDataSet = Arrays.copyOf(_10000000_elements_array, _10000000_elements_array.length);
        System.out.println(sequentialDataSet.length);
        Arrays.sort(sequentialDataSet);
        
        assertNotNull(sequentialDataSet);
        assertNotSame(Arrays.copyOf(_10000000_elements_array, _10000000_elements_array.length), sequentialDataSet);
    }
    
    
    @Test
    public void test_10000000_elements_parallelsort() {
        int[] parallelDataSet = Arrays.copyOf(_10000000_elements_array, _10000000_elements_array.length);
        System.out.println(parallelDataSet.length);
        Arrays.parallelSort(parallelDataSet);
        
        assertNotNull(parallelDataSet);
        assertNotSame(Arrays.copyOf(_10000000_elements_array, _10000000_elements_array.length), parallelDataSet);
    }
    

    @After
    public void tearDown() throws Exception {
        sizeOfArrays = null;
       _1000_elements_array = null;
       _10000_elements_array = null;
       _100000_elements_array = null;
       _1000000_elements_array = null;
       _10000000_elements_array = null;
    }

}
