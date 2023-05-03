package uk.co.example.ListPrimeFactorsService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseExponentTest {

    @Test
    void testToString() {
        BaseExponent be = new BaseExponent(3L, 234L);
        String result = be.toString();
        assertEquals(result, "3\u00B2\u00B3\u2074");
    }
}