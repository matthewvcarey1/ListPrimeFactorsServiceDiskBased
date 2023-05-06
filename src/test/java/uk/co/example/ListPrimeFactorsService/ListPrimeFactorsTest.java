package uk.co.example.ListPrimeFactorsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ListPrimeFactorsTest {
    TreeSet<Long> primes;
    @BeforeEach
    void setUp() {
        primes = new TreeSet<>();
        primes.add(2L);
        primes.add(3L);
        primes.add(5L);
        primes.add(7L);
        primes.add(11L);
        primes.add(13L);
        primes.add(17L);
        primes.add(19L);
        primes.add(23L);
        primes.add(29L);
        primes.add(31L);
        primes.add(37L);
        primes.add(41L);
        primes.add(43L);
        primes.add(47L);
        primes.add(53L);
        try {
            ListPrimeFactors.removeInstance();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterEach
    void tearDown() {
        primes = null;
    }


    @Test
    public void generateBaseExponentFactorsStringTest(){
        ArrayList<BaseExponent> input = new ArrayList<>( Arrays.asList(new BaseExponent(2L,13L),new BaseExponent(5L,1L),new BaseExponent(9L,1L)));
        assertEquals("2¹³ × 5 × 9", ListPrimeFactors.generateBaseExponentFactorsString(input));
    }

    @Test
    void listFactorsString() {
        ListPrimeFactors lpf = ListPrimeFactors.getInstance(null,null, 10, -1, primes);
        String lfs = lpf.ListFactorsString(250);
        assertEquals("2 × 5³",lfs);
    }
    @Test
    void validateTrue() {
        ListPrimeFactors lpf = ListPrimeFactors.getInstance(null,"2500", 10, 1, primes);
        assertTrue(lpf.validate(10L));
    }

    @Test
    void validateFalse() {
        ListPrimeFactors lpf = ListPrimeFactors.getInstance(null,"2500", 10, 1, primes);
        assertFalse(lpf.validate(-1L));
    }
}