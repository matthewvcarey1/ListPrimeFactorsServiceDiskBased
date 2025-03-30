package uk.co.example.ListPrimeFactorsService;

import org.mapdb.DB;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.NavigableSet;


public final class Factors {

    private Factors(){}

    /**
     * Generate list of prime factors for a given number
     * @param num the input.
     * @param primeSet persistent set of existing primes
     * @param slowCompositeMap persistent map of slow to compute composite numbers
     * @param results the store for factors
     * @param db persistent database
     * @param flags to signal if a new prime was added to the persistent set of primes
     * @return results
     */
    static ArrayList<Long> listFactors(long num,
                                       NavigableSet<Long> primeSet, NavigableMap<Long,Long> slowCompositeMap,
                                       ArrayList<Long> results,
                                       DB db,
                                       FactorResultFlags flags) {
        // regular base case
        if (num < 2){
            return results;
        }
        if(primeSet.contains(num)){
            results.add(num);
            return results;
        }
        if (slowCompositeMap.containsKey(num)){
            long prime = slowCompositeMap.get(num);
            results.add(prime);
            return listFactors(num / prime, primeSet, slowCompositeMap, results, db, flags);
        }
        long sqrtNum = (long) Math.sqrt(num);
        for (long prime : primeSet) {
            if (prime > sqrtNum) {
                break;
            }
            if (num % prime == 0) {
                results.add(prime);
                return listFactors(num / prime, primeSet, slowCompositeMap, results, db, flags);
            }
        }
        results.add(num);
        flags.setNewPrime(true);
        // Kick off a thread to store the newly discovered prime number in the database.
        Runnable r = () -> Factors.insertPrime(num, primeSet, db);
        new Thread(r).start();
        return results;
    }

    /**
     * Inserts number in set of primes
     * @param num
     * the number to insert.
     *
     * @param primeSet
     * set of primes.
     *
     * @param db
     * the disk based db could be null.
     */
    private static synchronized void insertPrime(long num, NavigableSet<Long> primeSet, DB db) {
        System.out.println("inserting: "+num);
        primeSet.add(num);
        if(db != null) db.commit();
    }

}
