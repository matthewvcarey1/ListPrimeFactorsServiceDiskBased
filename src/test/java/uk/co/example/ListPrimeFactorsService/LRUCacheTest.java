package uk.co.example.ListPrimeFactorsService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    LRUCache cache;
    @BeforeEach
    void setUp() {
        cache=new LRUCache(3);
        cache.put(1L,"one");
        cache.put(2L,"two");
    }
    @AfterEach
    void teardown(){
        cache=null;
    }
    @Test
    void get() {
    }

    @Test
    void put1() {
        cache.put(3L,"three");
        assertEquals("three",cache.get(3L));
    }

    @Test
    void put2() {
        cache.put(3L,"three");
        cache.put(4L,"four");
        assertEquals("four",cache.get(4L));
        assertEquals(null,cache.get(1L));
    }
}