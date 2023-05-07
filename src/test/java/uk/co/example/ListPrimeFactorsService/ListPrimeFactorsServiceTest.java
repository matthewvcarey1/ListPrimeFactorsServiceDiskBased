package uk.co.example.ListPrimeFactorsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ListPrimeFactorsServiceTest {

    private ListPrimeFactorsService lps;
    private TreeSet<Long> primes;
    @BeforeEach
    void setUp() {
        lps = new ListPrimeFactorsService();
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
        ListPrimeFactors lpf = ListPrimeFactors.getInstance(null,"2500", 10, 1, primes);
        lps.setLimit(2500L);
        lps.setUpperLimitString("2500");
        lps.setLpf(lpf);
    }
    @AfterEach
    void teardown(){
        lps = null;
    }

    @Test
    void setPrimeDBPath() {
        lps.setPrimeDBPath("foo");
        assertEquals("foo",lps.getPrimesDbPath());
    }

    @Test
    void setUpperLimitString() {
        lps.setUpperLimitString("36");
        assertEquals("36",lps.getUpperLimitString());
    }

    @Test
    void setLimit() {
        lps.setLimit(36);
        assertEquals(36,lps.getLimit());
    }

    @Test
    void setLpf() {
        lps.setLpf(null);
        assertEquals(lps.lpf,null);
    }

    @Test
    void getLimit() {
        lps.setLimit(36);
        assertEquals(36,lps.getLimit());
    }

    @Test
    void getUpperLimitString() {
        lps.setUpperLimitString("36");
        assertEquals("36",lps.getUpperLimitString());
    }

    @Test
    void getPrimesDbPath() {
        lps.setPrimeDBPath("foo");
        assertEquals("foo",lps.getPrimesDbPath());
    }

    @Test
    void setCacheSize() {
        lps.setCacheSize(21);
        assertEquals(lps.getCacheSize(),21);
    }

    @Test
    void getCacheSize() {
        lps.setCacheSize(21);
        assertEquals(lps.getCacheSize(),21);
    }

    @Test
    void setCacheableMillisecs() {
        lps.setCacheableMillisecs(1000);
        assertEquals(lps.getCacheableMillisecs(),1000);
    }

    @Test
    void getCacheableMillisecs() {
        lps.setCacheableMillisecs(1000);
        assertEquals(lps.getCacheableMillisecs(),1000);
    }

    @Test
    void listHappy() {
        ListFactorsResult lfr = lps.list("250");
        assertEquals(lfr.error(), "");
        assertEquals(lfr.source(), "250");
        assertEquals(lfr.value(), "2 × 5³");
    }

    @Test
    void listTooSmall(){
        String upperLimitStr = lps.getUpperLimitString();
        ListFactorsResult lfr = lps.list("1");
        assertEquals("Value not in range  2 - " + upperLimitStr, lfr.error());
        assertEquals("", lfr.source());
        assertEquals("", lfr.value());
    }


    @Test
    void listNotNumber(){
        ListFactorsResult lfr = lps.list("<IMG SRC=j&#X41vascript:alert('test2')>\n" +
                "\n");
        assertEquals("The input value could not be parsed as a Long Integer",lfr.error());
        assertEquals(lfr.source(), "");
        assertEquals(lfr.value(), "");
    }
    @Test
    void limitsHappy() {
        PrimeLimits pl = lps.limits();
        assertEquals("2", pl.getLowerLimit());
        assertEquals("2500", pl.getUpperLimit());
        assertEquals("",pl.getError());
    }

    @Test
    void limitSad(){
        lps.setLimit(0);
        lps.setUpperLimitString("foo");
        PrimeLimits pl = lps.limits();
        assertEquals("", pl.getLowerLimit());
        assertEquals("", pl.getUpperLimit());
        assertEquals("For input string: \"foo\"",pl.getError());
    }
}