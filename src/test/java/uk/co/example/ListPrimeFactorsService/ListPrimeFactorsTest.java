package uk.co.example.ListPrimeFactorsService;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ListPrimeFactorsTest {
    @Test
    public void generateBaseExponentFactorsStringTest(){
        ArrayList<BaseExponent> input = new ArrayList<>( Arrays.asList(new BaseExponent(2L,13L),new BaseExponent(5L,1L),new BaseExponent(9L,1L)));
        assertEquals("2¹³ × 5 × 9", ListPrimeFactors.generateBaseExponentFactorsString(input));
    }
}