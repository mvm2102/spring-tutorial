package com.baeldung.maths;

import java.math.BigInteger;

import org.junit.Assert;

import org.junit.Test;

public class BigIntegerImplTest {

    @Test
    public void givenBigIntegerNumbers_whenAddedTogether_thenGetExpectedResult() {
        BigInteger numStarsMilkyWay = new BigInteger("8731409320171337804361260816606476");
        BigInteger numStarsAndromeda = new BigInteger("5379309320171337804361260816606476");

        BigInteger totalStars = numStarsMilkyWay.add(numStarsAndromeda);
        BigInteger result = new BigInteger("14110718640342675608722521633212952");

        Assert.assertEquals(result, totalStars);
    }

}
