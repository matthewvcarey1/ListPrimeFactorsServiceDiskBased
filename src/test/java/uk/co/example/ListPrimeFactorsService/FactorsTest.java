package uk.co.example.ListPrimeFactorsService;

import java.util.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactorsTest {

    TreeMap<Long, Long> scm = new TreeMap<>();
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
    }

    @AfterEach
    void tearDown() {
        primes = null;
    }

    @Test
    void listFactors50() {
        ArrayList<Long> factors = Factors.listFactors(50L, primes, scm, new ArrayList<Long>(),null, new FactorResultFlags());
        ArrayList<Long> expected = new ArrayList<>(Arrays.asList(2L,5L,5L));
        assertArrayEquals(expected.toArray(),factors.toArray());
    }
    @Test
    void listFactors2500() {
        ArrayList<Long> factors = Factors.listFactors(2500L, primes, scm, new ArrayList<Long>(),null, new FactorResultFlags());
        ArrayList<Long> expected = new ArrayList<>(Arrays.asList(2L,2L,5L,5L,5L,5L));
        assertArrayEquals(expected.toArray(),factors.toArray());
    }
    @Test
    void listFactors1283() {
        int oldSize = primes.size();
        ArrayList<Long> factors = Factors.listFactors(1283L, primes, scm, new ArrayList<Long>(),null, new FactorResultFlags());
        ArrayList<Long> expected = new ArrayList<>(List.of(1283L));
        assertArrayEquals(expected.toArray(),factors.toArray());
        try {
            Thread.sleep(10);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(primes.size(),oldSize + 1);
        assertTrue(primes.contains(1283L));
    }
    @Test
    void listFactors1() {
        ArrayList<Long> factors = Factors.listFactors(1L, primes, scm, new ArrayList<Long>(),null, new FactorResultFlags());
        ArrayList<Long> expected = new ArrayList<>();
        assertArrayEquals(expected.toArray(),factors.toArray());
    }
}