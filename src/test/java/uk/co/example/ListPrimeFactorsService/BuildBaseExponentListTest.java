package uk.co.example.ListPrimeFactorsService;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BuildBaseExponentListTest {

    @Test
    void build() {
        ArrayList<Long> factors = new ArrayList<>(Arrays.asList(5L,3L,5L,2L,3L,3L));
        ArrayList<BaseExponent> actual = BuildBaseExponentList.build(factors);
        ArrayList<BaseExponent> expected = new ArrayList<>(Arrays.asList(new BaseExponent(2L,1L),new BaseExponent(3L,3L),new BaseExponent(5L,2L)));
        assertArrayEquals(expected.toArray(),actual.toArray());
    }
}